package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class ParserException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ParserException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public ParserException() {
		super();
	}

	/**
	 * Constructs a ParserException with the specified detail message. A detail
	 * message is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public ParserException(String msg) {
		super(msg);
	}
}
