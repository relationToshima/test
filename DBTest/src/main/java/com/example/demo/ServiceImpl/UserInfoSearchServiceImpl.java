package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ConstantsData;
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
	@Autowired
	ConstantsData constantsData;

	@Override
	public UserInfoSearchFormDetail UserInfoSearchInit() {
		UserInfoSearchFormDetail userInfoSearchFormDetail = new UserInfoSearchFormDetail();

		//データ項目を取得
		userInfoSearchFormDetail.setUserInfoSearchFormList(userInfoMapper.selectUserInfoForSearch());

		//Date型をString型に変換
		for (UserInfoSearchForm data : userInfoSearchFormDetail.getUserInfoSearchFormList()) {
			data.setRegistrationDateString(String.valueOf(data.getRegistrationDate()));
			data.setUpdatedDateString(String.valueOf(data.getUpdatedDate()));
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
	public UserInfoSearchFormDetail UserInfoSearchSortOn(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//入力状態の確認
		boolean searchFlg = false;
		boolean sort1Flg = false;
		boolean sort2Flg = false;
		if (!(stringUtils.isEmpty(userInfoSearchFormDetail.getSelectColumnsForSearchStr()))
				&& !(stringUtils.isEmpty(userInfoSearchFormDetail.getSearchKey()))) {
			searchFlg = true;
		}
		if (!(stringUtils.isEmpty(userInfoSearchFormDetail.getSelectColumnsForSort1Str()))
				&& !(stringUtils.isEmpty(userInfoSearchFormDetail.getSelectSortOrder1Str()))) {
			sort1Flg = true;
		}
		if (!(stringUtils.isEmpty(userInfoSearchFormDetail.getSelectColumnsForSort2Str()))
				&& !(stringUtils.isEmpty(userInfoSearchFormDetail.getSelectSortOrder2Str()))) {
			sort1Flg = true;
		}

		if (searchFlg == true && sort1Flg == false && sort2Flg == false) {
			//検索のみ実行

		} else if (searchFlg = false && (sort1Flg == true || sort2Flg == true)) {
			//並べ替えのみ実行

		} else if (searchFlg = true && (sort1Flg == true || sort2Flg == true)) {
			//検索と並べ替えともに実行

		} else {
			//未選択

		}

		return userInfoSearchFormDetail;

	}

	@Override
	public UserInfoSearchFormDetail SearchOn(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//必要データの抽出
		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		String columns = userInfoSearchFormDetail.getSelectColumnsForSearchStr();
		String key = userInfoSearchFormDetail.getSearchKey();

		int count = dataList.size();

		//一旦すべて表示off
		for (int i = 0; i < count; i++) {
			dataList.get(i).setOutputFlg(constantsData.OUTPUT_OFF);
		}

		//検索
		for (int i = 0; i < count; i++) {

			switch (columns) {
			case "社員番号":
				if (dataList.get(i).getId().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "フリガナ":
				if (dataList.get(i).getNameReading().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "氏名":
				if (dataList.get(i).getName().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "メールアドレス":
				if (dataList.get(i).getMailAddress().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "認証失敗回数":
				if (dataList.get(i).getFailureTimes().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "最終ログイン日":
				if (dataList.get(i).getLastLoginDate().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "登録者":
				if (dataList.get(i).getRegistrant().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "登録日":
				if (dataList.get(i).getRegistrationDateString().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "更新者":
				if (dataList.get(i).getUpdater().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "更新日":
				if (dataList.get(i).getUpdatedDateString().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			case "役職":
				if (dataList.get(i).getPosition().contains(key)) {
					dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
				}
				break;
			}

		}

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		return userInfoSearchFormDetail;
	}

	@Override
	public UserInfoSearchFormDetail SearchOff(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		int count = dataList.size();

		//すべて表示on
		for (int i = 0; i < count; i++) {
			dataList.get(i).setOutputFlg(constantsData.OUTPUT_ON);
		}

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		//列選択のプルダウンと検索キーのテキストボックスを初期化
		userInfoSearchFormDetail.setSelectColumnsForSearchStr("");
		userInfoSearchFormDetail.setSearchKey("");

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

		return userInfoSearchFormDetail;
	}

	@Override
	public UserInfoSearchFormDetail SortOn(UserInfoSearchFormDetail userInfoSearchFormDetail) {

		//必要データ抽出
		List<UserInfoSearchForm> dataList = userInfoSearchFormDetail.getUserInfoSearchFormList();
		String columns = userInfoSearchFormDetail.getSelectColumnsForSort1Str();
		String sortOrder = userInfoSearchFormDetail.getSelectSortOrder1Str();

		int count = dataList.size();
		UserInfoSearchForm tmp;

		boolean sortExecutionFlg;

		//並べ替え
		for (int i = 0; i < count - 1; i++) {
			for (int j = i; j < count; j++) {

				sortExecutionFlg = false;

				switch (columns) {
				case "社員番号":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getId().compareTo(dataList.get(j).getId()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getId().compareTo(dataList.get(j).getId()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "フリガナ":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getNameReading().compareTo(dataList.get(j).getNameReading()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getNameReading().compareTo(dataList.get(j).getNameReading()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "氏名":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getName().compareTo(dataList.get(j).getName()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getName().compareTo(dataList.get(j).getName()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "メールアドレス":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getMailAddress().compareTo(dataList.get(j).getMailAddress()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getMailAddress().compareTo(dataList.get(j).getMailAddress()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "認証失敗回数":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getFailureTimes().compareTo(dataList.get(j).getFailureTimes()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getFailureTimes().compareTo(dataList.get(j).getFailureTimes()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "最終ログイン日":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getLastLoginDate().compareTo(dataList.get(j).getLastLoginDate()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getLastLoginDate().compareTo(dataList.get(j).getLastLoginDate()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "登録者":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getRegistrant().compareTo(dataList.get(j).getRegistrant()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getRegistrant().compareTo(dataList.get(j).getRegistrant()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "登録日":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getRegistrationDateString()
								.compareTo(dataList.get(j).getRegistrationDateString()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getRegistrationDateString()
								.compareTo(dataList.get(j).getRegistrationDateString()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "更新者":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getUpdater().compareTo(dataList.get(j).getUpdater()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getUpdater().compareTo(dataList.get(j).getUpdater()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "更新日":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getUpdatedDateString()
								.compareTo(dataList.get(j).getUpdatedDateString()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getUpdatedDateString()
								.compareTo(dataList.get(j).getUpdatedDateString()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				case "役職":
					if (sortOrder.equals("昇順")) {
						if (dataList.get(i).getPosition().compareTo(dataList.get(j).getPosition()) > 0) {
							sortExecutionFlg = true;
						}
					} else {
						if (dataList.get(i).getPosition().compareTo(dataList.get(j).getPosition()) < 0) {
							sortExecutionFlg = true;
						}
					}
					break;
				}

				if (sortExecutionFlg == true) {
					tmp = dataList.get(i);
					dataList.set(i, dataList.get(j));
					dataList.set(j, tmp);
				}
			}
		}

		userInfoSearchFormDetail.setUserInfoSearchFormList(dataList);

		//プルダウン対処用
		userInfoSearchFormDetail
				.setSelectColumnsForSearch(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

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
				.setSelectColumnsForSearch(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSearch()));
		userInfoSearchFormDetail
				.setSelectColumnsForSort1(itemColumnsShaping(userInfoSearchFormDetail.getSelectColumnsForSort1()));
		userInfoSearchFormDetail
				.setSelectSortOrder1(itemColumnsShaping(userInfoSearchFormDetail.getSelectSortOrder1()));

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

	//なぜかプルダウン用のListに”[”と”]”が含まれるので一時対処用
	public List<String> itemColumnsShaping(List<String> itemColumns) {

		int maxElements = itemColumns.size() - 1;

		itemColumns.set(0, itemColumns.get(0).replace("[", ""));
		itemColumns.set(maxElements, itemColumns.get(maxElements).replace("]", ""));

		return itemColumns;
	}

}
