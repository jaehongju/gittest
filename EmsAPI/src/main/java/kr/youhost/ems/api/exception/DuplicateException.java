package kr.youhost.ems.api.exception;

public class DuplicateException extends Exception {

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959887683791618019L;

}
