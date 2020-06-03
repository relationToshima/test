package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

	/**
	 * isEmptyメソッド
	 * パラメータ(String)がNULLもしくは空文字("")の場合、trueを返却する.
	 * @param str 文字列
	 * @return 文字列がNULLもしくは空文字("")の場合、true
	 */
	public boolean isEmpty(String str) {

		boolean ans = false;

		if (str == null || str.isEmpty()) {
			ans = true;
		}

		return ans;

	}

	/**
	 * isNumメソッド
	 * パラメータ(String)が数値の場合、trueを返却する.
	 * @param str 文字列
	 * @return 文字列が数値の場合、true
	 */
	public boolean isNum(String str) {

		boolean ans = false;

		String regex_num = "^[0-9０-９]+$";
		Pattern pattern = Pattern.compile(regex_num);
		Matcher match = pattern.matcher(str);

		if (match.matches()) {
			ans = true;
		}

		return ans;

	}

	/**
	 * trimメソッド
	 * パラメータ(String)の値の前後の空白(半角・全角)を取り除いた結果を返却する.
	 * @param str 文字列
	 * @return 文字列の前後の空白を取り除いた文字列
	 */
	public String trim(String str) {
		int len = str.length();
		int st = 0;
		String ans = "";
		String[] val = str.split("");

		while ((st < len) && (val[st].equals(" ") || val[st].equals("　"))) {
			st++;
		}
		while ((st < len) && (val[len - 1].equals(" ") || val[len - 1].equals("　"))) {
			len--;
		}

		if ((st > 0) || (len < str.length())) {
			ans = str.substring(st, len);
		} else {
			ans = str;
		}
		return ans;
	}

}
