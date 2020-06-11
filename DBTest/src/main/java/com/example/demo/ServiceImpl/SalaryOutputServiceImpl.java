package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.myInterface.OfficeWorker.Administrater;
import com.example.demo.myInterface.OfficeWorker.Member;
import com.example.demo.service.SalaryOutputService;

@Service
public class SalaryOutputServiceImpl implements SalaryOutputService {

	@Autowired
	Administrater admin;
	@Autowired
	Member member;

	@Override
	@Transactional(readOnly = true)
	public List<OfficeWorker> SalaryCalculationOutput() {

		//管理者の給与情報を取得
		List<OfficeWorker> adminList = admin.DBSelect();

		//メンバーの給与情報を取得
		List<OfficeWorker> memberList = member.DBSelect();

		//管理者の給与にメンバーの給与情報を結合
		List<OfficeWorker> returnData = new ArrayList<OfficeWorker>();
		returnData.addAll(adminList);
		returnData.addAll(memberList);

		return returnData;
	}

}
