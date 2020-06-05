package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.SalaryInfo;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;

public interface SalaryInfoMapper {
	List<SalaryInfo> selectAll();

	void deleteData(String idList);

	boolean insertSalaryInfo(@Param("salaryInfoToInsert") SalaryInfo salaryInfoToInsert);

	boolean updateSalaryInfo(UserInfoUpdateFormDetail userInfoUpdateFormDetail);
}
