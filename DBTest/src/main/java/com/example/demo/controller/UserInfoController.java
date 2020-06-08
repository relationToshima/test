package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.ServiceImpl.InsertToUserInfoServiceImpl;
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
	InsertToUserInfoServiceImpl inertToUserInfoServiceImpl;
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
	public String Top() {
		return "top";
	}

	/**
	 * UserInfoSearchInitメソッド
	 * ユーザー情報検索画面の初期表示を行う.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", method = RequestMethod.GET)
	public String UserInfoSearchInit(Model model) {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();
		userInfoSearchFormDetail = userInfoSearchServiceImpl.UserInfoSearchInit();
		model.addAttribute("userInfoSearchFormDetail", userInfoSearchFormDetail);
		return "userInfoSearch";
	}

	/**
	 * UserInfoSearchOnメソッド
	 * ユーザー情報検索画面で検索を行う.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "searchOn", method = RequestMethod.POST)
	public String UserInfoSearchOn(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			Model model) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SearchOn(userInfoSearchFormDetail);

		model.addAttribute("userInfoSearchFormDetail", userInfoSearchFormDetail);

		return "userInfoSearch";
	}

	/**
	 * UserInfoSearchOffメソッド
	 * ユーザー情報検索画面で検索を解除する.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "searchOff", method = RequestMethod.POST)
	public String UserInfoSearchOff(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			Model model) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SearchOff(userInfoSearchFormDetail);

		model.addAttribute("userInfoSearchFormDetail", userInfoSearchFormDetail);

		return "userInfoSearch";
	}

	/**
	 * UserInfoSortOnメソッド
	 * ユーザー情報検索画面で並べ替えを行う.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "sortOn", method = RequestMethod.POST)

	public String UserInfoSortOn(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			Model model) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SortOn(userInfoSearchFormDetail);

		model.addAttribute("userInfoSearchFormDetail", userInfoSearchFormDetail);

		return "userInfoSearch";
	}

	/**
	 * UserInfoSortOffメソッド
	 * ユーザー情報検索画面で並べ替えを解除する.
	 * @param userInfoSearchFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoSearch", params = "sortOff", method = RequestMethod.POST)
	public String UserInfoSortOff(
			@ModelAttribute("userInfoSearchFormDetail") UserInfoSearchFormDetail userInfoSearchFormDetail,
			Model model) {

		userInfoSearchFormDetail = userInfoSearchServiceImpl.SortOff(userInfoSearchFormDetail);

		model.addAttribute("userInfoSearchFormDetail", userInfoSearchFormDetail);

		return "userInfoSearch";
	}

	/**
	 * UserInfoUpdateメソッド
	 * ユーザー情報の更新を行う.
	 * @param userInfoUpdateFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "UserInfoUpdate", params = "submit", method = RequestMethod.POST)
	public String UserInfoUpdate(
			@ModelAttribute("userInfoUpdateFormDetail") UserInfoUpdateFormDetail userInfoUpdateFormDetail,
			Model model) {

		userInfoUpdateFormDetail = userInfoUpdateServiceImpl.UserInfoUpdate(userInfoUpdateFormDetail);
		model.addAttribute("userInfoUpdateFormDetail", userInfoUpdateFormDetail);
		return "UserInfoUpdate";

	}

	/**
	 * UserInfoUpdateListInitメソッド
	 * ユーザー情報更新一覧画面を表示する.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdateList", method = RequestMethod.GET)
	public String UserInfoUpdateListInit(Model model) {
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail
				.setUserInfoUpdateListFormList(userInfoUpdateListServiceImpl.UserInfoSelectForUpdateList());
		model.addAttribute("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
		return "UserInfoUpdateList";
	}

	/**
	 * UserInfoUpdateInitメソッド
	 * 更新対象のユーザーの選択を取得し、更新画面を表示する.
	 * @param userInfoUpdateFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoUpdateList", params = "submit", method = RequestMethod.POST)
	public String UserInfoUpdateInit(
			@ModelAttribute("userInfoUpdateListFormDetail") UserInfoUpdateListFormDetail userInfoUpdateListFormDetail,
			Model model) {

		//ラジオボタンの選択チェック
		if (!(userInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(userInfoUpdateListFormDetail))) {
			userInfoUpdateListFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);
			model.addAttribute("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
			return "userInfoUpdateList";
		}

		//更新画面のデータ取得
		UserInfoUpdateFormDetail userInfoUpdateFormDetail = userInfoUpdateServiceImpl
				.UserInfoUpdateInit(userInfoUpdateListFormDetail.getRadio());
		model.addAttribute("userInfoUpdateFormDetail", userInfoUpdateFormDetail);
		return "userInfoUpdate";

	}

	/**
	 * UserInfoDeleteメソッド
	 * ユーザー情報削除画面を表示する.
	 * @para	m model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", method = RequestMethod.GET)
	public String UserInfoDeleteInit(Model model) {
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoDeleteServiceImpl.UserInfoDeleteInit());
		model.addAttribute("userInfoDeleteFormDetail", userInfoDeleteFormDetail);
		return "userInfoDelete";
	}

	/**
	 * UserInfoDeleteメソッド
	 * 選択したユーザー情報を削除する.
	 * @param userInfoDeleteFormDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", params = "submit", method = RequestMethod.POST)
	public String UserInfoDelete(
			@ModelAttribute("userInfoDeleteFormDetail") UserInfoDeleteFormDetail userInfoDeleteFormDetail,
			Model model) {

		//確認ポップアップ表示する

		userInfoDeleteFormDetail = userInfoDeleteServiceImpl.UserInfoDelete(userInfoDeleteFormDetail);
		model.addAttribute("userInfoDeleteFormDetail", userInfoDeleteFormDetail);
		return "userInfoDelete";

	}

	/**
	 * SalaryInfoListメソッド
	 * 給与情報一覧画面を出力する.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/SalaryInfoList", method = RequestMethod.GET)
	public String SalaryInfoList(Model model) {

		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> officeWorkerList = salaryOutputServiceImpl.SalaryCalculationOutput();
		model.addAttribute("data", officeWorkerList);

		return "salaryInfoList";
	}

	/**
	 * UserInfoRegisterInitメソッド
	 * ユーザー情報登録画面の初期表示を行う.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", method = RequestMethod.GET)
	public String UserInfoRegisterInit(Model model) {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegisterInit();
		model.addAttribute("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		return "userInfoRegister";
	}

	/**
	 * UserIngoRegisterメソッド
	 * 入力値のデータチェックを行い、ユーザー情報の登録を行う.
	 * @param userInfoRegisterFormDetail , model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "submit", method = RequestMethod.POST)
	public String UserInfoRegister(
			@ModelAttribute("userInfoRegisterFormDetail") UserInfoRegisterFormDetail userInfoRegisterFormDetail,
			Model model) {

		userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegister(userInfoRegisterFormDetail);
		model.addAttribute("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		return "userInfoRegister";

	}

}
