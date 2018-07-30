package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a LoginException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public LoginException() {
		super();
	}

	/**
	 * Constructs a LoginException with the specified detail message. A detail
	 * message is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public LoginException(String msg) {
		super(msg);
	}
}
