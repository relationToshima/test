package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;

@Controller
public class SalaryInfoListController {

	@Autowired
	SalaryOutputServiceImpl salaryOutputServiceImpl;

	/**
	 * SalaryInfoListBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/SalaryInfoList", params = "back", method = RequestMethod.POST)
	public ModelAndView SalaryInfoListBack(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

}