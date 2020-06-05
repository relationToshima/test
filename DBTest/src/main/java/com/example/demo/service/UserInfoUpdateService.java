package com.example.demo.service;

import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface UserInfoUpdateService {

	UserInfoUpdateFormDetail UserInfoSelectForUpdate(String id);

	UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	UserInfoUpdateFormDetail UserInfoUpdateFormDetailAllTrim(UserInfoUpdateFormDetail userInfoUpdateFormDetail);
}
