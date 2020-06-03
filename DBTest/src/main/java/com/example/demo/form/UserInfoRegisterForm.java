package com.example.demo.form;

import lombok.Data;

/**
 * ユーザー情報登録画面　DB項目
 * */
@Data
public class UserInfoRegisterForm {

	/** 氏名 **/
	String inputName;
	/** 役職 **/
	String inputPosition;
	/** 基本給 **/
	String inputBasicSalary;

}
