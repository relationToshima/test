package com.example.demo.mapper;

import java.util.List;

import com.example.demo.domain.SalaryInfo;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface SalaryInfoMapper {
	List<SalaryInfo> selectAll();

	void deleteData(String id);

	void insertSalaryInfo(SalaryInfo salaryInfoToInsert);

	void updateSalaryInfo(UserInfoUpdateFormDetail userInfoUpdateFormDetail);
}
