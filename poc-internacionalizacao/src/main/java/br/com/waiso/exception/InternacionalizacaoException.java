package br.com.waiso.exception;

public class InternacionalizacaoException extends Exception {

	private static final long serialVersionUID = 2600320622042238730L;

	public InternacionalizacaoException(String msg) {
		super(msg);
	}

	public InternacionalizacaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
