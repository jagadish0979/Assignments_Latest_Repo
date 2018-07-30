package com.assignments.controller;

import javax.security.auth.login.LoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignments.domain.LoginModel;
import com.assignments.domain.Result;
import com.assignments.service.LoginService;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private Environment env;

	@Autowired
	private LoginService loginService;

	@PostMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> doLogin(@RequestBody LoginModel loginModel) throws LoginException, Exception {
		logger.info("Trying to login using userName [ " + loginModel.getUserName() + " ] and password [ "
				+ loginModel.getPassword() + " ]");
		if (loginService.validateLogin(loginModel)) {
			Result result = new Result();
			result.setMessage("Success");
			result.setUrl("/main");
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		} else
			throw new LoginException(env.getProperty("error.login.invalid"));
	}

}