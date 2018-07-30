package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class EncodingException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a EncodingException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public EncodingException() {
		super();
	}

	/**
	 * Constructs a EncodingException with the specified detail message. A detail
	 * message is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public EncodingException(String msg) {
		super(msg);
	}
}
