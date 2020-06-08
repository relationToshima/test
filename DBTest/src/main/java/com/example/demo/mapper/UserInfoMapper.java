package com.example.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.UserInfo;
import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.form.UserInfoSearchForm;
import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface UserInfoMapper {
	List<UserInfo> selectAll();

	List<UserInfoDeleteForm> selectIdNameForDelete();

	List<UserInfoUpdateListForm> selectIdNameForUpdateList();

	UserInfoUpdateFormDetail selectUserInfoForUpdate(String id);

	void updateUserInfo(UserInfoUpdateFormDetail userInfoUpdateFormDetail);

	void deleteData(String id);

	String selectMaxId();

	boolean insertUserInfo(@Param("userInfoToInsert") UserInfo userInfoToInsert) throws SQLException;

	List<UserInfoSearchForm> selectUserInfoForSearch();
}