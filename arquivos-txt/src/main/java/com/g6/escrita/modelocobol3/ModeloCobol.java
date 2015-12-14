package com.g6.escrita.modelocobol3;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModeloCobol {
	
	public String tamanho();

	public enum TipoModelo {
		CHAR, PIC;
	}

	TipoModelo tipoModelo() default TipoModelo.CHAR;

}