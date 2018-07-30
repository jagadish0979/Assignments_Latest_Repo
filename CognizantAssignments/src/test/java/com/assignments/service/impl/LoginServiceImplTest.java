package com.assignments.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.domain.LoginModel;
import com.assignments.service.LoginService;

/**
 * @author Jagadish Anala
 *
 */
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginServiceImplTest extends CognizantAssignmentsApplicationTests {

	@Autowired
	private LoginService loginService;

	@Test
	public void successLogin() throws Exception {
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName("admin");
		loginModel.setPassword("admin".toCharArray());
		assertTrue(loginService.validateLogin(loginModel));
	}

	@Test
	public void failedLogin() throws Exception {
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName("admin");
		loginModel.setPassword("1234".toCharArray());
		assertFalse(loginService.validateLogin(loginModel));
	}
}
