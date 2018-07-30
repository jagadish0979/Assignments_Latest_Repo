package com.assignments.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Jagadish Anala
 *
 */
public class AssignmentsUtilTest {

	@Test
	public void Round() {
		assertEquals(12.56, AssignmentsUtil.Round(12.5566), 0.001);
	}

	@Test
	public void encodeANDdecode() throws Exception {
		String source = "Cognizant";
		assertEquals(source, AssignmentsUtil.decode(AssignmentsUtil.encode(source)));
	}
}
