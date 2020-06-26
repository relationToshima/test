package com.example.demo.service;

import java.util.List;

import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

public interface UserInfoUpdateService {

	UserInfoUpdateFormDetail UserInfoUpdateInit(String id);

	UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	UserInfoUpdateFormDetail UserInfoUpdateFormDetailAllTrim(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	List<String> itemReturn();

	UserInfoUpdateFormDetail imageUpload(UserInfoUpdateFormDetail form);

	UserInfoUpdateListFormDetail UserInfoUpdateBack(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	UserInfoUpdateFormDetail imageDelete(UserInfoUpdateFormDetail userInfoUpdateFormDetail);
}
