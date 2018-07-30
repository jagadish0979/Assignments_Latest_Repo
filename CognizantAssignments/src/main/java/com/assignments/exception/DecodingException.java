package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class DecodingException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a DecodingException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public DecodingException() {
		super();
	}

	/**
	 * Constructs a DecodingException with the specified detail message. A detail
	 * message is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public DecodingException(String msg) {
		super(msg);
	}
}
