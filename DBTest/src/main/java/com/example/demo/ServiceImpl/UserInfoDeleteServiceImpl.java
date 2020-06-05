package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoDeleteService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoDeleteServiceImpl implements UserInfoDeleteService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	@Autowired
	StringUtils stringUtils;

	@Override
	public List<UserInfoDeleteForm> userInfoSelectForDelete() {

		List<UserInfoDeleteForm> userInfoList = userInfoMapper.selectIdNameForDalete();

		return userInfoList;
	}

	@Override
	public UserInfoDeleteFormDetail userInfoDelete(UserInfoDeleteFormDetail userInfoDeleteFormDetail) {

		//メッセージの初期化
		userInfoDeleteFormDetail.setMessage("");

		//チェックボックスが選択されていない場合
		if (userInfoDeleteFormDetail.getCheckBox() == null) {
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);
			return userInfoDeleteFormDetail;
		}

		//選択したチェックボックスの値をDELETE文に使用できるように整形
		boolean result = true;
		String idList = "";
		for (String str : userInfoDeleteFormDetail.getCheckBox()) {
			idList = idList + " " + str;
		}
		idList = stringUtils.trim(idList);
		idList = idList.replaceAll(" ", ",");

		try {
			//DELETE
			userInfoMapper.deleteData(idList);
			salaryInfoMapper.deleteData(idList);
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_OK);

			//DELETE後のリストを取得
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdNameForDalete());

		} catch (Exception e) {
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_NG);
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdNameForDalete());
		}

		return userInfoDeleteFormDetail;
	}

}
