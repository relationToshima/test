package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

	/**
	 * StringIsNullorEmptyStringメソッド
	 * パラメータ(String)がNULLもしくは空文字("")の場合、trueを返却する.
	 * @param str 文字列
	 * @return 文字列がNULLもしくは空文字("")の場合、true
	 */
	private boolean StringIsNullOrEmptyString(String str) {

		boolean ans = false;

		if(str.equals("") || str.equals(null)) {
			ans = true;
		}

		return ans;

	}

}
