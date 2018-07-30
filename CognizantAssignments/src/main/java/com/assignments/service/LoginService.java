package com.assignments.service;

import com.assignments.domain.LoginModel;
import com.assignments.exception.DecodingException;
import com.assignments.exception.LoginException;

/**
 * @author Jagadish Anala
 *
 */
public interface LoginService {
	public LoginModel validateLogin(LoginModel loginModel) throws LoginException, DecodingException;
}
