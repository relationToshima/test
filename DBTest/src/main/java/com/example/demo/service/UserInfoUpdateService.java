package com.example.demo.service;

import java.util.List;

import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface UserInfoUpdateService {

	UserInfoUpdateFormDetail UserInfoUpdateInit(String id);

	UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	UserInfoUpdateFormDetail UserInfoUpdateFormDetailAllTrim(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	List<String> itemReturn();
}
