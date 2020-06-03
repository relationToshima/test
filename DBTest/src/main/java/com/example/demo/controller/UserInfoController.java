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
import com.example.demo.domain.OfficeWorker;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;

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
	 * UserInfoDeleteメソッド
	 * ユーザー情報削除画面を表示する.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoDelete", method = RequestMethod.GET)
	public String UserInfoDeleteInit(Model model) {
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoDeleteServiceImpl.userInfoSelectForDelete());
		model.addAttribute("UserInfoDeleteFormDetail", userInfoDeleteFormDetail);
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
			@ModelAttribute("UserInfoDeleteFormDetail") UserInfoDeleteFormDetail userInfoDeleteFormDetail,
			Model model) {

		//確認ポップアップ表示する

		userInfoDeleteFormDetail = userInfoDeleteServiceImpl.userInfoDelete(userInfoDeleteFormDetail.getCheckBox());
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
	 * UserInfoRegisterメソッド
	 * ユーザー情報登録画面の初期表示を行う.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", method = RequestMethod.GET)
	public String UserInfoRegisterInit(Model model) {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
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

	/**
	 * backメソッド
	 * 給与情報一覧画面に戻る.
	 * @return
	 */
	@RequestMapping(value = "/UserInfoRegister", params = "back", method = RequestMethod.GET)
	public String back() {
		System.out.println("aaa");
		return "salaryInfoList";

	}

}
