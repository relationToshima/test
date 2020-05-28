package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.officeWorker.Administrater;
import com.example.demo.officeWorker.Menber;

@Controller
public class UserInfoController {

	@Autowired
	Administrater administrater;
	@Autowired
	Menber menber;

	@RequestMapping(value="/test")
	public String UserInfo(Model model) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/

		//管理者の取得
		List<OfficeWorker> adminList =  administrater.DBSelect();
		model.addAttribute("Admin",adminList);

		//メンバーの取得
		List<OfficeWorker> menberList = menber.DBSelect();
		model.addAttribute("Menber",menberList);

		return "userInfo";
	}

}
