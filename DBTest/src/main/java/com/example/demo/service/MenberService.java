package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.mapper.MenberMapper;

@Component
public class MenberService{

	@Autowired
	MenberMapper menberMapper;

	public List<OfficeWorker> DBSelect() {

		List<OfficeWorker> menberList = menberMapper.selectNamePositionSalaryFromMenber();
		return menberList;
	}


}
