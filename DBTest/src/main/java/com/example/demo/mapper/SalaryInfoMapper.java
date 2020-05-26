package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {
	List<SalaryInfo> selectAll();
}
