package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ConstantsData;
import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.form.UserInfoSearchForm;
import com.example.demo.formDetail.UserInfoSearchFormDetail;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoSearchService;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoSearchServiceImpl implements UserInfoSearchService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	StringUtils stringUtils;

	@Override
	public UserInfoSearchFormDetail UserInfoSearchInit() {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();

		//データ項目を取得
		userInfoSearchFormDetail.setUserInfoSearchFormList(userInfoMapper.selectUserInfoForSearch());

		//Date型をString型に変換
		for (UserInfoSearchForm data : userInfoSearchFormDetail.getUserInfoSearchFormList()) {
			if (data.getRegistrationDate() != null) {
				data.setRegistrationDateString(data.getRegistrationDate().toString());
			}
			if (data.getUpdatedDate() != null) {
				data.setUpdatedDateString(data.getUpdatedDate().toString());
			}
		}

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
	public UserInfoSearchFormDetail SearchOn(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//必要データの抽出
		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		String columns = userInfoSearchFormDetail.getSelectColumnsForSearchStr();
		String key = userInfoSearchFormDetail.getSearchKey();

		//未設定
		if (stringUtils.isEmpty(columns) || stringUtils.isEmpty(key)) {
			userInfoSearchFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);

			//プルダウン対処用
			userInfoSearchFormDetail
					.setSelectColumnsForSearch(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
			userInfoSearchFormDetail
					.setSelectColumnsForSort1(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
			userInfoSearchFormDetail
					.setSelectSortOrder1(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

			return userInfoSearchFormDetail;
		}

		int count = dataList.size();

		//一旦すべて表示off & 検索列の抽出
		List<String> tmpList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			dataList.get(i).setOutputFlg(ConstantsData.OUTPUT_OFF);
			switch (columns) {
			case "社員番号":
				tmpList.add(dataList.get(i).getId());
				break;
			case "フリガナ":
				tmpList.add(dataList.get(i).getNameReading());
				break;
			case "氏名":
				tmpList.add(dataList.get(i).getName());
				break;
			case "メールアドレス":
				tmpList.add(dataList.get(i).getMailAddress());
				break;
			case "認証失敗回数":
				tmpList.add(dataList.get(i).getFailureTimes());
				break;
			case "最終ログイン日":
				tmpList.add(dataList.get(i).getLastLoginDate());
				break;
			case "登録者":
				tmpList.add(dataList.get(i).getRegistrant());
				break;
			case "登録日":
				tmpList.add(dataList.get(i).getRegistrationDateString());
				break;
			case "更新者":
				tmpList.add(dataList.get(i).getUpdater());
				break;
			case "更新日":
				tmpList.add(dataList.get(i).getUpdatedDateString());
				break;
			case "役職":
				tmpList.add(dataList.get(i).getPosition());
				break;
			}
		}

		//検索
		for (int i = 0; i < count; i++) {
			if (tmpList.get(i).contains(key)) {
				dataList.get(i).setOutputFlg(ConstantsData.OUTPUT_ON);
			}
		}

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		return userInfoSearchFormDetail;
	}

	@Override
	public UserInfoSearchFormDetail SearchOff(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		int count = dataList.size();

		//すべて表示on
		for (int i = 0; i < count; i++) {
			dataList.get(i).setOutputFlg(ConstantsData.OUTPUT_ON);
		}

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		//列選択のプルダウンと検索キーのテキストボックスを初期化
		userInfoSearchFormDetail.setSelectColumnsForSearchStr("");
		userInfoSearchFormDetail.setSearchKey("");

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

		return userInfoSearchFormDetail;
	}

	@Override
	public UserInfoSearchFormDetail SortOn(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//必要データ抽出
		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		String columns = userInfoSearchFormDetail.getSelectColumnsForSort1Str();
		String sortOrder = userInfoSearchFormDetail.getSelectSortOrder1Str();

		//未設定
		if (stringUtils.isEmpty(columns) || stringUtils.isEmpty(sortOrder)) {

			userInfoSearchFormDetail.setMessage(ConstantsMsg.MSG_NOT_SELECT);

			//プルダウン対処用
			userInfoSearchFormDetail
					.setSelectColumnsForSearch(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
			userInfoSearchFormDetail
					.setSelectColumnsForSort1(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
			userInfoSearchFormDetail
					.setSelectSortOrder1(
							stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

			return userInfoSearchFormDetail;
		}

		int count = dataList.size();
		UserInfoSearchForm tmp;
		String tmpStr;

		//並べ替えの基準列を別配列に準備
		List<String> tmpList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			switch (columns) {
			case "社員番号":
				tmpList.add(dataList.get(i).getId());
				break;
			case "フリガナ":

				tmpList.add(dataList.get(i).getNameReading());
				break;
			case "氏名":

				tmpList.add(dataList.get(i).getName());
				break;
			case "メールアドレス":
				tmpList.add(dataList.get(i).getMailAddress());
				break;
			case "認証失敗回数":

				tmpList.add(dataList.get(i).getFailureTimes());
				break;
			case "最終ログイン日":

				tmpList.add(dataList.get(i).getLastLoginDate());
				break;
			case "登録者":

				tmpList.add(dataList.get(i).getRegistrant());
				break;
			case "登録日":

				tmpList.add(dataList.get(i).getRegistrationDateString());
				break;
			case "更新者":

				tmpList.add(dataList.get(i).getUpdater());
				break;
			case "更新日":

				tmpList.add(dataList.get(i).getUpdatedDateString());
				break;
			case "役職":

				tmpList.add(dataList.get(i).getPosition());
				break;
			}
		}

		//実行
		if (sortOrder.equals("昇順")) {
			for (int i = 0; i < count - 1; i++) {
				for (int j = i; j < count; j++) {
					if (tmpList.get(i).compareTo(tmpList.get(j)) > 0) {

						//並べ替え基準列の入れ替え
						tmpStr = tmpList.get(i);
						tmpList.set(i, tmpList.get(j));
						tmpList.set(j, tmpStr);

						//実データの入れ替え
						tmp = dataList.get(i);
						dataList.set(i, dataList.get(j));
						dataList.set(j, tmp);
					}
				}
			}

		} else {
			for (int i = 0; i < count - 1; i++) {
				for (int j = i; j < count; j++) {
					if (tmpList.get(i).compareTo(tmpList.get(j)) < 0) {

						//並べ替え基準列の入れ替え
						tmpStr = tmpList.get(i);
						tmpList.set(i, tmpList.get(j));
						tmpList.set(j, tmpStr);

						//実データの入れ替え
						tmp = dataList.get(i);
						dataList.set(i, dataList.get(j));
						dataList.set(j, tmp);
					}
				}
			}
		}

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		//プルダウン対処用
		userInfoSearchFormDetail.setSelectColumnsForSearch(
				stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail.setSelectColumnsForSort1(
				stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

		return userInfoSearchFormDetail;
	}

	@Override
	public UserInfoSearchFormDetail SortOff(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//必要データ抽出
		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();

		int count = dataList.size();
		UserInfoSearchForm tmp;

		for (int i = 0; i < count - 1; i++) {
			for (int j = i; j < count; j++) {
				if (dataList.get(i).getId().compareTo(dataList.get(j).getId()) > 0) {
					tmp = dataList.get(i);
					dataList.set(i, dataList.get(j));
					dataList.set(j, tmp);
				}
			}
		}

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		//列選択のプルダウンと並べ替え順のプルダウンを初期化
		userInfoSearchFormDetail.setSelectColumnsForSort1Str("");
		userInfoSearchFormDetail.setSelectSortOrder1Str("");

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(
						stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(stringUtils.itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

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
