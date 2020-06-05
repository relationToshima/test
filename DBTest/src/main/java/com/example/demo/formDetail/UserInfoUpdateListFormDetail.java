package com.example.demo.formDetail;

import java.util.List;

import com.example.demo.form.UserInfoUpdateListForm;

import lombok.Data;

@Data
public class UserInfoUpdateListFormDetail {

	/** 画面項目のリスト **/
	List<UserInfoUpdateListForm> userInfoUpdateListFormList;
	/** メッセージ **/
	String message;
	/** ラジオボタン **/
	String radio;
}
