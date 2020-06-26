package com.example.demo.form;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.myValidation.MyNotBlank;

import lombok.Data;

@Data
public class UserInfoUpdateForm {

	/** 社員番号 */
	String id;

	/** フリガナ */
	String nameReading;

	/** 氏名 */
	@MyNotBlank(message = "名前は、必須入力です。")
	String name;

	/** メールアドレス */
	@MyNotBlank(message = "メールアドレスは、必須入力です。")
	String mailAddress;

	/** パスワード */
	@MyNotBlank(message = "パスワードは、必須入力です。")
	String password;

	/** 役職 **/
	@MyNotBlank(message = "役職は、必須入力です。")
	String position;

	/** 基本給（String型） **/
	@MyNotBlank(message = "基本給は、必須入力です。")
	@Pattern(regexp = "^[0-9０-９]+$", message = "基本給は、数値項目です。")
	@Size(min = 1, max = 9, message = "基本給は、１桁以上１０桁未満で入力してください。")
	String basicSalaryStr;

	/** 更新者 **/
	String updater;

	/** 更新日 **/
	Date updatedDate;

	/** ユーザー画像 **/
	MultipartFile image;

	/** ユーザー画像 アップロードデータ（画像名） **/
	String imageName;

	/** ユーザー画像　アップロードデータ（画像データ） **/
	byte[] imageData;

	/** ユーザー画像　表示用 **/
	String imageOutput;
}
