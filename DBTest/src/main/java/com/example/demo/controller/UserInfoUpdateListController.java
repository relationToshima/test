package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoUpdateListServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateServiceImpl;
import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

@Controller
public class UserInfoUpdateListController {

	@Autowired
	UserInfoUpdateListServiceImpl userInfoUpdateListServiceImpl;
	@Autowired
	UserInfoUpdateServiceImpl userInfoUpdateServiceImpl;

	/**
	 * UserInfoUpdateListBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdateList", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoUpdateListBack(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * UserInfoUpdateInitメソッド
	 * 更新対象のユーザーの選択を取得し、更新画面を表示する.
	 * @param userInfoUpdateFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdateList", params = "submit", method = RequestMethod.POST)
	public ModelAndView UserInfoUpdateInit(
			@ModelAttribute("userInfoUpdateListFormDetail") UserInfoUpdateListFormDetail userInfoUpdateListFormDetail,
			ModelAndView mav) {

		//ラジオボタンの選択チェック
		if (!(userInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(userInfoUpdateListFormDetail))) {
			userInfoUpdateListFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);
			mav.addObject("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
			mav.setViewName("userInfoUpdateList");
			return mav;
		}

		//更新画面のデータ取得
		UserInfoUpdateFormDetail userInfoUpdateFormDetail = userInfoUpdateServiceImpl
				.UserInfoUpdateInit(userInfoUpdateListFormDetail.getRadio());
		mav.addObject("userInfoUpdateFormDetail", userInfoUpdateFormDetail);
		mav.setViewName("userInfoUpdate");
		return mav;

	}

}