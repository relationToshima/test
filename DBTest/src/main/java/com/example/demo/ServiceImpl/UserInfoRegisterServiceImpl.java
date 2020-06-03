package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.service.UserInfoRegisterService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoRegisterServiceImpl implements UserInfoRegisterService {

	@Autowired
	StringUtils stringUtils;
	@Autowired
	InsertToUserInfoServiceImpl insertToUserInfoServiceImpl;

	@Override
	public UserInfoRegisterFormDetail UserInfoRegister(UserInfoRegisterFormDetail form) {

		/*エラーフラグ　エラーメッセージ　メッセージ　初期化*/
		form = FormReset(form);

		/*入力データのトリム*/
		form.setInputName(stringUtils.trim(form.getInputName()));
		form.setInputPosition(stringUtils.trim(form.getInputPosition()));
		form.setInputBasicSalary(stringUtils.trim(form.getInputBasicSalary()));

		/*データチェック*/
		//氏名（必須チェック）
		if (stringUtils.isEmpty(form.getInputName())) {
			form.setNameErrFlg(true);
			form.setNameErrMsg("氏名" + ConstantsMsg.ERR_MSG_NULL);
		}

		//役職（必須チェック）
		if (stringUtils.isEmpty(form.getInputPosition())) {
			form.setPositionErrFlg(true);
			form.setPositionErrMsg("役職" + ConstantsMsg.ERR_MSG_NULL);
		}

		//基本給（必須チェック＋数値チェック）
		if (stringUtils.isEmpty(form.getInputBasicSalary())) {
			form.setBasicSalaryErrFlg(true);
			form.setBasicSalaryErrMsg("基本給" + ConstantsMsg.ERR_MSG_NULL);
		} else if (!(stringUtils.isNum(form.getInputBasicSalary()))) {
			form.setBasicSalaryErrFlg(true);
			form.setBasicSalaryErrMsg("基本給" + ConstantsMsg.ERR_MSG_NOT_NUM);
		}

		//いずれかの項目でエラーが合った場合、返却する
		if (form.isNameErrFlg() == true
				|| form.isPositionErrFlg() == true
				|| form.isBasicSalaryErrFlg() == true) {
			return form;
		}

		//データのDB登録
		UserInfo userInfoToInsert = new UserInfo();
		userInfoToInsert.setName(form.getInputName());

		SalaryInfo salaryInfoToInsert = new SalaryInfo();
		salaryInfoToInsert.setPosition(form.getInputPosition());
		salaryInfoToInsert.setBasicSalary(Integer.parseInt(form.getInputBasicSalary()));

		//登録結果の設定
		boolean result;
		result = insertToUserInfoServiceImpl.UserInfoInsert(userInfoToInsert, salaryInfoToInsert);

		if (result == true) {
			form.setInputName("");
			form.setInputPosition("");
			form.setInputBasicSalary("");
			form.setMessage(ConstantsMsg.MSG_OK);
		} else {
			form.setMessage(ConstantsMsg.MSG_NG);
		}

		return form;
	}

	/**
	 * FormResetメソッド
	 * UserInfoRegisterFormDetailの下記の値を初期化する。
	 * 各項目のエラーフラグ
	 * 各項目のエラーメッセージ
	 * DB登録の結果メッセージ.
	 * @param form UserInfoRegisterFormDetail
	 * @return 初期化後の UserInfoRegisterFormDetail
	 */
	private UserInfoRegisterFormDetail FormReset(UserInfoRegisterFormDetail form) {
		form.setNameErrFlg(false);
		form.setNameErrMsg("");
		form.setPositionErrFlg(false);
		form.setPositionErrMsg("");
		form.setBasicSalaryErrFlg(false);
		form.setBasicSalaryErrMsg("");
		form.setMessage("");
		return form;
	}

}