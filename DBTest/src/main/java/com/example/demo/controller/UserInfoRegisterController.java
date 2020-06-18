package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoRegisterServiceImpl;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;

@Controller
public class UserInfoRegisterController {

	@Autowired
	UserInfoRegisterServiceImpl userInfoRegisterServiceImpl;

	/**
	 * UserInfoRegisterBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoRegisterBack(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * UserIngoRegisterメソッド
	 * 入力値のデータチェックを行い、ユーザー情報の登録を行う.
	 * @param userInfoRegisterFormDetail , model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "submit", method = RequestMethod.POST)
	public ModelAndView UserInfoRegister(
			@ModelAttribute("userInfoRegisterFormDetail") UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			ModelAndView mav) {

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegister(userInfoRegisterFormDetail);
		mav.addObject("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
		return mav;

	}

}