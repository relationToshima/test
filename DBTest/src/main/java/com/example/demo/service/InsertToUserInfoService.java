package com.example.demo.service;

import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;

public interface InsertToUserInfoService {

	boolean UserInfoInsert(UserInfo userinfoToInsert, SalaryInfo salaryInfoToInsert);

}
