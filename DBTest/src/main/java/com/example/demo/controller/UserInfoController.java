package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.UserInfoMapper;

@Controller
public class UserInfoController {

	@Autowired
	UserInfoMapper userInfoMapper;

	@RequestMapping(value="/test")
	public String UserInfo(Model model) {
		List<UserInfo> list = userInfoMapper.selectAll();
		model.addAttribute("UserInfo",list);
		return "userInfo";
	}

}
