package com.example.demo.myValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.UserInfoMapper;

public class UnUsedValidator implements ConstraintValidator<UnUsed, String> {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public boolean isValid(String mailAddress, ConstraintValidatorContext context) {
		int count = userInfoMapper.selectMailAddressCount(mailAddress);
		if (count == 0) {
			return true;
		}
		return false;

	}

}
