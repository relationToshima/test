package com.example.demo.formDetail;

import java.util.List;

import com.example.demo.form.UserInfoRegisterForm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoRegisterFormDetail extends UserInfoRegisterForm {

	/** メッセージ **/
	String message;

	/** チェック内容 **/

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

	public UserInfoRegisterFormDetail() {
		this.setNameErrFlg(false);
		this.setPositionErrFlg(false);
		this.setBasicSalaryErrFlg(false);
	}

}
