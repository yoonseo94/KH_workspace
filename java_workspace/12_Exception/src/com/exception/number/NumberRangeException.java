package com.exception.number;

/**
 * 
 * 사용자정의 예외클래스
 *
 */
public class NumberRangeException extends RuntimeException {

	public NumberRangeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NumberRangeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NumberRangeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NumberRangeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NumberRangeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
