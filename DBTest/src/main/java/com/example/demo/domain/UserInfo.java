package com.example.demo.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * ユーザー情報Bean
 */
@Component
@Data
public class UserInfo{
		/** 社員番号 */
	    private String id;
	    /** フリガナ */
	    private String nameReading;
	    /** 氏名 */
	    private String name;
	    /** メールアドレス */
	    private String mailAddress;
	    /** パスワード */
	    private String password;
	    /** 認証失敗回数 */
	    private String failureTimes;
	    /** 最終ログイン日 */
	    private String lastLoginDate;
	    /** 登録者 */
	    private String registrant;
	    /** 登録日 */
	    private String registrationDate;
	    /** 更新者 */
	    private String updater;
	    /** 更新日 */
	    private String updatedDate;
}