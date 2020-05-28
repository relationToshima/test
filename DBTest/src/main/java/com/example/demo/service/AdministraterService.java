package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.mapper.AdministraterMapper;

@Component
public class AdministraterService{

	@Autowired
	AdministraterMapper administraterMapper;

	public List<OfficeWorker> DBSelect() {
		List<OfficeWorker> administraterList = administraterMapper.selectNamePositionSalaryFromAdministrater();
		return administraterList;
	}

}
