package kh.java.exception;

/**
 * 
 * 커스텀 예외클래스 작성
 * - Unchecked Exception 생성할 경우 RuntimeException 상속
 * - Checked Exception 생성할 경우 Exception 상속
 *
 */
public class UnderAgeException extends RuntimeException {

	public UnderAgeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnderAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UnderAgeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnderAgeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UnderAgeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
