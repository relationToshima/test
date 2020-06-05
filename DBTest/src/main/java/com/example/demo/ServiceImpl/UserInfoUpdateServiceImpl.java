package com.example.demo.ServiceImpl;

import java.sql.Date;

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
	public UserInfoUpdateFormDetail UserInfoSelectForUpdate(String id) {
		//データの取得
		UserInfoUpdateFormDetail userInfoUpdateFormDetail = userInfoMapper.selectUserInfoForUpdate(id);

		userInfoUpdateFormDetail = UserInfoUpdateFormDetailAllTrim(userInfoUpdateFormDetail);

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {

		//入力データのトリム
		userInfoUpdateFormDetail = UserInfoUpdateFormDetailAllTrim(userInfoUpdateFormDetail);

		/*データチェック*/

		//更新日の設定
		userInfoUpdateFormDetail.setUpdatedDate(Date.valueOf(stringUtils.getNowDate()));

		//DB比較したい

		try {

			//更新
			userInfoMapper.updateUserInfo(userInfoUpdateFormDetail);
			salaryInfoMapper.updateSalaryInfo(userInfoUpdateFormDetail);

			userInfoUpdateFormDetail.setMessage(ConstantsMsg.MSG_UPDATE_OK);

		} catch (Exception e) {

			userInfoUpdateFormDetail.setMessage(ConstantsMsg.MSG_UPDATE_NG);

		}

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

}
