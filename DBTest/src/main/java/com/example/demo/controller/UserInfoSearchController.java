package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoSearchServiceImpl;
import com.example.demo.formDetail.UserInfoSearchFormDetail;

@Controller
public class UserInfoSearchController {

	@Autowired
	UserInfoSearchServiceImpl userInfoSearchServiceImpl;

	/**
	 * UserInfoSearchBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoSearchBack(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * UserInfoSearchOnメソッド
	 * ユーザー情報検索画面で検索を行う.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "searchOn", method = RequestMethod.POST)
	public ModelAndView UserInfoSearchOn(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			ModelAndView mav) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SearchOn(userInfoSearchFormDetail);

		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");
		return mav;
	}

	/**
	 * UserInfoSearchOffメソッド
	 * ユーザー情報検索画面で検索を解除する.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "searchOff", method = RequestMethod.POST)
	public ModelAndView UserInfoSearchOff(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			ModelAndView mav) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SearchOff(userInfoSearchFormDetail);

		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");
		return mav;
	}

	/**
	 * UserInfoSortOnメソッド
	 * ユーザー情報検索画面で並べ替えを行う.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "sortOn", method = RequestMethod.POST)
	public ModelAndView UserInfoSortOn(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			ModelAndView mav) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SortOn(userInfoSearchFormDetail);

		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");

		return mav;
	}

	/**
	 * UserInfoSortOffメソッド
	 * ユーザー情報検索画面で並べ替えを解除する.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "sortOff", method = RequestMethod.POST)
	public ModelAndView UserInfoSortOff(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			ModelAndView mav) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SortOff(userInfoSearchFormDetail);

		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");

		return mav;
	}

}