package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.OfficeWorker;
import com.example.demo.form.UserInfoUpdateListForm;
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

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> grantCollect = auth.getAuthorities();

		Object[] grantList = grantCollect.toArray();

		List<UserInfoUpdateListForm> userInfoUpdateListFormList = null;

		List<OfficeWorker> adminList = null;
		List<OfficeWorker> memberList = null;
		List<OfficeWorker> returnData = new ArrayList<OfficeWorker>();

		//ADMINは全ユーザーが確認可能
		//USER(Member)は自分のみ確認可能
		if (grantList[0].equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

			//管理者の給与情報を取得
			adminList = admin.DBSelect();

			//メンバーの給与情報を取得
			memberList = member.DBSelect();

			//管理者の給与にメンバーの給与情報を結合
			returnData.addAll(adminList);
			returnData.addAll(memberList);

		} else {

			//社員番号の取得
			String[] nameId = auth.getName().split("/");
			String id = nameId[1];

			//本人の給与情報を取得
			memberList = member.DBSelect(id);

			returnData.addAll(memberList);

		}

		return returnData;
	}

}
