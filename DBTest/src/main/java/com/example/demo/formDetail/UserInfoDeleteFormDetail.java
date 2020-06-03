package com.example.demo.formDetail;

import java.util.List;

import com.example.demo.form.UserInfoDeleteForm;

import lombok.Data;

@Data
public class UserInfoDeleteFormDetail {

	//画面項目のリスト
	List<UserInfoDeleteForm> userInfoDeleteFormList;
	/** チェックボックス **/
	String[] checkBox;
	/** メッセージ **/
	String message;

}
