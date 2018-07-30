/**
 * 
 */
package com.assignments.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assignments.domain.LoginModel;
import com.assignments.exception.DecodingException;
import com.assignments.exception.LoginException;
import com.assignments.service.LoginService;
import com.assignments.util.AssignmentsUtil;

/**
 * @author Jagadish Anala
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

	@Value("${service.login.username}")
	private String userName;

	@Value("${service.login.password}")
	private char[] password;

	@Value("${service.login.fullName}")
	private String fullName;

	@Value("${error.login.invalid}")
	private String loginErrorMessage;

	@Override
	public LoginModel validateLogin(LoginModel loginModel) throws LoginException, DecodingException {
		logger.info("username from properties [ " + userName + " ]");
		logger.info("password from properties [ " + password + " ]");
		loginModel = findLoginDetails(loginModel);
		if (loginModel != null) {
			return loginModel;
		} else
			throw new LoginException(loginErrorMessage);
	}

	// Need to modify the code when connected to database.
	private LoginModel findLoginDetails(LoginModel loginModel) throws LoginException, DecodingException {
		if (userName.equals(loginModel.getUserName())
				&& AssignmentsUtil.decode(String.valueOf(password)).equals(String.valueOf(loginModel.getPassword()))) {
			loginModel.setFullName(fullName);
			return loginModel;
		}
		return null;
	}
}
