package com.example.demo.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoUpdateService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoUpdateServiceImpl implements UserInfoUpdateService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	@Autowired
	StringUtils stringUtils;

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdateInit(String id) {

		//データの取得
		UserInfoUpdateFormDetail userInfoUpdateFormDetail = userInfoMapper.selectUserInfoForUpdate(id);

		//役職のプルダウンを設定
		userInfoUpdateFormDetail.setSelectPosition(itemReturn());

		//データのトリム
		userInfoUpdateFormDetail = UserInfoUpdateFormDetailAllTrim(userInfoUpdateFormDetail);

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {

		//いろいろ初期化
		userInfoUpdateFormDetail = formReset(userInfoUpdateFormDetail);

		//入力データのトリム
		userInfoUpdateFormDetail = UserInfoUpdateFormDetailAllTrim(userInfoUpdateFormDetail);

		/*データチェック*/
		//氏名（必須チェック）
		if (stringUtils.isEmpty(userInfoUpdateFormDetail.getName())) {
			userInfoUpdateFormDetail.setNameErrFlg(true);
			userInfoUpdateFormDetail.setNameErrMsg("氏名" + ConstantsMsg.ERR_MSG_NULL);
		}

		//役職（必須チェック）
		if (stringUtils.isEmpty(userInfoUpdateFormDetail.getPosition())) {
			userInfoUpdateFormDetail.setPositionErrFlg(true);
			userInfoUpdateFormDetail.setPositionErrMsg("役職" + ConstantsMsg.ERR_MSG_NULL);
		}

		//基本給（必須チェック＋数値チェック）
		if (stringUtils.isEmpty(userInfoUpdateFormDetail.getBasicSalaryStr())) {
			userInfoUpdateFormDetail.setBasicSalaryErrFlg(true);
			userInfoUpdateFormDetail.setBasicSalaryErrMsg("基本給" + ConstantsMsg.ERR_MSG_NULL);
		} else if (!(stringUtils.isNum(userInfoUpdateFormDetail.getBasicSalaryStr()))) {
			userInfoUpdateFormDetail.setBasicSalaryErrFlg(true);
			userInfoUpdateFormDetail.setBasicSalaryErrMsg("基本給" + ConstantsMsg.ERR_MSG_NOT_NUM);
		}

		//いずれかの項目でエラーがあった場合、返却する
		if (userInfoUpdateFormDetail.isNameErrFlg() == true
				|| userInfoUpdateFormDetail.isPositionErrFlg() == true
				|| userInfoUpdateFormDetail.isBasicSalaryErrFlg() == true) {
			return userInfoUpdateFormDetail;
		}

		//更新日の設定
		userInfoUpdateFormDetail.setUpdatedDate(Date.valueOf(stringUtils.getNowDate()));
		//基本給の設定
		userInfoUpdateFormDetail.setBasicSalary(Integer.parseInt(userInfoUpdateFormDetail.getBasicSalaryStr()));

		//DB比較したい

		//更新
		userInfoMapper.updateUserInfo(userInfoUpdateFormDetail);
		salaryInfoMapper.updateSalaryInfo(userInfoUpdateFormDetail);

		userInfoUpdateFormDetail.setMessage(ConstantsMsg.MSG_UPDATE_OK);

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdateFormDetailAllTrim(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {
		//トリム
		if (!(stringUtils.isEmpty(stringUtils.trim(userInfoUpdateFormDetail.getId())))) {
			userInfoUpdateFormDetail.setId(userInfoUpdateFormDetail.getId());
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getNameReading()))) {
			userInfoUpdateFormDetail.setNameReading(stringUtils.trim(userInfoUpdateFormDetail.getNameReading()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getName()))) {
			userInfoUpdateFormDetail.setName(stringUtils.trim(userInfoUpdateFormDetail.getName()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getMailAddress()))) {
			userInfoUpdateFormDetail.setMailAddress(stringUtils.trim(userInfoUpdateFormDetail.getMailAddress()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getPassword()))) {
			userInfoUpdateFormDetail.setPassword(stringUtils.trim(userInfoUpdateFormDetail.getPassword()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getPosition()))) {
			userInfoUpdateFormDetail.setPosition(stringUtils.trim(userInfoUpdateFormDetail.getPosition()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getUpdater()))) {
			userInfoUpdateFormDetail.setUpdater(stringUtils.trim(userInfoUpdateFormDetail.getUpdater()));
		}
		return userInfoUpdateFormDetail;
	}

	@Override
	public List<String> itemReturn() {
		List<String> tmpList = new ArrayList<String>();

		tmpList.add("Administrater");
		tmpList.add("Member");

		return tmpList;
	}

	public UserInfoUpdateFormDetail formReset(UserInfoUpdateFormDetail form) {
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
