package com.assignments.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.domain.LoginModel;
import com.assignments.util.TestUtil;

/**
 * @author Jagadish Anala
 *
 */
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginControllerTest extends CognizantAssignmentsApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void doLogin() {
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName("admin");
		loginModel.setPassword("admin".toCharArray());
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/doLogin").contentType(TestUtil.APPLICATION_JSON_UTF8)
					.content(TestUtil.convertObjectToJsonBytes(loginModel))).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
