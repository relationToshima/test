package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;
import com.example.demo.ServiceImpl.UserInfoDeleteServiceImpl;
import com.example.demo.ServiceImpl.UserInfoRegisterServiceImpl;
import com.example.demo.ServiceImpl.UserInfoSearchServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateListServiceImpl;
import com.example.demo.domain.OfficeWorker;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.formDetail.UserInfoSearchFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;

@Controller
public class topController {

	@Autowired
	UserInfoRegisterServiceImpl userInfoRegisterServiceImpl;
	@Autowired
	SalaryOutputServiceImpl salaryOutputServiceImpl;
	@Autowired
	UserInfoUpdateListServiceImpl userInfoUpdateListServiceImpl;
	@Autowired
	UserInfoDeleteServiceImpl userInfoDeleteServiceImpl;
	@Autowired
	UserInfoSearchServiceImpl userInfoSearchServiceImpl;

	/**
	 * topメソッド
	 * 社員情報管理トップ画面を表示する.
	 * @return
	 */
	@RequestMapping(value = "/top", method = RequestMethod.POST)
	public ModelAndView TopInit(ModelAndView mav) {
		mav.setViewName("top");
		return mav;
	}

	/**
	 * TopGoToRegisterメソッド
	 * 新規社員登録画面に遷移する.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/top", params = "register", method = RequestMethod.POST)
	public ModelAndView TopGoToRegister(ModelAndView mav) {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = userInfoRegisterServiceImpl.UserInfoRegisterInit();
		mav.addObject("userInfoRegisterFormDetail", userInfoRegisterFormDetail);
		mav.setViewName("userInfoRegister");
		return mav;
	}

	/**
	 * TopGoToSalaryListメソッド
	 * 給与情報一覧画面に遷移する.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/top", params = "salaryList", method = RequestMethod.POST)
	public ModelAndView TopGoToSalaryList(ModelAndView mav) {
		/*従業員名・役職・基本給・基本給＋役職手当を取得*/
		List<OfficeWorker> officeWorkerList = salaryOutputServiceImpl.SalaryCalculationOutput();
		mav.addObject("data", officeWorkerList);
		mav.setViewName("salaryInfoList");
		return mav;
	}

	/**
	 * TopGoToUpdateメソッド
	 * 社員情報更新画面に遷移する.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/top", params = "update", method = RequestMethod.POST)
	public ModelAndView TopGoToUpdate(ModelAndView mav) {
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail
				.setUserInfoUpdateListFormList(userInfoUpdateListServiceImpl.UserInfoSelectForUpdateList());
		mav.addObject("userInfoUpdateListFormDetail", userInfoUpdateListFormDetail);
		mav.setViewName("UserInfoUpdateList");
		return mav;
	}

	/**
	 * TopGoToDeleteメソッド
	 * 社員情報削除画面に遷移する.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/top", params = "delete", method = RequestMethod.POST)
	public ModelAndView TopGoToDelete(ModelAndView mav) {
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		userInfoDeleteFormDetail.setUserInfoDeleteFormList(userInfoDeleteServiceImpl.UserInfoDeleteInit());
		mav.addObject("userInfoDeleteFormDetail", userInfoDeleteFormDetail);
		mav.setViewName("userInfoDelete");
		return mav;
	}

	/**
	 * TopGoToSearchメソッド
	 * 社員情報検索画面に遷移する.
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/top", params = "search", method = RequestMethod.POST)
	public ModelAndView TopGoToSearch(ModelAndView mav) {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();
		userInfoSearchFormDetail = userInfoSearchServiceImpl.UserInfoSearchInit();
		mav.addObject("userInfoSearchFormDetail", userInfoSearchFormDetail);
		mav.setViewName("userInfoSearch");
		return mav;
	}

}