package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class SessionExpiredDetectingLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public SessionExpiredDetectingLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {

		String redirectUrl = super.buildRedirectUrlToLoginPage(request, response, authException);
		//リダイレクトの理由がタイムアウトの場合、パラメーター追加
		if (isRequestedSessionInvalid(request)) {
			redirectUrl += redirectUrl.contains("?") ? "&" : "?";
			redirectUrl += "timeout";
		}
		return redirectUrl;
	}

	private boolean isRequestedSessionInvalid(HttpServletRequest request) {
		return request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
	}
}