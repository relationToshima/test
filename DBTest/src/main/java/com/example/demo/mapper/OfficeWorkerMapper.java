package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.OfficeWorker;

@Mapper
public interface OfficeWorkerMapper {
	List<OfficeWorker> selectNameSalary();
}
