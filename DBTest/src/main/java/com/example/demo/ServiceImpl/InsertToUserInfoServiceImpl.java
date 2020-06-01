package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.InsertToUserInfoService;

@Service
@Transactional(readOnly = true)
public class InsertToUserInfoServiceImpl implements InsertToUserInfoService{

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	SalaryInfoMapper salaryInfoMapper;

	@Override
	public boolean UserInfoInsert(UserInfo userInfoToInsert, SalaryInfo salaryInfoToInsert) {

		boolean result = true;

		try {
			//IDの採番
			String strId = userInfoMapper.selectMaxId();
			int id = Integer.parseInt(strId) + 1;
			userInfoToInsert.setId(String.valueOf(String.format("%04d", id)));
			salaryInfoToInsert.setId(userInfoToInsert.getId());

			//userInfoのインサート
			userInfoMapper.insertUserInfo(userInfoToInsert);

			//salaryInfoのインサート
			salaryInfoMapper.insertSalaryInfo(salaryInfoToInsert);


		}catch(Exception e){
			result = false;
		}

		return result;
	}

}
