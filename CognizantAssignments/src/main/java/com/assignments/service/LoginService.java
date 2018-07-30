package com.assignments.service;

import com.assignments.domain.LoginModel;

/**
 * @author Jagadish Anala
 *
 */
public interface LoginService {
	public boolean validateLogin(LoginModel loginModel) throws Exception;
}
