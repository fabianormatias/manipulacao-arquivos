package com.g6.escrita.modelocobol3;

public class ModeloCobolException extends Exception {

	private static final long serialVersionUID = 2600320622042238730L;

	public ModeloCobolException(String msg) {
		super(msg);
	}

	public ModeloCobolException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
