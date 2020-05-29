package com.example.demo.myInterface.OfficeWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.myInterface.OfficeWorkerInterface;

@Component
public class Member implements OfficeWorkerInterface{

	@Autowired
	MemberMapper memberMapper;

	public List<OfficeWorker> DBSelect() {

		List<OfficeWorker> menberList = memberMapper.selectNamePositionSalaryFromMember();
		return menberList;
	}


}
