package com.example.demo.service;

import java.util.List;

import com.example.demo.formDetail.UserInfoSearchFormDetail;

public interface UserInfoSearchService {

	UserInfoSearchFormDetail UserInfoSearchInit();

	List<String> itemReturn(int i);

	UserInfoSearchFormDetail UserInfoSearchSortOn(UserInfoSearchFormDetail userInfoSearchFormDetail);

	UserInfoSearchFormDetail SearchOn(UserInfoSearchFormDetail userInfoSearchFormDetail);

	UserInfoSearchFormDetail SearchOff(UserInfoSearchFormDetail userInfoSearchFormDetail);

	UserInfoSearchFormDetail SortOn(UserInfoSearchFormDetail userInfoSearchFormDetail);

	UserInfoSearchFormDetail SortOff(UserInfoSearchFormDetail userInfoSearchFormDetail);

}
