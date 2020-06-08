package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.utils.StringUtils;

public class junitTest {

	StringUtils stringUtils = new StringUtils();

	@Test
	public void StringUtils_getNowDate() throws Exception {

		//期待値
		String expected = "2020-06-08";

		//実測値
		String actual = stringUtils.getNowDate();

		//判定
		assertThat(actual, is(expected));
	}
}
