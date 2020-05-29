package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;
import com.example.demo.domain.OfficeWorker;

@Controller
public class UserInfoController {

/*
	@Autowired
	OfficeWorkerInterface officeWorker;
	//AdministraterService administraterService;
	@Autowired
	MenberService menberService;

    @Autowired
    private AutowireCapableBeanFactory factory;

    private <T> T createAutowiredObject(Class<T> c){
        return factory.createBean(c);
    }
*/
    @Autowired
    SalaryOutputServiceImpl salaryOutputServiceImpl;

	@RequestMapping(value="/test")
	public String UserInfo(Model model) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> data = salaryOutputServiceImpl.SalaryCalculationOutput();
		model.addAttribute("data",data);
/*
		//管理者の取得
		OfficeWorkerInterface admin =  createAutowiredObject(AdministraterService.class);
		List<OfficeWorker> adminList = admin.DBSelect();
		//List<OfficeWorker> adminList =  administraterService.DBSelect();
		model.addAttribute("Admin",adminList);

		//メンバーの取得
		List<OfficeWorker> menberList = menberService.DBSelect();
		model.addAttribute("Menber",menberList);
*/
		return "userInfo";
	}

}
