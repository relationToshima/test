package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoDeleteService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoDeleteServiceImpl implements UserInfoDeleteService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	StringUtils stringUtils;

	@Override
	public List<UserInfoDeleteForm> userInfoSelectForDelete() {

		List<UserInfoDeleteForm> userInfoList = userInfoMapper.selectIdName();

		return userInfoList;
	}

	@Override
	public UserInfoDeleteFormDetail userInfoDelete(String[] checkBox) {

		boolean result = true;
		String idList = "";
		for (String str : checkBox) {
			idList = idList + " " + str;
		}
		idList = stringUtils.trim(idList);
		idList = idList.replaceAll(" ", ",");
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();

		try {
			userInfoMapper.deleteData(idList);
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_OK);
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdName());

		} catch (Exception e) {
			userInfoDeleteFormDetail.setMessage(ConstantsMsg.MSG_DELETE_NG);
			userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoMapper.selectIdName());
		}

		return userInfoDeleteFormDetail;
	}

}
