package com.g6.escrita.modelocobol3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @author fabianomatias
 * @email fabianormatias@gmail.com
 * @since 30/10/2015
 *
 */
public class ReflectionUtils {

	public enum TipoMetodo {
		GET, SET;
	}

	public static String nomeMetodo(TipoMetodo tipoMetodo, Field atributo, Method[] metodos) {
		String nomeMetodo = null;
		String tipo = tipoMetodo.equals(TipoMetodo.GET) ? "get" : "set";
		for (int i = 0; i < metodos.length; i++) {
			if (metodos[i].getName().startsWith(tipo) && (atributo.getName().length() + tipo.length()) == metodos[i].getName().length() && atributo.getName().equalsIgnoreCase(metodos[i].getName().substring(tipo.length()))) {
				nomeMetodo = metodos[i].getName().toString();
				break;
			}
		}
		return nomeMetodo;
	}

}