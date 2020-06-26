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

	/** 画像エラーメッセージ **/
	String imageErrMessage;
}
