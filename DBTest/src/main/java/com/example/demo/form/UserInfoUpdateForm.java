package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class UserInfoUpdateForm {
	/** 社員番号 */
	String id;
	/** フリガナ */
	String nameReading;
	/** 氏名 */
	String name;
	/** メールアドレス */
	String mailAddress;
	/** パスワード */
	String password;
	/** 役職 **/
	String position;
	/** 基本給 **/
	int basicSalary;
	/** 更新者 **/
	String updater;
	/** 更新日 **/
	Date updatedDate;
}
