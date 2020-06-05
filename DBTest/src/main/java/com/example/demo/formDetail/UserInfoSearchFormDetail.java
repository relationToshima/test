package com.example.demo.formDetail;

import java.util.List;

import com.example.demo.form.UserInfoSearchForm;

import lombok.Data;

@Data
public class UserInfoSearchFormDetail {

	/** 画面項目のリスト **/
	List<UserInfoSearchForm> userInfoSearchFormList;

	/** メッセージ **/
	String message;

	/** 検索列プルダウン **/
	List<String> selectColumnsForSearch;

	/** 検索列プルダウン選択値 **/
	String selectColumnsForSearchStr;

	/** 検索キー **/
	String searchKey;

	/** 並べ替え列第１キープルダウン **/
	List<String> selectColumnsForSort1;

	/** 並べ替え列第１キープルダウン選択値 **/
	String selectColumnsForSort1Str;

	/** 並べ替え順第１キープルダウン **/
	List<String> selectSortOrder1;

	/** 並べ替え順第１キープルダウン選択値 **/
	String selectSortOrder1Str;

	/** 並べ替え列第２キープルダウン **/
	List<String> selectColumnsForSort2;

	/** 並べ替え列第２キープルダウン選択値 **/
	String selectColumnsForSort2Str;

	/** 並べ替え順第２キープルダウン **/
	List<String> selectSortOrder2;

	/** 並べ替え順第２キープルダウン選択値 **/
	String selectSortOrder2Str;
}
