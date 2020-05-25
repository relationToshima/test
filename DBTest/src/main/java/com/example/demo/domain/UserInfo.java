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
	    private String namereading;
	    /** 氏名 */
	    private String name;
	    /** メールアドレス */
	    private String mailaddress;
	    /** パスワード */
	    private String password;
	    /** 認証失敗回数 */
	    private String failuretimes;
	    /** 最終ログイン日 */
	    private String lastlogindate;
	    /** 登録者 */
	    private String registrant;
	    /** 登録日 */
	    private String registrationdate;
	    /** 更新者 */
	    private String updater;
	    /** 更新日 */
	    private String updateddate;
}