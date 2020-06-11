package com.example.demo;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ServiceImpl.SalaryOutputServiceImpl;
import com.example.demo.ServiceImpl.UserInfoDeleteServiceImpl;
import com.example.demo.ServiceImpl.UserInfoRegisterServiceImpl;
import com.example.demo.ServiceImpl.UserInfoSearchServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateListServiceImpl;
import com.example.demo.ServiceImpl.UserInfoUpdateServiceImpl;
import com.example.demo.constants.ConstantsData;
import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.controller.UserInfoController;
import com.example.demo.domain.OfficeWorker;
import com.example.demo.form.UserInfoDeleteForm;
import com.example.demo.form.UserInfoSearchForm;
import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoDeleteFormDetail;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.formDetail.UserInfoSearchFormDetail;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.myInterface.OfficeWorker.Administrater;
import com.example.demo.myInterface.OfficeWorker.Member;
import com.example.demo.utils.StringUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JunitTester {

	UserInfoController controller = new UserInfoController();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	Administrater mockAdministrater;
	@Mock
	Member mockMember;
	@Mock
	UserInfoMapper mockUserInfoMapper;
	@Mock
	SalaryInfoMapper mockSalaryInfoMapper;
	@Mock
	UserInfoRegisterServiceImpl mockUserInfoRegisterServiceImpl;
	@Mock
	StringUtils mockStringUtils;
	@Mock
	UserInfoSearchServiceImpl mockUserInfoSearchServiceImpl;
	@Mock
	UserInfoUpdateServiceImpl mockUserInfoUpdateServiceImpl;
	@Mock
	UserInfoUpdateListServiceImpl mockUserInfoUpdateListServiceImpl;
	@Mock
	UserInfoDeleteServiceImpl mockUserInfoDeleteServiceImpl;
	@Mock
	SalaryOutputServiceImpl mockSalaryOutputServiceImpl;

	@InjectMocks
	SalaryOutputServiceImpl injectMocksSalaryOutputServiceImpl = new SalaryOutputServiceImpl();
	@InjectMocks
	UserInfoDeleteServiceImpl injectMocksUserInfoDeleteServiceImpl = new UserInfoDeleteServiceImpl();
	@InjectMocks
	UserInfoRegisterServiceImpl injectMocksUserInfoRegisterServiceImpl = new UserInfoRegisterServiceImpl();
	@InjectMocks
	UserInfoSearchServiceImpl injectMocksUserInfoSearchServiceImpl = new UserInfoSearchServiceImpl();
	@InjectMocks
	UserInfoUpdateListServiceImpl injectMocksUserInfoUpdateListServiceImpl = new UserInfoUpdateListServiceImpl();
	@InjectMocks
	UserInfoUpdateServiceImpl injectMocksUserInfoUpdateServiceImpl = new UserInfoUpdateServiceImpl();
	@InjectMocks
	UserInfoController injectMocksUserInfoController = new UserInfoController();

	/***************StringUtils***************/

	StringUtils stringUtils;

	@Before
	public void StringUtils_setup() {

		stringUtils = new StringUtils();
	}

	@Test
	public void StringUtils_getNowDate() {

		//期待値
		String expected = "2020-06-11";
		//①
		//実測値
		String actual = stringUtils.getNowDate();
		//判定
		assertThat(actual).isEqualTo(expected);

	}

	@Test
	public void StringUtils_isEmpty() {

		//①
		//実測値
		boolean actual1 = stringUtils.isEmpty("aaa");
		//判定
		assertThat(actual1).as("false確認").isEqualTo(false);

		//②
		//実測値
		boolean actual2 = stringUtils.isEmpty("");
		//判定
		assertThat(actual2).as("true確認（\"\"）").isEqualTo(true);

		//④
		//実測値
		boolean actual3 = stringUtils.isEmpty(null);
		//判定
		assertThat(actual3).as("true確認（null）").isEqualTo(true);
	}

	@Test
	public void StringUtils_isNum() {

		//①
		//実測値
		boolean actual1 = stringUtils.isNum("aaa");
		//判定
		assertThat(actual1).as("false(値あり)確認").isEqualTo(false);

		//②
		//実測値
		boolean actual2 = stringUtils.isNum("123");
		//判定
		assertThat(actual2).as("true(半角)確認").isEqualTo(true);

		//③
		//実測値
		boolean actual3 = stringUtils.isNum("１２３");
		//判定
		assertThat(actual3).as("true(全角)確認").isEqualTo(true);

		//④
		//実測値
		boolean actual4 = stringUtils.isNum("");
		//判定
		assertThat(actual4).as("false(空文字)確認");

	}

	@Test
	public void StringUtils_itemColumnsShaping() {

		//期待値
		List<String> expected = new ArrayList<String>();
		expected.add("aaa");
		expected.add("bbb");
		expected.add("ccc");
		expected.add("ddd");

		//①
		//テスト値
		List<String> list1 = new ArrayList<String>();
		list1.add("aaa");
		list1.add("bbb");
		list1.add("ccc");
		list1.add("ddd");
		//実測値
		List<String> actual1 = stringUtils.itemColumnsShaping(list1);
		//判定
		assertThat(actual1).as("[]なし確認").containsExactlyElementsOf(expected);

		//②
		//テスト値
		List<String> list2 = new ArrayList<String>();
		list2.add("[aaa");
		list2.add("bbb");
		list2.add("ccc");
		list2.add("ddd]");
		//実測値
		List<String> actual2 = stringUtils.itemColumnsShaping(list2);
		//判定
		assertThat(actual2).as("[]あり確認").containsExactlyElementsOf(expected);

		//③
		//テスト値　兼　期待値
		List<String> list3 = new ArrayList<String>();
		//実測値
		List<String> actual3 = stringUtils.itemColumnsShaping(list3);
		//判定
		assertThat(actual3).as("要素なし確認").containsExactlyElementsOf(list3);

	}

	@Test
	public void StringUtils_trim() {

		//期待値
		String expected = "aaa";

		//①
		//実測値
		String actual = stringUtils.trim("aaa");
		//判定
		assertThat(actual).as("トリム対象なし").isEqualTo(expected);

		//②
		//実測値
		String actual2 = stringUtils.trim(" aaa ");
		//判定
		assertThat(actual2).as("トリム対象半角スペース").isEqualTo(expected);

		//③
		//実測値
		String actual3 = stringUtils.trim("　aaa　");
		//判定
		assertThat(actual3).as("トリム対象全角スペース").isEqualTo(expected);

		//④
		//実測値
		String actual4 = stringUtils.trim("");
		//判定
		assertThat(actual4).as("トリム対象\"\"").isEmpty();

		//⑤
		//実測値
		String actual5 = stringUtils.trim(null);
		//判定
		assertThat(actual5).as("トリム対象null").isNull();

	}

	/***************SalaryOutputServiceImpl***************/

	List<OfficeWorker> adminDataList;
	List<OfficeWorker> memberDataList;

	public OfficeWorker SalaryOutputServiceImpl_adminSetup() {

		OfficeWorker ow = new OfficeWorker();

		ow.setId("0001");
		ow.setName("Admin太郎");
		ow.setPosition("Administrater");
		ow.setBasicSalary(100000);
		ow.setBasicSalaryAddPositionAllowance(125000);

		return ow;

	}

	public OfficeWorker SalaryOutputServiceImpl_memberSetup() {
		OfficeWorker ow = new OfficeWorker();

		ow.setId("0002");
		ow.setName("Member次郎");
		ow.setPosition("Member");
		ow.setBasicSalary(50000);
		ow.setBasicSalaryAddPositionAllowance(50000);

		return ow;

	}

	@Test
	public void SalaryInfoServiceImpl_SalaryCalculationOutput1() {

		//Adminのデータリスト
		List<OfficeWorker> adminDataList = new ArrayList<OfficeWorker>();
		adminDataList.add(SalaryOutputServiceImpl_adminSetup());

		//Memberのデータリスト
		List<OfficeWorker> memberDataList = new ArrayList<OfficeWorker>();
		memberDataList.add(SalaryOutputServiceImpl_memberSetup());

		//mockオブジェクトの設定
		when(mockAdministrater.DBSelect()).thenReturn(adminDataList);
		when(mockMember.DBSelect()).thenReturn(memberDataList);

		//①
		//期待値
		List<OfficeWorker> excepted1 = new ArrayList<OfficeWorker>();
		excepted1.add(SalaryOutputServiceImpl_adminSetup());
		excepted1.add(SalaryOutputServiceImpl_memberSetup());

		//実測値
		List<OfficeWorker> actual1 = injectMocksSalaryOutputServiceImpl.SalaryCalculationOutput();

		//判定
		assertThat(actual1).as("両方ともデータあり").containsExactlyElementsOf(excepted1);

	}

	@Test
	public void SalaryInfoServiceImpl_SalaryCalculationOutput2() {

		//Adminのデータリスト
		List<OfficeWorker> adminDataList = new ArrayList<OfficeWorker>();
		adminDataList.add(SalaryOutputServiceImpl_adminSetup());

		//Memberのデータリスト
		List<OfficeWorker> memberDataList = new ArrayList<OfficeWorker>();

		//mockオブジェクトの設定
		when(mockAdministrater.DBSelect()).thenReturn(adminDataList);
		when(mockMember.DBSelect()).thenReturn(memberDataList);

		//①
		//期待値
		List<OfficeWorker> excepted1 = new ArrayList<OfficeWorker>();
		excepted1.add(SalaryOutputServiceImpl_adminSetup());

		//実測値
		List<OfficeWorker> actual1 = injectMocksSalaryOutputServiceImpl.SalaryCalculationOutput();

		//判定
		assertThat(actual1).as("Adminのみデータあり").containsExactlyElementsOf(excepted1);

	}

	@Test
	public void SalaryInfoServiceImpl_SalaryCalculationOutput3() {

		//Adminのデータリスト
		List<OfficeWorker> adminDataList = new ArrayList<OfficeWorker>();

		//Memberのデータリスト
		List<OfficeWorker> memberDataList = new ArrayList<OfficeWorker>();
		memberDataList.add(SalaryOutputServiceImpl_memberSetup());

		//mockオブジェクトの設定
		when(mockAdministrater.DBSelect()).thenReturn(adminDataList);
		when(mockMember.DBSelect()).thenReturn(memberDataList);

		//①
		//期待値
		List<OfficeWorker> excepted1 = new ArrayList<OfficeWorker>();
		excepted1.add(SalaryOutputServiceImpl_memberSetup());

		//実測値
		List<OfficeWorker> actual1 = injectMocksSalaryOutputServiceImpl.SalaryCalculationOutput();

		//判定
		assertThat(actual1).as("Memberのみデータあり").containsExactlyElementsOf(excepted1);

	}

	@Test
	public void SalaryInfoServiceImpl_SalaryCalculationOutput4() {

		//Adminのデータリスト
		List<OfficeWorker> adminDataList = new ArrayList<OfficeWorker>();

		//Memberのデータリスト
		List<OfficeWorker> memberDataList = new ArrayList<OfficeWorker>();

		//mockオブジェクトの設定
		when(mockAdministrater.DBSelect()).thenReturn(adminDataList);
		when(mockMember.DBSelect()).thenReturn(memberDataList);

		//①
		//期待値
		List<OfficeWorker> excepted1 = new ArrayList<OfficeWorker>();

		//実測値
		List<OfficeWorker> actual1 = injectMocksSalaryOutputServiceImpl.SalaryCalculationOutput();

		//判定
		assertThat(actual1).as("両方ともデータなし").containsExactlyElementsOf(excepted1);

	}

	/***************UserInfoDeleteServiceImpl***************/

	public UserInfoDeleteForm UserInfoDeleteServiceImpl_dataSetup() {

		UserInfoDeleteForm tmp = new UserInfoDeleteForm();

		tmp.setId("0001");
		tmp.setName("Delete太郎");

		return tmp;

	}

	@Test
	public void UserInfoDeleteServiceImpl_UserInfoDeleteInit1() {

		//データリスト
		List<UserInfoDeleteForm> dataList = new ArrayList<UserInfoDeleteForm>();
		dataList.add(UserInfoDeleteServiceImpl_dataSetup());

		//mockオブジェクトの設定
		when(mockUserInfoMapper.selectIdNameForDelete()).thenReturn(dataList);

		//①
		//期待値
		List<UserInfoDeleteForm> excepted = dataList;

		//実測値
		List<UserInfoDeleteForm> actual = injectMocksUserInfoDeleteServiceImpl.UserInfoDeleteInit();

		//判定
		assertThat(actual).as("データあり").containsExactlyElementsOf(excepted);

	}

	@Test
	public void UserInfoDeleteServiceImpl_UserInfoDeleteInit2() {

		//データリスト
		List<UserInfoDeleteForm> dataList = new ArrayList<UserInfoDeleteForm>();

		//mockオブジェクトの設定
		when(mockUserInfoMapper.selectIdNameForDelete()).thenReturn(dataList);

		//①
		//期待値
		List<UserInfoDeleteForm> excepted = dataList;

		//実測値
		List<UserInfoDeleteForm> actual = injectMocksUserInfoDeleteServiceImpl.UserInfoDeleteInit();

		//判定
		assertThat(actual).as("データなし").containsExactlyElementsOf(excepted);

	}

	public UserInfoDeleteFormDetail UserInfoDeleteServiceImpl_UserInfoDelete_setup() {

		//データ準備
		UserInfoDeleteForm form = new UserInfoDeleteForm();
		form.setId("0001");
		form.setName("Delete太郎");
		List<UserInfoDeleteForm> list = new ArrayList<>();
		list.add(form);
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		userInfoDeleteFormDetail.setUserInfoDeleteFormList(list);
		userInfoDeleteFormDetail.setMessage("メッセージ");
		String[] check = null;
		userInfoDeleteFormDetail.setCheckBox(check);

		return userInfoDeleteFormDetail;

	}

	public List<UserInfoDeleteForm> UserInfoDeleteServiceImpl_UserInfoDelete_resultData() {

		//データ準備
		UserInfoDeleteForm form = new UserInfoDeleteForm();
		form.setId("0002");
		form.setName("Delete次郎");
		List<UserInfoDeleteForm> list = new ArrayList<>();
		list.add(form);

		return list;

	}

	@Test
	public void UserInfoDeleteServiceImpl_UserInfoDelete1() {

		//データ準備
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = UserInfoDeleteServiceImpl_UserInfoDelete_setup();

		//①
		//期待値
		UserInfoDeleteFormDetail expected1 = userInfoDeleteFormDetail;
		expected1.setMessage(ConstantsMsg.MSG_NOT_SELECT);

		//実測値
		UserInfoDeleteFormDetail actual1 = injectMocksUserInfoDeleteServiceImpl
				.UserInfoDelete(userInfoDeleteFormDetail);
		//判定
		assertThat(actual1.getMessage()).as("チェックボックス未選択").isEqualTo(expected1.getMessage());

		//②
		String[] check2 = { "0001" };
		userInfoDeleteFormDetail.setCheckBox(check2);

		//mock
		doNothing().when(mockUserInfoMapper).deleteData(Mockito.any());
		doNothing().when(mockSalaryInfoMapper).deleteData(Mockito.any());
		when(mockUserInfoMapper.selectIdNameForDelete())
				.thenReturn(UserInfoDeleteServiceImpl_UserInfoDelete_resultData());

		//期待値
		UserInfoDeleteFormDetail expected2 = new UserInfoDeleteFormDetail();
		expected2.setMessage(ConstantsMsg.MSG_DELETE_OK);
		expected2.setUserInfoDeleteFormList(UserInfoDeleteServiceImpl_UserInfoDelete_resultData());

		//実測値
		UserInfoDeleteFormDetail actual2 = injectMocksUserInfoDeleteServiceImpl
				.UserInfoDelete(userInfoDeleteFormDetail);
		//判定
		assertThat(actual2.getMessage()).as("正常デリート").isEqualTo(expected2.getMessage());
		assertThat(actual2.getUserInfoDeleteFormList()).as("正常デリート(リスト確認)")
				.containsExactlyElementsOf(expected2.getUserInfoDeleteFormList());

	}

	/***************UserInfoRegisterServiceImpl***************/

	@Test
	public void UserInfoRegisterServiceImpl_UserInfoRegisterInit() {

		//①
		List<String> list = new ArrayList<String>();
		list.add("Administrater");
		list.add("Member");

		//期待値
		UserInfoRegisterFormDetail exepted1 = new UserInfoRegisterFormDetail();
		exepted1.setSelectPosition(list);
		//実測値
		UserInfoRegisterFormDetail actual1 = injectMocksUserInfoRegisterServiceImpl.UserInfoRegisterInit();

		//判定
		assertThat(actual1.getSelectPosition()).containsExactlyElementsOf(exepted1.getSelectPosition());

	}

	@Test
	public void UserInfoRegisterServiceImpl_UserInfoRegister1() {

		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail.setInputName("");
		userInfoRegisterFormDetail.setInputPosition("Administrater");
		userInfoRegisterFormDetail.setInputBasicSalary("10000");

		//②
		//when(mockUserInfoRegisterServiceImpl.FormReset(Mockito.any())).thenReturn(userInfoRegisterFormDetail);
		when(mockStringUtils.trim("")).thenReturn("");
		when(mockStringUtils.trim("Regist太郎")).thenReturn("Regist太郎");
		when(mockStringUtils.trim("Administrater")).thenReturn("Administrater");
		when(mockStringUtils.trim("10000")).thenReturn("10000");
		when(mockStringUtils.trim("aaa")).thenReturn("aaa");
		when(mockStringUtils.isEmpty("")).thenReturn(true);
		when(mockStringUtils.isEmpty("Regist太郎")).thenReturn(false);
		when(mockStringUtils.isEmpty("Administrater")).thenReturn(false);
		when(mockStringUtils.isEmpty("10000")).thenReturn(false);
		when(mockStringUtils.isEmpty("aaa")).thenReturn(false);
		when(mockStringUtils.isNum("10000")).thenReturn(true);

		//期待値
		String exeptedErrMessage1 = "氏名は、必須入力です。";
		String exeptedName1 = "";
		String exeptedPosition1 = "Administrater";
		String exeptedBasicSalary1 = "10000";

		//実測値
		UserInfoRegisterFormDetail actual1 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail);

		//判定
		assertThat(actual1.isNameErrFlg()).as("regist氏名必須チェックFLG").isTrue();
		assertThat(actual1.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEqualTo(exeptedErrMessage1);
		assertThat(actual1.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual1.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual1.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isFalse();
		assertThat(actual1.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEmpty();
		assertThat(actual1.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName1);
		assertThat(actual1.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition1);
		assertThat(actual1.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary1);

		//②
		UserInfoRegisterFormDetail userInfoRegisterFormDetail2 = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail2.setInputName("Regist太郎");
		userInfoRegisterFormDetail2.setInputPosition("");
		userInfoRegisterFormDetail2.setInputBasicSalary("10000");

		//期待値
		String exeptedErrMessage2 = "役職は、必須入力です。";
		String exeptedName2 = "Regist太郎";
		String exeptedPosition2 = "";
		String exeptedBasicSalary2 = "10000";

		//実測値
		UserInfoRegisterFormDetail actual2 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail2);

		//判定
		assertThat(actual2.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual2.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual2.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isTrue();
		assertThat(actual2.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEqualTo(exeptedErrMessage2);
		assertThat(actual2.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isFalse();
		assertThat(actual2.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEmpty();
		assertThat(actual2.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName2);
		assertThat(actual2.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition2);
		assertThat(actual2.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary2);

		//③
		UserInfoRegisterFormDetail userInfoRegisterFormDetail3 = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail3.setInputName("Regist太郎");
		userInfoRegisterFormDetail3.setInputPosition("Administrater");
		userInfoRegisterFormDetail3.setInputBasicSalary("");

		//期待値
		String exeptedErrMessage3 = "基本給は、必須入力です。";
		String exeptedName3 = "Regist太郎";
		String exeptedPosition3 = "Administrater";
		String exeptedBasicSalary3 = "";

		//実測値
		UserInfoRegisterFormDetail actual3 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail3);

		//判定
		assertThat(actual3.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual3.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual3.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual3.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual3.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isTrue();
		assertThat(actual3.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEqualTo(exeptedErrMessage3);
		assertThat(actual3.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName3);
		assertThat(actual3.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition3);
		assertThat(actual3.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary3);

		//④
		UserInfoRegisterFormDetail userInfoRegisterFormDetail4 = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail4.setInputName("Regist太郎");
		userInfoRegisterFormDetail4.setInputPosition("Administrater");
		userInfoRegisterFormDetail4.setInputBasicSalary("aaa");

		//期待値
		String exeptedErrMessage4 = "基本給は、数値項目です。";
		String exeptedName4 = "Regist太郎";
		String exeptedPosition4 = "Administrater";
		String exeptedBasicSalary4 = "aaa";

		//実測値
		UserInfoRegisterFormDetail actual4 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail4);

		//判定
		assertThat(actual4.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual4.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual4.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual4.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual4.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isTrue();
		assertThat(actual4.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEqualTo(exeptedErrMessage4);
		assertThat(actual4.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName4);
		assertThat(actual4.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition4);
		assertThat(actual4.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary4);

		//⑤
		UserInfoRegisterFormDetail userInfoRegisterFormDetail5 = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail5.setInputName("Regist太郎");
		userInfoRegisterFormDetail5.setInputPosition("Administrater");
		userInfoRegisterFormDetail5.setInputBasicSalary("10000");

		when(mockUserInfoMapper.selectMaxId()).thenReturn("0002");
		when(mockStringUtils.getNowDate()).thenReturn("2020-06-09");
		doNothing().when(mockUserInfoMapper).insertUserInfo(Mockito.any());
		doNothing().when(mockSalaryInfoMapper).insertSalaryInfo(Mockito.any());

		//期待値
		String exeptedMessage1 = "登録が完了しました。";
		String exeptedName5 = "";
		String exeptedPosition5 = "";
		String exeptedBasicSalary5 = "";

		//実測値
		UserInfoRegisterFormDetail actual5 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail5);

		assertThat(actual5.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual5.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual5.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual5.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual5.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isFalse();
		assertThat(actual5.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEmpty();
		assertThat(actual5.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName5);
		assertThat(actual5.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition5);
		assertThat(actual5.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary5);
		assertThat(actual5.getMessage()).as("registメッセージ").isEqualTo(exeptedMessage1);

	}

	@Test
	public void UserInfoRegisterServiceImpl_UserInfoRegister2() {

		//⑥
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
		userInfoRegisterFormDetail.setInputName("Regist太郎");
		userInfoRegisterFormDetail.setInputPosition("Administrater");
		userInfoRegisterFormDetail.setInputBasicSalary("10000");

		when(mockUserInfoRegisterServiceImpl.FormReset(Mockito.any())).thenReturn(userInfoRegisterFormDetail);
		when(mockStringUtils.trim("Regist太郎")).thenReturn("Regist太郎");
		when(mockStringUtils.trim("Administrater")).thenReturn("Administrater");
		when(mockStringUtils.trim("10000")).thenReturn("10000");
		when(mockUserInfoMapper.selectMaxId()).thenReturn("0002");
		when(mockStringUtils.getNowDate()).thenReturn("2020/06/09");
		when(mockStringUtils.isNum("10000")).thenReturn(true);

		//期待値
		String exeptedMessage2 = "登録に失敗しました。";
		String exeptedName6 = "Regist太郎";
		String exeptedPosition6 = "Administrater";
		String exeptedBasicSalary6 = "10000";

		//実測値
		UserInfoRegisterFormDetail actual6 = injectMocksUserInfoRegisterServiceImpl
				.UserInfoRegister(userInfoRegisterFormDetail);

		//判定
		assertThat(actual6.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual6.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual6.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual6.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual6.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isFalse();
		assertThat(actual6.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEmpty();
		assertThat(actual6.getInputName()).as("regist氏名必須チェック：氏名入力値").isEqualTo(exeptedName6);
		assertThat(actual6.getInputPosition()).as("regist氏名必須チェック：役職入力値").isEqualTo(exeptedPosition6);
		assertThat(actual6.getInputBasicSalary()).as("regist氏名必須チェック：基本給入力値").isEqualTo(exeptedBasicSalary6);
		assertThat(actual6.getMessage()).as("registメッセージ").isEqualTo(exeptedMessage2);

	}

	@Test
	public void UserInfoRegisterServiceImpl_itemReturn() {

		//⑦
		//期待値
		List<String> exepted = new ArrayList<String>();
		exepted.add("Administrater");
		exepted.add("Member");

		//実測値
		List<String> actual = injectMocksUserInfoRegisterServiceImpl.itemReturn();

		//判定
		assertThat(actual).as("itemReturn").containsExactlyElementsOf(exepted);

	}

	@Test
	public void UserInfoRegisterServiceImpl_formReset() {

		//⑧

		when(mockStringUtils.itemColumnsShaping(Mockito.any())).thenReturn(new ArrayList<String>());

		UserInfoRegisterFormDetail form = new UserInfoRegisterFormDetail();
		form.setNameErrFlg(true);
		form.setNameErrMsg("aaa");
		form.setPositionErrFlg(true);
		form.setPositionErrMsg("bbb");
		form.setBasicSalaryErrFlg(true);
		form.setBasicSalaryErrMsg("ccc");
		form.setMessage("ddd");

		//期待値
		UserInfoRegisterFormDetail exepted = new UserInfoRegisterFormDetail();
		exepted.setNameErrFlg(false);
		exepted.setNameErrMsg("");
		exepted.setPositionErrFlg(false);
		exepted.setPositionErrMsg("");
		exepted.setBasicSalaryErrFlg(false);
		exepted.setBasicSalaryErrMsg("");
		exepted.setMessage("");

		//実測値
		UserInfoRegisterFormDetail actual = injectMocksUserInfoRegisterServiceImpl.FormReset(form);

		//判定
		assertThat(actual.isNameErrFlg()).as("regist氏名必須チェックFLG").isFalse();
		assertThat(actual.getNameErrMsg()).as("regist氏名必須チェックメッセージ").isEmpty();
		assertThat(actual.isPositionErrFlg()).as("regist氏名必須チェック：役職FLG").isFalse();
		assertThat(actual.getPositionErrMsg()).as("regist氏名必須チェック：役職メッセージ").isEmpty();
		assertThat(actual.isBasicSalaryErrFlg()).as("regist氏名必須チェック：基本給FLG").isFalse();
		assertThat(actual.getBasicSalaryErrMsg()).as("regist氏名必須チェック：基本給メッセージ").isEmpty();

	}

	/***************UserInfoSearchServiceImpl***************/

	@Test
	public void UserInfoSearchServiceImpl_UserInfoSearchInit1() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);

		when(mockUserInfoMapper.selectUserInfoForSearch()).thenReturn(list);

		//①期待値
		String exeptedRegistrationDate = "2020-06-09";
		String exeptedUpdatedDate = "2020-06-10";
		List<String> exeptedList1 = new ArrayList<String>();
		exeptedList1.add("社員番号");
		exeptedList1.add("フリガナ");
		exeptedList1.add("氏名");
		exeptedList1.add("メールアドレス");
		exeptedList1.add("認証失敗回数");
		exeptedList1.add("最終ログイン日");
		exeptedList1.add("登録者");
		exeptedList1.add("登録日");
		exeptedList1.add("更新者");
		exeptedList1.add("更新日");
		exeptedList1.add("役職");
		List<String> exeptedList2 = new ArrayList<String>();
		exeptedList2.add("昇順");
		exeptedList2.add("降順");

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.UserInfoSearchInit();

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getRegistrationDateString())
				.isEqualTo(exeptedRegistrationDate);
		assertThat(actual.getUserInfoSearchFormList().get(0).getUpdatedDateString()).isEqualTo(exeptedUpdatedDate);
		assertThat(actual.getSelectColumnsForSearch()).containsExactlyElementsOf(exeptedList1);
		assertThat(actual.getSelectColumnsForSort1()).containsExactlyElementsOf(exeptedList1);
		assertThat(actual.getSelectSortOrder1()).containsExactlyElementsOf(exeptedList2);
		assertThat(actual.getSelectColumnsForSort2()).containsExactlyElementsOf(exeptedList1);
		assertThat(actual.getSelectSortOrder2()).containsExactlyElementsOf(exeptedList2);

	}

	@Test
	public void UserInfoSearchServiceImpl_UserInfoSearchInit2() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setRegistrationDate(null);
		form.setUpdatedDate(null);
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);

		when(mockUserInfoMapper.selectUserInfoForSearch()).thenReturn(list);

		//②

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.UserInfoSearchInit();

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getRegistrationDateString())
				.isNullOrEmpty();
		assertThat(actual.getUserInfoSearchFormList().get(0).getUpdatedDateString()).isNullOrEmpty();

	}

	@Test
	public void UserInfoSearchServiceimpl_SearchOn1() {

		//①
		UserInfoSearchFormDetail data = new UserInfoSearchFormDetail();
		List<UserInfoSearchForm> dataList = new ArrayList<UserInfoSearchForm>();
		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		form.setNameReading("あああ");
		form.setName("亜亜亜");
		form.setMailAddress("aaa");
		form.setFailureTimes("0");
		form.setLastLoginDate("2020-06-08");
		form.setRegistrant("いいい");
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setRegistrationDateString("2020-06-09");
		form.setUpdater("ううう");
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		form.setUpdatedDateString("2020-06-10");
		form.setPosition("Administrater");
		dataList.add(form);
		data.setUserInfoSearchFormList(dataList);

		data.setSelectColumnsForSearchStr("");
		data.setSearchKey("あ");

		when(mockStringUtils.isEmpty("")).thenReturn(true);
		when(mockStringUtils.isEmpty("あ")).thenReturn(false);
		when(mockStringUtils.isEmpty("社員番号")).thenReturn(false);

		//期待値
		String exepted1 = ConstantsMsg.MSG_NOT_SELECT;

		//実測値
		UserInfoSearchFormDetail actual1 = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual1.getMessage()).isEqualTo(exepted1);

		//②
		data.setSelectColumnsForSearchStr("社員番号");
		data.setSearchKey("");

		//期待値
		String exepted2 = ConstantsMsg.MSG_NOT_SELECT;

		//実測値
		UserInfoSearchFormDetail actual12 = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual12.getMessage()).isEqualTo(exepted2);

	}

	public UserInfoSearchFormDetail UserInfoSearchServiceimpl_SearchOn2_setup() {
		UserInfoSearchFormDetail data = new UserInfoSearchFormDetail();
		List<UserInfoSearchForm> dataList = new ArrayList<UserInfoSearchForm>();
		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		form.setNameReading("データ１フリガナ");
		form.setName("データ１振り仮名");
		form.setMailAddress("dataiAdress");
		form.setFailureTimes("0");
		form.setLastLoginDate("2020-06-08");
		form.setRegistrant("データ１登録者");
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setRegistrationDateString("2020-06-09");
		form.setUpdater("データ１更新者");
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		form.setUpdatedDateString("2020-06-10");
		form.setPosition("Administrater");
		UserInfoSearchForm form2 = new UserInfoSearchForm();
		form2.setId("0002");
		form2.setNameReading("データ２フリガナ");
		form2.setName("データ２振り仮名");
		form2.setMailAddress("data2Adress");
		form2.setFailureTimes("1");
		form2.setLastLoginDate("2020-06-11");
		form2.setRegistrant("データ２登録者");
		form2.setRegistrationDate(Date.valueOf("2020-06-12"));
		form2.setRegistrationDateString("2020-06-12");
		form2.setUpdater("データ２更新者");
		form2.setUpdatedDate(Date.valueOf("2020-06-13"));
		form2.setUpdatedDateString("2020-06-13");
		form2.setPosition("Member");
		dataList.add(form);
		dataList.add(form2);
		data.setUserInfoSearchFormList(dataList);
		return data;

	}

	@Test
	public void UserInfoSearchServiceimpl_SearchOn2() {

		//③
		UserInfoSearchFormDetail data = UserInfoSearchServiceimpl_SearchOn2_setup();

		data.setSelectColumnsForSearchStr("社員番号");
		data.setSearchKey("1");

		when(mockStringUtils.isEmpty(Mockito.any())).thenReturn(false);

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

		//③
		data.setSelectColumnsForSearchStr("フリガナ");
		data.setSearchKey("２");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

		//④
		data.setSelectColumnsForSearchStr("氏名");
		data.setSearchKey("１");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

		//⑤
		data.setSelectColumnsForSearchStr("メールアドレス");
		data.setSearchKey("2");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

		//⑥
		data.setSelectColumnsForSearchStr("認証失敗回数");
		data.setSearchKey("0");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

		//⑦
		data.setSelectColumnsForSearchStr("最終ログイン日");
		data.setSearchKey("11");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

		//⑧
		data.setSelectColumnsForSearchStr("登録者");
		data.setSearchKey("１");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

		//⑨
		data.setSelectColumnsForSearchStr("登録日");
		data.setSearchKey("12");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

		//⑩
		data.setSelectColumnsForSearchStr("更新者");
		data.setSearchKey("１");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

		//⑪
		data.setSelectColumnsForSearchStr("更新日");
		data.setSearchKey("13");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

		//⑫
		data.setSelectColumnsForSearchStr("役職");
		data.setSearchKey("Admin");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SearchOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);
		assertThat(actual.getUserInfoSearchFormList().get(1).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_OFF);

	}

	@Test
	public void UserInfoSearchServiceimpl_SearchOff() {

		UserInfoSearchFormDetail data = new UserInfoSearchFormDetail();
		List<UserInfoSearchForm> dataList = new ArrayList<UserInfoSearchForm>();
		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		form.setNameReading("あああ");
		form.setName("亜亜亜");
		form.setMailAddress("aaa");
		form.setFailureTimes("0");
		form.setLastLoginDate("2020-06-08");
		form.setRegistrant("いいい");
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setRegistrationDateString("2020-06-09");
		form.setUpdater("ううう");
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		form.setUpdatedDateString("2020-06-10");
		form.setPosition("Administrater");
		form.setOutputFlg(ConstantsData.OUTPUT_OFF);
		dataList.add(form);
		data.setUserInfoSearchFormList(dataList);

		data.setSelectColumnsForSearchStr("社員番号");
		data.setSearchKey("あ");

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.SearchOff(data);

		//判定
		assertThat(actual.getSelectColumnsForSearchStr()).isEmpty();
		assertThat(actual.getSearchKey()).isEmpty();
		assertThat(actual.getUserInfoSearchFormList().get(0).getOutputFlg()).isEqualTo(ConstantsData.OUTPUT_ON);

	}

	public UserInfoSearchFormDetail UserInfoSearchServiceimpl_SortOn_setup1() {
		UserInfoSearchFormDetail data = new UserInfoSearchFormDetail();
		List<UserInfoSearchForm> dataList = new ArrayList<UserInfoSearchForm>();
		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		form.setNameReading("データ１フリガナ");
		form.setName("データ１振り仮名");
		form.setMailAddress("dataiAdress");
		form.setFailureTimes("0");
		form.setLastLoginDate("2020-06-08");
		form.setRegistrant("データ１登録者");
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setRegistrationDateString("2020-06-09");
		form.setUpdater("データ１更新者");
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		form.setUpdatedDateString("2020-06-10");
		form.setPosition("Administrater");
		UserInfoSearchForm form2 = new UserInfoSearchForm();
		form2.setId("0002");
		form2.setNameReading("データ２フリガナ");
		form2.setName("データ２振り仮名");
		form2.setMailAddress("data2Adress");
		form2.setFailureTimes("1");
		form2.setLastLoginDate("2020-06-11");
		form2.setRegistrant("データ２登録者");
		form2.setRegistrationDate(Date.valueOf("2020-06-12"));
		form2.setRegistrationDateString("2020-06-12");
		form2.setUpdater("データ２更新者");
		form2.setUpdatedDate(Date.valueOf("2020-06-13"));
		form2.setUpdatedDateString("2020-06-13");
		form2.setPosition("Member");
		dataList.add(form2);
		dataList.add(form);
		data.setUserInfoSearchFormList(dataList);
		return data;

	}

	public UserInfoSearchFormDetail UserInfoSearchServiceimpl_SortOn_setup2() {
		UserInfoSearchFormDetail data = new UserInfoSearchFormDetail();
		List<UserInfoSearchForm> dataList = new ArrayList<UserInfoSearchForm>();
		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		form.setNameReading("データ１フリガナ");
		form.setName("データ１振り仮名");
		form.setMailAddress("data1Adress");
		form.setFailureTimes("0");
		form.setLastLoginDate("2020-06-08");
		form.setRegistrant("データ１登録者");
		form.setRegistrationDate(Date.valueOf("2020-06-09"));
		form.setRegistrationDateString("2020-06-09");
		form.setUpdater("データ１更新者");
		form.setUpdatedDate(Date.valueOf("2020-06-10"));
		form.setUpdatedDateString("2020-06-10");
		form.setPosition("Administrater");
		UserInfoSearchForm form2 = new UserInfoSearchForm();
		form2.setId("0002");
		form2.setNameReading("データ２フリガナ");
		form2.setName("データ２振り仮名");
		form2.setMailAddress("data2Adress");
		form2.setFailureTimes("1");
		form2.setLastLoginDate("2020-06-11");
		form2.setRegistrant("データ２登録者");
		form2.setRegistrationDate(Date.valueOf("2020-06-12"));
		form2.setRegistrationDateString("2020-06-12");
		form2.setUpdater("データ２更新者");
		form2.setUpdatedDate(Date.valueOf("2020-06-13"));
		form2.setUpdatedDateString("2020-06-13");
		form2.setPosition("Member");
		dataList.add(form);
		dataList.add(form2);
		data.setUserInfoSearchFormList(dataList);
		return data;

	}

	@Test
	public void UserInfoSearchServiceimpl_SortOn1() {
		//①
		UserInfoSearchFormDetail data = UserInfoSearchServiceimpl_SortOn_setup1();

		when(mockStringUtils.isEmpty("")).thenReturn(true);
		when(mockStringUtils.isEmpty("社員番号")).thenReturn(false);
		when(mockStringUtils.isEmpty("昇順")).thenReturn(false);

		data.setSelectColumnsForSort1Str("");
		data.setSelectSortOrder1Str("昇順");

		//期待値
		String exepted = ConstantsMsg.MSG_NOT_SELECT;

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getMessage()).isEqualTo(exepted);

		//②
		data.setSelectColumnsForSort1Str("社員番号");
		data.setSelectSortOrder1Str("");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getMessage()).isEqualTo(exepted);

	}

	@Test
	public void UserInfoSearchServiceimpl_SortOn2() {
		//③
		UserInfoSearchFormDetail data = UserInfoSearchServiceimpl_SortOn_setup1();

		data.setSelectColumnsForSort1Str("社員番号");
		data.setSelectSortOrder1Str("昇順");

		when(mockStringUtils.isEmpty(Mockito.any())).thenReturn(false);

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");
		assertThat(actual.getUserInfoSearchFormList().get(1).getId()).isEqualTo("0002");

		//④
		data = UserInfoSearchServiceimpl_SortOn_setup2();
		data.setSelectColumnsForSort1Str("フリガナ");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getNameReading()).isEqualTo("データ２フリガナ");
		assertThat(actual.getUserInfoSearchFormList().get(1).getNameReading()).isEqualTo("データ１フリガナ");

		//⑤
		data.setSelectColumnsForSort1Str("氏名");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getName()).isEqualTo("データ２振り仮名");
		assertThat(actual.getUserInfoSearchFormList().get(1).getName()).isEqualTo("データ１振り仮名");

		//⑥
		data.setSelectColumnsForSort1Str("メールアドレス");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getMailAddress()).isEqualTo("data2Adress");
		assertThat(actual.getUserInfoSearchFormList().get(1).getMailAddress()).isEqualTo("data1Adress");

		//⑦
		data.setSelectColumnsForSort1Str("認証失敗回数");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getFailureTimes()).isEqualTo("1");
		assertThat(actual.getUserInfoSearchFormList().get(1).getFailureTimes()).isEqualTo("0");

		//⑧
		data.setSelectColumnsForSort1Str("最終ログイン日");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getLastLoginDate()).isEqualTo("2020-06-11");
		assertThat(actual.getUserInfoSearchFormList().get(1).getLastLoginDate()).isEqualTo("2020-06-08");

		//⑨
		data.setSelectColumnsForSort1Str("登録者");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getRegistrant()).isEqualTo("データ２登録者");
		assertThat(actual.getUserInfoSearchFormList().get(1).getRegistrant()).isEqualTo("データ１登録者");

		//⑩
		data.setSelectColumnsForSort1Str("登録日");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getRegistrationDateString()).isEqualTo("2020-06-12");
		assertThat(actual.getUserInfoSearchFormList().get(1).getRegistrationDateString()).isEqualTo("2020-06-09");

		//⑪
		data.setSelectColumnsForSort1Str("更新者");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getUpdater()).isEqualTo("データ２更新者");
		assertThat(actual.getUserInfoSearchFormList().get(1).getUpdater()).isEqualTo("データ１更新者");

		//⑫
		data.setSelectColumnsForSort1Str("更新日");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getUpdatedDateString()).isEqualTo("2020-06-13");
		assertThat(actual.getUserInfoSearchFormList().get(1).getUpdatedDateString()).isEqualTo("2020-06-10");

		//⑬
		data.setSelectColumnsForSort1Str("役職");
		data.setSelectSortOrder1Str("降順");

		//実測値
		actual = injectMocksUserInfoSearchServiceImpl.SortOn(data);

		//判定
		assertThat(actual.getUserInfoSearchFormList().get(0).getPosition()).isEqualTo("Member");
		assertThat(actual.getUserInfoSearchFormList().get(1).getPosition()).isEqualTo("Administrater");
	}

	@Test
	public void UserInfoSearchServiceimpl_SortOff() {

		UserInfoSearchFormDetail data = UserInfoSearchServiceimpl_SortOn_setup1();

		data.setSelectColumnsForSearchStr("社員番号");
		data.setSearchKey("あ");

		//実測値
		UserInfoSearchFormDetail actual = injectMocksUserInfoSearchServiceImpl.SortOff(data);

		//判定
		assertThat(actual.getSelectColumnsForSort1Str()).isEmpty();
		assertThat(actual.getSelectSortOrder1Str()).isEmpty();
		assertThat(actual.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");
		assertThat(actual.getUserInfoSearchFormList().get(1).getId()).isEqualTo("0002");

	}

	@Test
	public void UserInfoSearchServiceimpl_itemReturn() {
		List<String> exepted1 = new ArrayList<String>();
		exepted1.add("社員番号");
		exepted1.add("フリガナ");
		exepted1.add("氏名");
		exepted1.add("メールアドレス");
		exepted1.add("認証失敗回数");
		exepted1.add("最終ログイン日");
		exepted1.add("登録者");
		exepted1.add("登録日");
		exepted1.add("更新者");
		exepted1.add("更新日");
		exepted1.add("役職");

		List<String> actual = injectMocksUserInfoSearchServiceImpl.itemReturn(0);

		assertThat(actual).containsExactlyElementsOf(exepted1);

		List<String> exepted2 = new ArrayList<String>();
		exepted2.add("昇順");
		exepted2.add("降順");

		actual = injectMocksUserInfoSearchServiceImpl.itemReturn(1);

		assertThat(actual).containsExactlyElementsOf(exepted2);

	}

	/***************UserInfoUpdateListServiceImpl***************/

	@Test
	public void UserInfoUpdateListServiceImpl_UserInfoSelectForUpdateList() {

		List<UserInfoUpdateListForm> data = new ArrayList<UserInfoUpdateListForm>();
		UserInfoUpdateListForm form = new UserInfoUpdateListForm();
		form.setId("0001");
		form.setName("update太郎");
		data.add(form);

		when(mockUserInfoMapper.selectIdNameForUpdateList()).thenReturn(data);

		List<UserInfoUpdateListForm> actual = injectMocksUserInfoUpdateListServiceImpl.UserInfoSelectForUpdateList();

		assertThat(actual).containsExactlyElementsOf(data);

	}

	@Test
	public void UserInfoUpdateListServiceImpl_UserInfoUpdateListSelectCheck() {

		UserInfoUpdateListFormDetail dataDetail = new UserInfoUpdateListFormDetail();
		List<UserInfoUpdateListForm> data = new ArrayList<UserInfoUpdateListForm>();
		UserInfoUpdateListForm form = new UserInfoUpdateListForm();
		form.setId("0001");
		form.setName("update太郎");
		data.add(form);
		dataDetail.setUserInfoUpdateListFormList(data);

		dataDetail.setRadio(null);

		boolean actual = injectMocksUserInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(dataDetail);

		assertThat(actual).isFalse();

		dataDetail.setRadio("0001");

		actual = injectMocksUserInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(dataDetail);

		assertThat(actual).isTrue();

	}

	/***************UserInfoUpdateServiceImpl***************/

	public UserInfoUpdateFormDetail UserInfoUpdateServiceImpl_setup() {

		UserInfoUpdateFormDetail data = new UserInfoUpdateFormDetail();
		data.setId("0001");
		data.setNameReading("データ１フリガナ");
		data.setName("データ１振り仮名");
		data.setMailAddress("data1Address");
		data.setPassword("パスワード１");
		data.setPosition("Administrater");
		data.setBasicSalaryStr("10000");
		data.setUpdater("データ１更新者");

		return data;
	}

	@Test
	public void UserInfoUpdateServiceImpl_UserInfoUpdateInit() {

		UserInfoUpdateFormDetail data = UserInfoUpdateServiceImpl_setup();

		when(mockUserInfoMapper.selectUserInfoForUpdate(Mockito.any())).thenReturn(data);

		List<String> exepted = new ArrayList<String>();
		exepted.add("Administrater");
		exepted.add("Member");

		UserInfoUpdateFormDetail actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdateInit("0001");

		assertThat(actual.getSelectPosition()).containsExactlyElementsOf(exepted);

	}

	@Test
	public void UserInfoUpdateServiceImpl_UserInfoUpdate() {

		when(mockStringUtils.isEmpty("")).thenReturn(true);
		when(mockStringUtils.isEmpty("データ１振り仮名")).thenReturn(false);
		when(mockStringUtils.isEmpty("Administrater")).thenReturn(false);
		when(mockStringUtils.isEmpty("10000")).thenReturn(false);
		when(mockStringUtils.isEmpty("aaa")).thenReturn(false);
		when(mockStringUtils.isNum("")).thenReturn(false);
		when(mockStringUtils.isNum("10000")).thenReturn(true);

		UserInfoUpdateFormDetail data = UserInfoUpdateServiceImpl_setup();

		data.setName("");

		UserInfoUpdateFormDetail actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdate(data);

		assertThat(actual.isNameErrFlg()).isTrue();
		assertThat(actual.getNameErrMsg()).isEqualTo("氏名は、必須入力です。");
		assertThat(actual.isPositionErrFlg()).isFalse();
		assertThat(actual.getPositionErrMsg()).isEmpty();
		assertThat(actual.isBasicSalaryErrFlg()).isFalse();
		assertThat(actual.getBasicSalaryErrMsg()).isEmpty();

		data = UserInfoUpdateServiceImpl_setup();
		data.setPosition("");

		actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdate(data);

		assertThat(actual.isNameErrFlg()).isFalse();
		assertThat(actual.getNameErrMsg()).isEmpty();
		assertThat(actual.isPositionErrFlg()).isTrue();
		assertThat(actual.getPositionErrMsg()).isEqualTo("役職は、必須入力です。");
		assertThat(actual.isBasicSalaryErrFlg()).isFalse();
		assertThat(actual.getBasicSalaryErrMsg()).isEmpty();

		data = UserInfoUpdateServiceImpl_setup();
		data.setBasicSalaryStr("");

		actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdate(data);

		assertThat(actual.isNameErrFlg()).isFalse();
		assertThat(actual.getNameErrMsg()).isEmpty();
		assertThat(actual.isPositionErrFlg()).isFalse();
		assertThat(actual.getPositionErrMsg()).isEmpty();
		assertThat(actual.isBasicSalaryErrFlg()).isTrue();
		assertThat(actual.getBasicSalaryErrMsg()).isEqualTo("基本給は、必須入力です。");

		data = UserInfoUpdateServiceImpl_setup();
		data.setBasicSalaryStr("aaa");

		actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdate(data);

		assertThat(actual.isNameErrFlg()).isFalse();
		assertThat(actual.getNameErrMsg()).isEmpty();
		assertThat(actual.isPositionErrFlg()).isFalse();
		assertThat(actual.getPositionErrMsg()).isEmpty();
		assertThat(actual.isBasicSalaryErrFlg()).isTrue();
		assertThat(actual.getBasicSalaryErrMsg()).isEqualTo("基本給は、数値項目です。");

		when(mockStringUtils.getNowDate()).thenReturn("2020-06-09");
		doNothing().when(mockUserInfoMapper).updateUserInfo(Mockito.any());
		doNothing().when(mockSalaryInfoMapper).updateSalaryInfo(Mockito.any());

		data = UserInfoUpdateServiceImpl_setup();

		actual = injectMocksUserInfoUpdateServiceImpl.UserInfoUpdate(data);

		assertThat(actual.getBasicSalary()).isEqualTo(10000);
		assertThat(actual.getUpdatedDate()).isEqualTo(Date.valueOf("2020-06-09"));
		assertThat(actual.getMessage()).isEqualTo("更新が完了しました。");

	}

	@Test
	public void UserInfoController_Top() {

		ModelAndView mav = new ModelAndView();

		UserInfoController controller = new UserInfoController();
		ModelAndView actual = controller.Top(mav);

		assertThat(actual.getViewName()).isEqualTo("top");

	}

	@Test
	public void UserInfoController_UserInfoSearchInit() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);
		UserInfoSearchFormDetail formDetail = new UserInfoSearchFormDetail();
		formDetail.setUserInfoSearchFormList(list);
		formDetail.setMessage("メッセージ");

		ModelAndView mav = new ModelAndView();

		when(mockUserInfoSearchServiceImpl.UserInfoSearchInit()).thenReturn(formDetail);

		ModelAndView actual = injectMocksUserInfoController.UserInfoSearchInit(mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoSearch");
		Object obj = actual.getModel().get("userInfoSearchFormDetail");
		UserInfoSearchFormDetail a = (UserInfoSearchFormDetail) obj;
		assertThat(a.getMessage()).isEqualTo("メッセージ");
		assertThat(a.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoSearchOn() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);
		UserInfoSearchFormDetail formDetail = new UserInfoSearchFormDetail();
		formDetail.setUserInfoSearchFormList(list);
		formDetail.setMessage("メッセージ");

		when(mockUserInfoSearchServiceImpl.SearchOn(Mockito.any())).thenReturn(formDetail);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoSearchOn(null, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoSearch");
		Object obj = actual.getModel().get("userInfoSearchFormDetail");
		UserInfoSearchFormDetail a = (UserInfoSearchFormDetail) obj;
		assertThat(a.getMessage()).isEqualTo("メッセージ");
		assertThat(a.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");
	}

	@Test
	public void UserInfoController_UserInfoSearchOff() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);
		UserInfoSearchFormDetail formDetail = new UserInfoSearchFormDetail();
		formDetail.setUserInfoSearchFormList(list);
		formDetail.setMessage("メッセージ");

		when(mockUserInfoSearchServiceImpl.SearchOff(Mockito.any())).thenReturn(formDetail);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoSearchOff(null, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoSearch");
		Object obj = actual.getModel().get("userInfoSearchFormDetail");
		UserInfoSearchFormDetail a = (UserInfoSearchFormDetail) obj;
		assertThat(a.getMessage()).isEqualTo("メッセージ");
		assertThat(a.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");
	}

	@Test
	public void UserInfoController_UserInfoSortOn() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);
		UserInfoSearchFormDetail formDetail = new UserInfoSearchFormDetail();
		formDetail.setUserInfoSearchFormList(list);
		formDetail.setMessage("メッセージ");

		when(mockUserInfoSearchServiceImpl.SortOn(Mockito.any())).thenReturn(formDetail);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoSortOn(null, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoSearch");
		Object obj = actual.getModel().get("userInfoSearchFormDetail");
		UserInfoSearchFormDetail a = (UserInfoSearchFormDetail) obj;
		assertThat(a.getMessage()).isEqualTo("メッセージ");
		assertThat(a.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoSortOff() {

		UserInfoSearchForm form = new UserInfoSearchForm();
		form.setId("0001");
		List<UserInfoSearchForm> list = new ArrayList<UserInfoSearchForm>();
		list.add(form);
		UserInfoSearchFormDetail formDetail = new UserInfoSearchFormDetail();
		formDetail.setUserInfoSearchFormList(list);
		formDetail.setMessage("メッセージ");

		when(mockUserInfoSearchServiceImpl.SortOff(Mockito.any())).thenReturn(formDetail);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoSortOff(null, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoSearch");
		Object obj = actual.getModel().get("userInfoSearchFormDetail");
		UserInfoSearchFormDetail a = (UserInfoSearchFormDetail) obj;
		assertThat(a.getMessage()).isEqualTo("メッセージ");
		assertThat(a.getUserInfoSearchFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoUpdate() {

		UserInfoUpdateFormDetail formDetail = new UserInfoUpdateFormDetail();
		formDetail.setId("0001");

		when(mockUserInfoUpdateServiceImpl.UserInfoUpdate(Mockito.any())).thenReturn(formDetail);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoUpdate(null, mav);

		assertThat(actual.getViewName()).isEqualTo("UserInfoUpdate");
		Object obj = actual.getModel().get("userInfoUpdateFormDetail");
		UserInfoUpdateFormDetail a = (UserInfoUpdateFormDetail) obj;
		assertThat(a.getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoUpdateListInit() {

		UserInfoUpdateListForm form = new UserInfoUpdateListForm();
		form.setId("0001");
		List<UserInfoUpdateListForm> list = new ArrayList<UserInfoUpdateListForm>();
		list.add(form);

		when(mockUserInfoUpdateListServiceImpl.UserInfoSelectForUpdateList()).thenReturn(list);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoUpdateListInit(mav);

		assertThat(actual.getViewName()).isEqualTo("UserInfoUpdateList");
		Object obj = actual.getModel().get("userInfoUpdateListFormDetail");
		UserInfoUpdateListFormDetail a = (UserInfoUpdateListFormDetail) obj;
		assertThat(a.getUserInfoUpdateListFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoUpdateInit1() {

		when(mockUserInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(Mockito.any())).thenReturn(false);

		UserInfoUpdateListForm form = new UserInfoUpdateListForm();
		form.setId("0001");
		List<UserInfoUpdateListForm> list = new ArrayList<UserInfoUpdateListForm>();
		list.add(form);
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail.setUserInfoUpdateListFormList(list);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoUpdateInit(userInfoUpdateListFormDetail, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoUpdateList");
		Object obj = actual.getModel().get("userInfoUpdateListFormDetail");
		UserInfoUpdateListFormDetail a = (UserInfoUpdateListFormDetail) obj;
		assertThat(a.getUserInfoUpdateListFormList().get(0).getId()).isEqualTo("0001");
		assertThat(a.getMessage()).isEqualTo("選択してください。");

	}

	@Test
	public void UserInfoController_UserInfoUpdateInit2() {

		when(mockUserInfoUpdateListServiceImpl.UserInfoUpdateListSelectCheck(Mockito.any())).thenReturn(true);

		UserInfoUpdateListForm form = new UserInfoUpdateListForm();
		form.setId("0001");
		List<UserInfoUpdateListForm> list = new ArrayList<UserInfoUpdateListForm>();
		list.add(form);
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail.setUserInfoUpdateListFormList(list);
		ModelAndView mav = new ModelAndView();

		UserInfoUpdateFormDetail formDetail = new UserInfoUpdateFormDetail();
		formDetail.setId("0002");
		when(mockUserInfoUpdateServiceImpl.UserInfoUpdateInit(Mockito.any())).thenReturn(formDetail);

		ModelAndView actual = injectMocksUserInfoController.UserInfoUpdateInit(userInfoUpdateListFormDetail, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoUpdate");
		Object obj = actual.getModel().get("userInfoUpdateFormDetail");
		UserInfoUpdateFormDetail a = (UserInfoUpdateFormDetail) obj;
		assertThat(a.getId()).isEqualTo("0002");

	}

	@Test
	public void UserInfoController_UserInfoDeleteInit() {

		UserInfoDeleteForm form = new UserInfoDeleteForm();
		form.setId("0001");
		List<UserInfoDeleteForm> list = new ArrayList<UserInfoDeleteForm>();
		list.add(form);

		when(mockUserInfoDeleteServiceImpl.UserInfoDeleteInit()).thenReturn(list);
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoDeleteInit(mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoDelete");
		Object obj = actual.getModel().get("userInfoDeleteFormDetail");
		UserInfoDeleteFormDetail a = (UserInfoDeleteFormDetail) obj;
		assertThat(a.getUserInfoDeleteFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoDelete() {

		UserInfoDeleteForm form = new UserInfoDeleteForm();
		form.setId("0001");
		List<UserInfoDeleteForm> list = new ArrayList<UserInfoDeleteForm>();
		list.add(form);
		UserInfoDeleteFormDetail formDetail = new UserInfoDeleteFormDetail();
		formDetail.setUserInfoDeleteFormList(list);

		when(mockUserInfoDeleteServiceImpl.UserInfoDelete(Mockito.any())).thenReturn(formDetail);
		UserInfoDeleteFormDetail userInfoDeleteFormDetail = new UserInfoDeleteFormDetail();
		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoDelete(userInfoDeleteFormDetail, mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoDelete");
		Object obj = actual.getModel().get("userInfoDeleteFormDetail");
		UserInfoDeleteFormDetail a = (UserInfoDeleteFormDetail) obj;
		assertThat(a.getUserInfoDeleteFormList().get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_SalaryInfoList() {

		OfficeWorker officeWorker = new OfficeWorker();
		officeWorker.setId("0001");
		List<OfficeWorker> list = new ArrayList<OfficeWorker>();
		list.add(officeWorker);

		when(mockSalaryOutputServiceImpl.SalaryCalculationOutput()).thenReturn(list);

		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.SalaryInfoList(mav);

		assertThat(actual.getViewName()).isEqualTo("salaryInfoList");
		Object obj = actual.getModel().get("data");
		List<OfficeWorker> a = (List<OfficeWorker>) obj;
		assertThat(a.get(0).getId()).isEqualTo("0001");

	}

	@Test
	public void UserInfoController_UserInfoRegisterInit() {

		UserInfoRegisterFormDetail formDetail = new UserInfoRegisterFormDetail();
		formDetail.setInputName("太郎");
		when(mockUserInfoRegisterServiceImpl.UserInfoRegisterInit()).thenReturn(formDetail);

		ModelAndView mav = new ModelAndView();
		ModelAndView actual = injectMocksUserInfoController.UserInfoRegisterInit(mav);

		assertThat(actual.getViewName()).isEqualTo("userInfoRegister");
		Object obj = actual.getModel().get("userInfoRegisterFormDetail");
		UserInfoRegisterFormDetail a = (UserInfoRegisterFormDetail) obj;
		assertThat(a.getInputName()).isEqualTo("太郎");

	}

	@Test
	public void UserInfoController_UserInfoRegister() {

		UserInfoRegisterFormDetail formDetail = new UserInfoRegisterFormDetail();
		formDetail.setInputName("太郎");
		when(mockUserInfoRegisterServiceImpl.UserInfoRegister(Mockito.any())).thenReturn(formDetail);

		ModelAndView mav = new ModelAndView();
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
		ModelAndView actual = injectMocksUserInfoController.UserInfoRegister(userInfoRegisterFormDetail, mav);
		assertThat(actual.getViewName()).isEqualTo("userInfoRegister");

		Object obj = actual.getModel().get("userInfoRegisterFormDetail");
		UserInfoRegisterFormDetail a = (UserInfoRegisterFormDetail) obj;
		assertThat(a.getInputName()).isEqualTo("太郎");
	}
}