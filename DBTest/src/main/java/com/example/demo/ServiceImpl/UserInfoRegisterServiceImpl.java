package com.example.demo.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoRegisterService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoRegisterServiceImpl implements UserInfoRegisterService {

	@Autowired
	StringUtils stringUtils;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;

	@Override
	public UserInfoRegisterFormDetail UserInfoRegisterInit() {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail.setSelectPosition(itemReturn());
		return userInfoRegisterFormDetail;

	}

	@Override
	public UserInfoRegisterFormDetail UserInfoRegister(UserInfoRegisterFormDetail form) {

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
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

		//いずれかの項目でエラーがあった場合、返却する
		if (form.isNameErrFlg() == true
				|| form.isPositionErrFlg() == true
				|| form.isBasicSalaryErrFlg() == true) {
			return form;
		}

		//データのDB登録準備
		UserInfo userInfoToInsert = new UserInfo();
		userInfoToInsert.setName(form.getInputName());

		SalaryInfo salaryInfoToInsert = new SalaryInfo();
		salaryInfoToInsert.setPosition(form.getInputPosition());
		salaryInfoToInsert.setBasicSalary(Integer.parseInt(form.getInputBasicSalary()));

		//登録
		boolean result = true;
		try {
			//IDの採番
			String strId = userInfoMapper.selectMaxId();
			int id = 0;
			if (stringUtils.isEmpty(strId)) {
				id = 1;
			} else {
				id = Integer.parseInt(strId) + 1;
			}
			userInfoToInsert.setId(String.valueOf(String.format("%04d", id)));
			salaryInfoToInsert.setId(userInfoToInsert.getId());
			//登録日の設定
			userInfoToInsert.setRegistrationDate(Date.valueOf(stringUtils.getNowDate()));

			//userInfoのインサート
			userInfoMapper.insertUserInfo(userInfoToInsert);

			//salaryInfoのインサート
			salaryInfoMapper.insertSalaryInfo(salaryInfoToInsert);

		} catch (Exception e) {
			result = false;
		}

		if (result == true) {
			form.setInputName("");
			form.setInputPosition("");
			form.setInputBasicSalary("");
			form.setMessage(ConstantsMsg.MSG_REGISTER_OK);
		} else {
			form.setMessage(ConstantsMsg.MSG_REGISTER_NG);
		}

		return form;
	}

	/**
	 * itemReturnメソッド
	 * プルダウンの値を返却する.
	 * @return
	 */
	public List<String> itemReturn() {
		List<String> tmpList = new ArrayList<String>();

		tmpList.add("Administrater");
		tmpList.add("Member");

		return tmpList;
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
	public UserInfoRegisterFormDetail FormReset(UserInfoRegisterFormDetail form) {
		form.setNameErrFlg(false);
		form.setNameErrMsg("");
		form.setPositionErrFlg(false);
		form.setPositionErrMsg("");
		form.setBasicSalaryErrFlg(false);
		form.setBasicSalaryErrMsg("");
		form.setMessage("");
		form.setSelectPosition(stringUtils.itemColumnsShaping(form.getSelectPosition()));
		return form;
	}

}
