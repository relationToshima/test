package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Configurable;

import com.example.demo.domain.OfficeWorker;

@Configurable
@Mapper
public interface AdministraterMapper {
	List<OfficeWorker> selectNamePositionSalaryFromAdministrater();
}
