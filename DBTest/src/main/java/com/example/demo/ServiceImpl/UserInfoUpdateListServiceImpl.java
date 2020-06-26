package com.example.demo.ServiceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.form.UserInfoUpdateListForm;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoUpdateListService;

@Service
public class UserInfoUpdateListServiceImpl implements UserInfoUpdateListService {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfoUpdateListForm> UserInfoSelectForUpdateList() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> grantCollect = auth.getAuthorities();

		Object[] grantList = grantCollect.toArray();

		List<UserInfoUpdateListForm> userInfoUpdateListFormList = null;

		if (grantList[0].equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

			//ADMINは全ユーザーが操作可能
			userInfoUpdateListFormList = userInfoMapper.selectUserInfoForUpdateList();

		} else {

			//社員番号の取得
			String[] nameId = auth.getName().split("/");
			String id = nameId[1];

			//USER(Member)は自分のみ操作可能
			userInfoUpdateListFormList = userInfoMapper.selectUserInfoForUpdateListOnlyMe(id);

		}

		return userInfoUpdateListFormList;
	}

	@Override
	public boolean UserInfoUpdateListSelectCheck(UserInfoUpdateListFormDetail userInfoUpdateListFormDetail) {

		boolean result = true;

		if (userInfoUpdateListFormDetail.getRadio() == null) {
			result = false;
		}
		return result;
	}

}
