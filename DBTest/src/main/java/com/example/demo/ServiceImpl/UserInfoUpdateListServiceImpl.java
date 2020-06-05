package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoUpdateListService;

@Service
public class UserInfoUpdateListServiceImpl implements UserInfoUpdateListService {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfoUpdateListForm> UserInfoSelectForUpdateList() {
		List<UserInfoUpdateListForm> userInfoUpdateListFormList = userInfoMapper.selectIdNameForUpdateList();
		return userInfoUpdateListFormList;
	}

	@Override
	public boolean UserInfoUpdateListSelectCheck(UserInfoUpdateListFormDetail userInfoUpdateListFormDetail) {

		boolean result = true;

		if (userInfoUpdateListFormDetail.getRadio() == null) {
			result = false;
		}
		return result;
	}

}
