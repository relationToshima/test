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

	/** 役職プルダウン **/
	List<String> selectPosition;

	/** 画像エラーメッセージ **/
	String imageErrMessage;

}
