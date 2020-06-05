package com.example.demo.service;

import java.util.List;

import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;

public interface UserInfoDeleteService {
	UserInfoDeleteFormDetail userInfoDelete(UserInfoDeleteFormDetail userInfoDeleteFormDetail);

	List<UserInfoDeleteForm> userInfoSelectForDelete();
}
