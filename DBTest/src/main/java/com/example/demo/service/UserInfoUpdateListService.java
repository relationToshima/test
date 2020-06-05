package com.example.demo.service;

import java.util.List;

import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

public interface UserInfoUpdateListService {

	List<UserInfoUpdateListForm> UserInfoSelectForUpdateList();

	boolean UserInfoUpdateListSelectCheck(UserInfoUpdateListFormDetail userInfoUpdateListFormDetail);

}
