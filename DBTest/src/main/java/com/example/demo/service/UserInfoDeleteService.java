package com.example.demo.service;

import java.util.List;

import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;

public interface UserInfoDeleteService {
	UserInfoDeleteFormDetail UserInfoDelete(UserInfoDeleteFormDetail userInfoDeleteFormDetail);

	List<UserInfoDeleteForm> UserInfoDeleteInit();
}
