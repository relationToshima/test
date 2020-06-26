package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.demo.ServiceImpl.UserDetailsServiceImpl;
import com.example.demo.security.SessionExpiredDetectingLoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//使ってないけど一応
		web.ignoring().antMatchers(
				"/images/**",
				"/css/**",
				"/javascript/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/login") //ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
				.loginProcessingUrl("/sign_in") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
				.usernameParameter("mailAddress") //リクエストパラメータのname属性を明示
				.passwordParameter("password")
				.successForwardUrl("/top")//認証成功時
				.failureUrl("/login?error");//認証失敗時
		//.permitAll();

		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.deleteCookies("JSESSIONID") // ログアウト完了後Cookieを破棄する
				.invalidateHttpSession(true);//ログアウト完了後セッションを破棄する
		//.permitAll();

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint());//タイムアウトした際に判定できるようにする
	}

	@Bean
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new SessionExpiredDetectingLoginUrlAuthenticationEntryPoint("/login");
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		//DBに登録されているidとpasswordで認証
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

}
