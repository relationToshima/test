package com.example.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.UserInfo;

public interface UserInfoMapper {
	List<UserInfo> selectAll();
	String selectMaxId();
	boolean insertUserInfo(@Param ("userInfoToInsert") UserInfo userInfoToInsert) throws SQLException;
}