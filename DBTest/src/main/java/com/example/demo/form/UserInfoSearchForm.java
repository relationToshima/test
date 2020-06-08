package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class UserInfoSearchForm {

	/** 社員番号 **/
	String id;
	/** フリガナ **/
	String nameReading;
	/** 氏名 **/
	String name;
	/** メールアドレス **/
	String mailAddress;
	/** 認証失敗回数 **/
	String failureTimes;
	/** 最終ログイン日 **/
	String lastLoginDate;
	/** 登録者 **/
	String registrant;
	/** 登録日 **/
	Date registrationDate;
	/** 登録日(String) **/
	String registrationDateString;
	/** 更新者 **/
	String updater;
	/** 更新日 **/
	Date updatedDate;
	/** 更新日(String) **/
	String updatedDateString;
	/** 役職 **/
	String position;

	/** 表示フラグ(0:非表示/1:表示) **/
	String outputFlg;

	UserInfoSearchForm() {
		outputFlg = "1";
	}
}
