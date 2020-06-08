package com.example.demo.service;

import com.example.demo.formDetail.UserInfoRegisterFormDetail;

public interface UserInfoRegisterService {

	UserInfoRegisterFormDetail UserInfoRegisterInit();

	UserInfoRegisterFormDetail UserInfoRegister(UserInfoRegisterFormDetail form);

}
