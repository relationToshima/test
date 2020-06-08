package com.example.demo.constants.message;

import org.springframework.stereotype.Component;

@Component
public class ConstantsMsg {

	//必須チェック　エラーメッセージ
	public static final String ERR_MSG_NULL = "は、必須入力です。";
	//数値チェック　エラーメッセージ
	public static final String ERR_MSG_NOT_NUM = "は、数値項目です。";

	//DB登録　完了メッセージ
	public static final String MSG_REGISTER_OK = "登録が完了しました。";

	//DB登録　失敗メッセージ
	public static final String MSG_REGISTER_NG = "登録に失敗しました。";

	//DB削除　完了メッセージ
	public static final String MSG_DELETE_OK = "削除が完了しました。";

	//DB削除　失敗メッセージ
	public static final String MSG_DELETE_NG = "削除に失敗しました。";

	//未選択　メッセージ
	public static final String MSG_NOT_SELECT = "選択してください。";

	//DB更新　完了メッセージ
	public static final String MSG_UPDATE_OK = "更新が完了しました。";

	//DB更新　失敗メッセージ
	public static final String MSG_UPDATE_NG = "更新に失敗しました。";
}
