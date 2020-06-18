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
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		//DB確認
		LoginUser user = userInfoMapper.selectUserAuth(userId);

		if (user == null) {

			throw new UsernameNotFoundException("社員番号：" + userId + "は登録がありません。");

		}

		//トリム
		user.setName(stringUtils.trim(user.getName()));
		user.setPassword(stringUtils.trim(user.getPassword()));
		user.setPosition(stringUtils.trim(user.getPosition()));

		//権限のリスト
		//AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
		//権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		grantList.add(authority);

		//rawDataのパスワードは渡すことができないので、暗号化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		//UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
		UserDetails userDetails = (UserDetails) new User(user.getName(), encoder.encode(user.getPassword()),
				grantList);

		return userDetails;

	}

}
