/**
 * 
 */
package com.assignments.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assignments.domain.LoginModel;
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

	@Override
	public boolean validateLogin(LoginModel loginModel) throws Exception {
		logger.info("username from properties [ " + userName + " ]");
		logger.info("password from properties [ " + password + " ]");
		return userName.equals(loginModel.getUserName()) && AssignmentsUtil.decode(String.valueOf(password)).equals(String.valueOf(loginModel.getPassword()));
	}
}
