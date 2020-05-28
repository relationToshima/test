package com.example.demo.officeWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.mapper.MenberMapper;
import com.example.demo.myInterface.OfficeWorkerInterface;

@Component
public class Menber implements OfficeWorkerInterface{

	@Autowired
	MenberMapper menberMapper;

	@Override
	public List<OfficeWorker> DBSelect() {

		List<OfficeWorker> menberList = menberMapper.selectNamePositionSalaryFromMenber();
		return menberList;
	}


}
