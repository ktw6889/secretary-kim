package secretary.common.user.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	private String loginIdName;
	private String loginPasswdName;
	private String loginRedirectName;
	private String exceptionMsgName;
	private String defaultFailureUrl;
	
	public LoginFailureHandler() {
		this.loginIdName = "username";
		this.loginPasswdName = "password";
		this.loginRedirectName = "/login.do?";
		this.exceptionMsgName = "securityexceptionmsg";
		this.defaultFailureUrl = "/login.do?status=fail";
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
		System.out.println("onAuthenticationFailure");
		/*
		String loginId = request.getParameter(loginIdName);
		String loginPasswd = request.getParameter(loginPasswdName);
		String loginRedirect =  request.getParameter(loginRedirectName);
		
		request.setAttribute(loginIdName, loginId);
		request.setAttribute(loginPasswdName, loginPasswd);
		
		String errormsg = "";
		if(exception instanceof BadCredentialsException) {
            errormsg = "error.BadCredentials";
        } else if(exception instanceof InternalAuthenticationServiceException) {
            errormsg = "error.BadCredentials";
        } else if(exception instanceof DisabledException) {
            errormsg = "error.Disaled";
        } else if(exception instanceof CredentialsExpiredException) {
            errormsg = "error.CredentialsExpired";
        }
		
		System.out.println("errormsg:"+errormsg);
		
		request.setAttribute(exceptionMsgName, exception.getMessage());
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
		*/
		//super.onAuthenticationFailure(request, response, exception);
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}
	

	public String getLoginIdName() {
		return loginIdName;
	}

	public void setLoginIdName(String loginIdName) {
		this.loginIdName = loginIdName;
	}

	public String getLoginPasswdName() {
		return loginPasswdName;
	}

	public void setLoginPasswdName(String loginPasswdName) {
		this.loginPasswdName = loginPasswdName;
	}

	

	public String getLoginRedirectName() {
		return loginRedirectName;
	}

	public void setLoginRedirectName(String loginRedirectName) {
		this.loginRedirectName = loginRedirectName;
	}

	public String getExceptionMsgName() {
		return exceptionMsgName;
	}

	public void setExceptionMsgName(String exceptionMsgName) {
		this.exceptionMsgName = exceptionMsgName;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	
}
