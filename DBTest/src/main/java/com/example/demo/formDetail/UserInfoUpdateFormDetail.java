package com.example.demo.formDetail;

import java.util.List;

import com.example.demo.form.UserInfoUpdateForm;

import lombok.Data;

@Data
public class UserInfoUpdateFormDetail extends UserInfoUpdateForm {

	/** DB用基本給（int型） **/
	int basicSalary;

	/** メッセージ **/
	String message;

	/** 役職プルダウン **/
	List<String> selectPosition;

	/** 氏名　エラーメッセージ **/
	String nameErrMsg;
	/** 役職　エラーメッセージ **/
	String positionErrMsg;
	/** 基本給　エラーメッセージ **/
	String basicSalaryErrMsg;

	/** 氏名　エラーステータス **/
	boolean nameErrFlg;
	/** 役職　エラーステータス **/
	boolean positionErrFlg;
	/** 基本給　エラーステータス **/
	boolean basicSalaryErrFlg;

	public UserInfoUpdateFormDetail() {
		this.setNameErrFlg(false);
		this.setPositionErrFlg(false);
		this.setBasicSalaryErrFlg(false);
	}

}
