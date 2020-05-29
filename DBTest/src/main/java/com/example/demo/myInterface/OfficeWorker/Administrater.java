package com.example.demo.myInterface.OfficeWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.mapper.AdministraterMapper;
import com.example.demo.myInterface.OfficeWorkerInterface;

@Component
public class Administrater implements OfficeWorkerInterface{

	@Autowired
	AdministraterMapper administraterMapper;

	@Override
	public List<OfficeWorker> DBSelect() {
		List<OfficeWorker> administraterList = administraterMapper.selectNamePositionSalaryFromAdministrater();
		return administraterList;
	}

}
