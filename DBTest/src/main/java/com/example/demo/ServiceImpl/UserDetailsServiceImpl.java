package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.utils.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	StringUtils stringUtils;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

		//DB確認
		LoginUser user = userInfoMapper.selectUserAuth(mailAddress);

		if (user == null) {

			throw new UsernameNotFoundException("メールアドレス：" + mailAddress + "は登録がありません。");

		}

		//トリム
		user.setName(stringUtils.trim(user.getName()));
		user.setMailAddress(stringUtils.trim(user.getMailAddress()));
		user.setPassword(stringUtils.trim(user.getPassword()));
		user.setPosition(stringUtils.trim(user.getPosition()));

		//権限
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = null;
		if (user.getPosition().equals("Administrater")) {
			authority = new SimpleGrantedAuthority("ROLE_ADMIN");
		} else {
			authority = new SimpleGrantedAuthority("ROLE_USER");
		}
		grantList.add(authority);

		//エンコーダー
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		UserDetails userDetails = (UserDetails) new User(user.getName() + "/" + user.getId(),
				encoder.encode(user.getPassword()),
				grantList);

		return userDetails;

	}

}
