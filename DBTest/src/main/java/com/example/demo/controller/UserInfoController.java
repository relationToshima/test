package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;

@Controller
public class UserInfoController {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;

	@RequestMapping(value="/test")
	public String UserInfo(Model model) {

		//ユーザー情報を取得
		List<UserInfo> userInfoList = userInfoMapper.selectAll();
		model.addAttribute("UserInfo",userInfoList);

		//給与情報を取得
		List<SalaryInfo> salaryInfoList = salaryInfoMapper.selectAll();
		model.addAttribute("SalaryInfo",salaryInfoList);

		//各人の給与計算
		List<Double> salaryInfo = new ArrayList<Double>();
		for(SalaryInfo salaryInfoTmpList : salaryInfoList) {

			if(salaryInfoTmpList.getPosition().trim().equals("Administrater")) {
				salaryInfo.add(salaryInfoTmpList.getBasicSalary()*1.25);
			}else {
				salaryInfo.add((double) salaryInfoTmpList.getBasicSalary());
			}
		}

		model.addAttribute("Salary",salaryInfo);

		return "userInfo";
	}

}
