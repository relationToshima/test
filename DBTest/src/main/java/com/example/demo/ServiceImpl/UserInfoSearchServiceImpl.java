package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.formDetail.UserInfoSearchFormDetail;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoSearchService;

@Service
public class UserInfoSearchServiceImpl implements UserInfoSearchService {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public UserInfoSearchFormDetail UserInfoSearchInit() {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();

		//データ項目を取得
		userInfoSearchFormDetail.setUserInfoSearchFormList(userInfoMapper.selectUserInfoForSearch());

		/*プルダウン項目を設定*/
		//検索列
		userInfoSearchFormDetail.setSelectColumnsForSearch(itemReturn(0));

		//第１キー並べ替え列
		userInfoSearchFormDetail.setSelectColumnsForSort1(itemReturn(0));
		//第１キー並べ替え順
		userInfoSearchFormDetail.setSelectSortOrder1(itemReturn(1));

		//第２キー並べ替え列
		userInfoSearchFormDetail.setSelectColumnsForSort2(itemReturn(0));
		//第２キー並べ替え順
		userInfoSearchFormDetail.setSelectSortOrder2(itemReturn(1));

		return userInfoSearchFormDetail;
	}

	@Override
	public List<String> itemReturn(int i) {
		List<String> tmpList = new ArrayList<String>();
		if (i == 0) {
			tmpList.add("社員番号");
			tmpList.add("フリガナ");
			tmpList.add("氏名");
			tmpList.add("メールアドレス");
			tmpList.add("認証失敗回数");
			tmpList.add("最終ログイン日");
			tmpList.add("登録者");
			tmpList.add("登録日");
			tmpList.add("更新者");
			tmpList.add("更新日");
			tmpList.add("役職");
		} else if (i == 1) {
			tmpList.add("昇順");
			tmpList.add("降順");
		}

		return tmpList;
	}
}
