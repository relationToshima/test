package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoDeleteServiceImpl;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;

@Controller
public class UserInfoDeleteController {

	@Autowired
	UserInfoDeleteServiceImpl userInfoDeleteServiceImpl;

	/**
	 * UserInfoDeleteBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoDeleteBack(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * UserInfoDeleteメソッド
	 * 選択したユーザー情報を削除する.
	 * @param userInfoDeleteFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", params = "submit", method = RequestMethod.POST)
	public ModelAndView UserInfoDelete(
			@ModelAttribute("userInfoDeleteFormDetail") UserInfoDeleteFormDetail userInfoDeleteFormDetail,
			ModelAndView mav) {

		//確認ポップアップ表示する

		userInfoDeleteFormDetail = userInfoDeleteServiceImpl.UserInfoDelete(userInfoDeleteFormDetail);
		mav.addObject("userInfoDeleteFormDetail", userInfoDeleteFormDetail);
		mav.setViewName("userInfoDelete");
		return mav;

	}

}