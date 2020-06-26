package com.example.demo.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginUser {

	/** 社員番号 **/
	String id;
	/** 氏名 **/
	String name;
	/** メールアドレス **/
	String mailAddress;
	/** パスワード **/
	String password;
	/** 役職 **/
	String position;

}
