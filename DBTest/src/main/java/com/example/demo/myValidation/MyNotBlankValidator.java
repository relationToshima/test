package com.example.demo.myValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.utils.StringUtils;

public class MyNotBlankValidator implements ConstraintValidator<MyNotBlank, String> {

	@Autowired
	StringUtils stringUtils;

	@Override
	public boolean isValid(String data, ConstraintValidatorContext context) {

		data = stringUtils.trim(data);

		if (stringUtils.isEmpty(data)) {
			return false;
		}
		return true;

	}

}
