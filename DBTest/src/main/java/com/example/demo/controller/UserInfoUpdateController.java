package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoUpdateListServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateServiceImpl;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

@Controller
public class UserInfoUpdateController {

	@Autowired
	UserInfoUpdateListServiceImpl userInfoUpdateListServiceImpl;
	@Autowired
	UserInfoUpdateServiceImpl userInfoUpdateServiceImpl;

	/**
	 * UserInfoUpdateBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "UserInfoUpdate", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoUpdateBack(ModelAndView mav) {
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail
				.setUserInfoUpdateListFormList(userInfoUpdateListServiceImpl.UserInfoSelectForUpdateList());
		mav.addObject("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
		mav.setViewName("UserInfoUpdateList");
		return mav;
	}

	/**
	 * UserInfoUpdateメソッド
	 * ユーザー情報の更新を行う.
	 * @param userInfoUpdateFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "UserInfoUpdate", params = "submit", method = RequestMethod.POST)
	public ModelAndView UserInfoUpdate(
			@ModelAttribute("userInfoUpdateFormDetail") UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			ModelAndView mav) {

		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.UserInfoUpdate(userInfoUpdateFormDetail);
		mav.addObject("userInfoUpdateFormDetail", userInfoUpdateFormDetail);
		mav.setViewName("UserInfoUpdate");
		return mav;

	}

}