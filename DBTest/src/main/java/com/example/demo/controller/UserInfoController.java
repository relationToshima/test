package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;
import com.example.demo.ServiceImpl.UserInfoDeleteServiceImpl;
import com.example.demo.ServiceImpl.UserInfoRegisterServiceImpl;
import com.example.demo.ServiceImpl.UserInfoSearchServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateListServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateServiceImpl;
import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.domain.OfficeWorker;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.formDetail.UserInfoSearchFormDetail;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

@Controller
public class UserInfoController {

	@Autowired
	SalaryOutputServiceImpl salaryOutputServiceImpl;
	@Autowired
	UserInfoRegisterServiceImpl userInfoRegisterServiceImpl;
	@Autowired
	UserInfoDeleteServiceImpl userInfoDeleteServiceImpl;
	@Autowired
	UserInfoUpdateListServiceImpl userInfoUpdateListServiceImpl;
	@Autowired
	UserInfoUpdateServiceImpl userInfoUpdateServiceImpl;
	@Autowired
	UserInfoSearchServiceImpl userInfoSearchServiceImpl;

	/**
	 * topメソッド
	 * 社員情報管理トップ画面を表示する.
	 * @return
	 */
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public ModelAndView Top(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * UserInfoSearchInitメソッド
	 * ユーザー情報検索画面の初期表示を行う.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", method = RequestMethod.GET)
	public ModelAndView UserInfoSearchInit(ModelAndView mav) {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();
		userInfoSearchFormDetail = userInfoSearchServiceImpl.UserInfoSearchInit();
		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");
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

	/**
	 * UserInfoUpdateListInitメソッド
	 * ユーザー情報更新一覧画面を表示する.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdateList", method = RequestMethod.GET)
	public ModelAndView UserInfoUpdateListInit(ModelAndView mav) {
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail
				.setUserInfoUpdateListFormList(userInfoUpdateListServiceImpl.UserInfoSelectForUpdateList());
		mav.addObject("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
		mav.setViewName("UserInfoUpdateList");
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

	/**
	 * UserInfoDeleteメソッド
	 * ユーザー情報削除画面を表示する.
	 * @para	m model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", method = RequestMethod.GET)
	public ModelAndView UserInfoDeleteInit(ModelAndView mav) {
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoDeleteServiceImpl.UserInfoDeleteInit());
		mav.addObject("userInfoDeleteFormDetail", userInfoDeleteFormDetail);
		mav.setViewName("userInfoDelete");
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

	/**
	 * SalaryInfoListメソッド
	 * 給与情報一覧画面を出力する.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/SalaryInfoList", method = RequestMethod.GET)
	public ModelAndView SalaryInfoList(ModelAndView mav) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> officeWorkerList = salaryOutputServiceImpl.SalaryCalculationOutput();
		mav.addObject("data", officeWorkerList);
		mav.setViewName("salaryInfoList");

		return mav;
	}

	/**
	 * UserInfoRegisterInitメソッド
	 * ユーザー情報登録画面の初期表示を行う.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", method = RequestMethod.GET)
	public ModelAndView UserInfoRegisterInit(ModelAndView mav) {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegisterInit();
		mav.addObject("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
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
			@ModelAttribute("userInfoRegisterFormDetail") UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			ModelAndView mav) {

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegister(userInfoRegisterFormDetail);
		mav.addObject("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
		return mav;

	}

}
