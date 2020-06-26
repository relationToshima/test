package com.example.demo.constants;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class ConstantsData {

	//ユーザー情報検索画面　表示
	public static final String OUTPUT_ON = "1";

	//ユーザー情報検索画面　非表示
	public static final String OUTPUT_OFF = "0";

	//画像アップロードパス
	public static final String FILE_UPLOAD_PATH = new File(".").getAbsoluteFile().getParent() + "\\uploadFile";

}
