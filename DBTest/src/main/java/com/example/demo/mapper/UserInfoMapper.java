package com.example.demo.mapper;

import java.util.List;

import com.example.demo.domain.UserInfo;

public interface UserInfoMapper {
	List<UserInfo> selectAll();
}