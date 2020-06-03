package com.example.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.UserInfo;
import com.example.demo.form.UserInfoDeleteForm;

public interface UserInfoMapper {
	List<UserInfo> selectAll();

	List<UserInfoDeleteForm> selectIdName();

	void deleteData(String idList);

	String selectMaxId();

	boolean insertUserInfo(@Param("userInfoToInsert") UserInfo userInfoToInsert) throws SQLException;
}