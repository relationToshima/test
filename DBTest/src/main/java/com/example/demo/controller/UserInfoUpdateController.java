package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public ModelAndView UserInfoUpdateBack(@ModelAttribute UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			ModelAndView mav) {

		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = userInfoUpdateServiceImpl
				.UserInfoUpdateBack(userInfoUpdateFormDetail);

		mav.addObject("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
		mav.setViewName("UserInfoUpdateList");
		return mav;
	}

	/**
	 * ImageUploadメソッド
	 * 画像のアップロードを行う.
	 * @param userInfoRegisterFormDetail
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdate", params = "imageUpload", method = RequestMethod.POST)
	public ModelAndView ImageUpload(@ModelAttribute UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			ModelAndView mav) {

		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.imageUpload(userInfoUpdateFormDetail);

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.formReset(userInfoUpdateFormDetail);
		mav.setViewName("UserInfoUpdate");
		return mav;
	}

	@RequestMapping(value = "/UserInfoUpdate", params = "imageDelete", method = RequestMethod.POST)
	public ModelAndView ImageDelete(@ModelAttribute UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			ModelAndView mav) {

		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.imageDelete(userInfoUpdateFormDetail);

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.formReset(userInfoUpdateFormDetail);
		mav.setViewName("UserInfoUpdate");
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
			@Validated @ModelAttribute UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			BindingResult result, ModelAndView mav) {

		//入力値にエラーがある場合
		if (result.hasErrors()) {
			/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
			userInfoUpdateFormDetail = userInfoUpdateServiceImpl.formReset(userInfoUpdateFormDetail);
			mav.setViewName("UserInfoUpdate");
			return mav;
		}

		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.UserInfoUpdate(userInfoUpdateFormDetail);
		mav.addObject("userInfoUpdateFormDetail", userInfoUpdateFormDetail);
		mav.setViewName("UserInfoUpdate");
		return mav;

	}

}