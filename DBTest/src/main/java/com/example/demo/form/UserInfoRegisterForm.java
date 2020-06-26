package com.example.demo.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.myValidation.MyNotBlank;
import com.example.demo.myValidation.UnUsed;

import lombok.Data;

/**
 * ユーザー情報登録画面　DB項目
 * */
@Data
public class UserInfoRegisterForm {

	/** フリガナ **/
	String inputNameReading;

	/** 氏名 **/
	@MyNotBlank(message = "氏名は、必須入力です。")
	String inputName;

	/** メールアドレス **/
	@MyNotBlank(message = "メールアドレスは、必須入力です。")
	@UnUsed(message = "すでに使用されているメールアドレスです。違うアドレスを指定してください。")
	String inputMailAddress;

	/** パスワード **/
	@MyNotBlank(message = "パスワードは、必須入力です。")
	String inputPassword;

	/** 役職 **/
	@MyNotBlank(message = "役職は、必須入力です。")
	String inputPosition;

	/** 基本給 **/
	@MyNotBlank(message = "基本給は、必須入力です。")
	@Pattern(regexp = "^[0-9０-９]+$", message = "基本給は、数値項目です。")
	@Size(min = 1, max = 9, message = "基本給は、１桁以上１０桁未満で入力してください。")
	String inputBasicSalary;

	/** ユーザー画像 **/
	MultipartFile image;

	/** ユーザー画像 アップロードデータ（画像名） **/
	String imageName;

	/** ユーザー画像　アップロードデータ（画像データ） **/
	byte[] imageData;

	/** ユーザー画像　表示用 **/
	String imageOutput;

}
