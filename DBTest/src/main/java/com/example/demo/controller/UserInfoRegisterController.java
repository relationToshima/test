package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.UserInfoRegisterServiceImpl;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.utils.ImageUtils;

@Controller
public class UserInfoRegisterController {

	@Autowired
	UserInfoRegisterServiceImpl userInfoRegisterServiceImpl;
	@Autowired
	ImageUtils imageUtils;

	/**
	 * UserInfoRegisterBackメソッド
	 * 戻るボタン.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "back", method = RequestMethod.POST)
	public ModelAndView UserInfoRegisterBack(ModelAndView mav) {

		imageUtils.DeleteUploadFile();

		mav.setViewName("top");
		return mav;
	}

	/**
	 * ImageUploadメソッド
	 * 画像のアップロードを行う.
	 * @param userInfoRegisterFormDetail
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "imageUpload", method = RequestMethod.POST)
	public ModelAndView ImageUpload(@ModelAttribute UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			ModelAndView mav) {

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.imageUpload(userInfoRegisterFormDetail);

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.FormReset(userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
		return mav;
	}

	@RequestMapping(value = "/UserInfoRegister", params = "imageDelete", method = RequestMethod.POST)
	public ModelAndView ImageDelete(@ModelAttribute UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			ModelAndView mav) {

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.imageDelete(userInfoRegisterFormDetail);

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.FormReset(userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
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
			@Validated @ModelAttribute UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			BindingResult result, ModelAndView mav) {

		//入力値にエラーがある場合
		if (result.hasErrors()) {
			/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
			userInfoRegisterFormDetail = userInfoRegisterServiceImpl.FormReset(userInfoRegisterFormDetail);
			mav.setViewName("userInfoRegister");
			return mav;
		}

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegister(userInfoRegisterFormDetail);
		mav.addObject("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
		return mav;

	}

}