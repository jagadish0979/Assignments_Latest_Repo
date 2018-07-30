package com.assignments.exception;

import java.util.Date;

import javax.security.auth.login.LoginException;
import javax.xml.bind.UnmarshalException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignments.domain.ErrorDetails;

/**
 * @author Jagadish Anala
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private Environment env;

	@ExceptionHandler(LoginException.class)
	public final ResponseEntity<ErrorDetails> handleLoginException(WebRequest request, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		logger.error("LoginException ErrorDetails: [ " + errorDetails.toString() + " ]");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ParserException.class)
	public final ResponseEntity<ErrorDetails> handleParserException(WebRequest request, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		logger.error("Parser Exception Parsing ErrorDetails: [ " + errorDetails.toString() + " ]");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnmarshalException.class)
	public final ResponseEntity<ErrorDetails> handleUnmarshalException(WebRequest request, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), env.getProperty("error.file.invalid"),
				request.getDescription(false));
		logger.error("UnmarshalException ErrorDetails: [ " + errorDetails.toString() + " ]");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(WebRequest request, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

		if (ex.getMessage() != null && ex.getMessage().contains("For input string")) {
			errorDetails = new ErrorDetails(new Date(), env.getProperty("error.file.invalid"),
					request.getDescription(false));
		}

		logger.error("Exception ErrorDetails: [ " + errorDetails.toString() + " ]");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}