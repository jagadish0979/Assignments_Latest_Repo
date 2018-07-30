package com.assignments.util;

import java.text.DecimalFormat;
import java.util.Base64;

/**
 * @author Jagadish Anala
 *
 */
public class AssignmentsUtil {

	public static Double Round(Double val) {
		DecimalFormat format = new DecimalFormat("##.00");
		return Double.valueOf(format.format(val));
	}

	public static String encode(String stringToEncode) throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(stringToEncode.getBytes());
	}

	public static String decode(String stringToDecode) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(stringToDecode));
	}
}
