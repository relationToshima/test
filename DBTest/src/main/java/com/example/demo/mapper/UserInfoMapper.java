package com.example.demo.mapper;

import java.util.List;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.UserInfo;
import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.form.UserInfoSearchForm;
import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface UserInfoMapper {
	List<UserInfo> selectAll();

	List<UserInfoDeleteForm> selectIdNameForDelete();

	List<UserInfoUpdateListForm> selectUserInfoForUpdateList();

	List<UserInfoUpdateListForm> selectUserInfoForUpdateListOnlyMe(String id);

	UserInfoUpdateFormDetail selectUserInfoForUpdate(String id);

	void updateUserInfo(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	void deleteData(String id);

	String selectMaxId();

	void insertUserInfo(UserInfo userInfoToInsert);

	List<UserInfoSearchForm> selectUserInfoForSearch();

	LoginUser selectUserAuth(String mailAddress);

	int selectMailAddressCount(String mailAddress);
}