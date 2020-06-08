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
	public List<UserInfoDeleteForm> UserInfoDeleteInit() {

		List<UserInfoDeleteForm> userInfoList = userInfoMapper.selectIdNameForDelete();

		return userInfoList;
	}

	@Override
	public UserInfoDeleteFormDetail UserInfoDelete(UserInfoDeleteFormDetail userInfoDeleteFormDetail) {

		//メッセージの初期化
		userInfoDeleteFormDetail.setMessage("");

		//チェックボックスが選択されていない場合
		if (userInfoDeleteFormDetail.getCheckBox() == null) {
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);
			return userInfoDeleteFormDetail;
		}

		try {
			//DELETE
			for (String id : userInfoDeleteFormDetail.getCheckBox()) {
				userInfoMapper.deleteData(id);
				salaryInfoMapper.deleteData(id);
			}

			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_OK);

			//DELETE後のリストを取得
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdNameForDelete());

		} catch (Exception e) {
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_NG);
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdNameForDelete());
		}

		return userInfoDeleteFormDetail;
	}

}
