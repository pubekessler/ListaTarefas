package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "ListaNullException")
public class ListaNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9112692989463323760L;

}
