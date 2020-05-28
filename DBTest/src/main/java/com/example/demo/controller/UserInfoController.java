package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.service.AdministraterService;
import com.example.demo.service.MenberService;

@Controller
public class UserInfoController {

	@Autowired
	AdministraterService administraterService;
	@Autowired
	MenberService menberService;

	@RequestMapping(value="/test")
	public String UserInfo(Model model) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/

		//管理者の取得
		List<OfficeWorker> adminList =  administraterService.DBSelect();
		model.addAttribute("Admin",adminList);

		//メンバーの取得
		List<OfficeWorker> menberList = menberService.DBSelect();
		model.addAttribute("Menber",menberList);

		return "userInfo";
	}

}
