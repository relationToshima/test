package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.SalaryInfo;

public interface SalaryInfoMapper {
	List<SalaryInfo> selectAll();

	boolean insertSalaryInfo(@Param("salaryInfoToInsert") SalaryInfo salaryInfoToInsert);
}
