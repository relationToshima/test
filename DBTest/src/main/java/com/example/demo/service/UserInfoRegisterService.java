package com.example.demo.service;

import java.io.IOException;

import com.example.demo.formDetail.UserInfoRegisterFormDetail;

public interface UserInfoRegisterService {

	UserInfoRegisterFormDetail UserInfoRegisterInit();

	UserInfoRegisterFormDetail UserInfoRegister(UserInfoRegisterFormDetail form) throws IOException;

	UserInfoRegisterFormDetail imageUpload(UserInfoRegisterFormDetail form);

	UserInfoRegisterFormDetail imageDelete(UserInfoRegisterFormDetail userInfoRegisterFormDetail);

}
