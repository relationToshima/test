package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.ServiceImpl.InsertToUserInfoServiceImpl;
import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;
import com.example.demo.domain.OfficeWorker;
import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;

@Controller
public class UserInfoController {

    @Autowired
    SalaryOutputServiceImpl salaryOutputServiceImpl;
    @Autowired
    InsertToUserInfoServiceImpl inertToUserInfoServiceImpl;

	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	public String UserInfo(Model model) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> data = salaryOutputServiceImpl.SalaryCalculationOutput();
		model.addAttribute("data",data);

		return "userInfo";
	}

	@RequestMapping(value = "/insertToReadOnly" , method = RequestMethod.GET)
	public String InsertTotReadOnly(Model model) {

		//フリガナ
		String nameReading = "イシイ　ヨンコ";
		//氏名
		String name = "石井　四子";
		//メールアドレス
		String mailAddress = "yonko.ishii@mail.com";
		//パスワード
		String password = "ishii";
		//登録者
		String registrant = "田中　太郎";
		//役職
		String position = "Member";
		//基本給
		int basicSalary = 190000;

		UserInfo userInfoToInsert = new UserInfo();
		userInfoToInsert.setNameReading(nameReading);
		userInfoToInsert.setName(name);
		userInfoToInsert.setMailAddress(mailAddress);
		userInfoToInsert.setPassword(password);
		userInfoToInsert.setRegistrant(registrant);

		SalaryInfo salaryInfoToInsert = new SalaryInfo();
		salaryInfoToInsert.setPosition(position);
		salaryInfoToInsert.setBasicSalary(basicSalary);

		boolean result;
		result = inertToUserInfoServiceImpl.UserInfoInsert(userInfoToInsert, salaryInfoToInsert);

		if(result == true) {
			model.addAttribute("result","インサートに成功しました。");
		}else {
			model.addAttribute("result","インサートに失敗しました。");
		}

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> data = salaryOutputServiceImpl.SalaryCalculationOutput();
		model.addAttribute("data",data);

		return "userInfo";
	}

}
