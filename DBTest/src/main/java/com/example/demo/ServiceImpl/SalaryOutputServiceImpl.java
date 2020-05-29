package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.myInterface.OfficeWorkerInterface;
import com.example.demo.myInterface.OfficeWorker.Administrater;
import com.example.demo.myInterface.OfficeWorker.Member;
import com.example.demo.service.SalaryOutputService;

@Component
public class SalaryOutputServiceImpl implements SalaryOutputService {

    @Autowired
    private AutowireCapableBeanFactory factory;

    private <T> T createAutowiredObject(Class<T> c){
        return factory.createBean(c);
    }

	@Override
	public List<OfficeWorker> SalaryCalculationOutput() {

		//管理者の給与情報を取得
		OfficeWorkerInterface admin = createAutowiredObject(Administrater.class);
		List<OfficeWorker> adminList = admin.DBSelect();

		//メンバーの給与譲歩を取得
		OfficeWorkerInterface member = createAutowiredObject(Member.class);
		List<OfficeWorker> memberList = member.DBSelect();

		//管理者の給与にメンバーの給与情報を結合
		List<OfficeWorker> returnData = new ArrayList<OfficeWorker>();
		returnData.addAll(adminList);
		returnData.addAll(memberList);

		return returnData;
	}

}
