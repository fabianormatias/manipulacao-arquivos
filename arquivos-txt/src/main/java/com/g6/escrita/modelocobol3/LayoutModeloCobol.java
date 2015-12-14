package com.g6.escrita.modelocobol3;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.g6.escrita.modelocobol1.StringUtils;
import com.g6.escrita.modelocobol3.ModeloCobol.TipoModelo;
import com.g6.escrita.modelocobol3.ReflectionUtils.TipoMetodo;

public class LayoutModeloCobol {

	private List<String> itens = new ArrayList<String>();

	public LayoutModeloCobol() {
	}

	public <T> void add(T objeto) throws ModeloCobolException {
		try {
			Class<?> classe = objeto.getClass();
			Field[] fieldList = classe.getDeclaredFields();
			Method[] methodList = classe.getDeclaredMethods();
			if (!ArrayUtils.isEmpty(fieldList) && !ArrayUtils.isEmpty(methodList)) {
				for (Field field : classe.getDeclaredFields()) {
					Boolean temAnotacao = field.isAnnotationPresent(ModeloCobol.class);
					if (temAnotacao) {
						Method metodoGet = classe.getDeclaredMethod(ReflectionUtils.nomeMetodo(TipoMetodo.GET, field, methodList), null);
						Object atributo = metodoGet.invoke(objeto, new Object[] {});
						Class<?> tipoAtributo = field.getType();
						Method metodoSet = classe.getDeclaredMethod(ReflectionUtils.nomeMetodo(TipoMetodo.SET, field, methodList), tipoAtributo);
						ModeloCobol anotacao = field.getAnnotation(ModeloCobol.class);
						TipoModelo tipoModelo = anotacao.tipoModelo();
						if (tipoModelo == TipoModelo.CHAR) {
							atributo = tipoChar(atributo.toString(), anotacao.tamanho());
						} else if (tipoModelo == TipoModelo.PIC) {
							atributo = tipoPic(atributo.toString(), anotacao.tamanho());
						}
						metodoSet.invoke(objeto, atributo);
					}
				}
			}
			itens.add(addComFormato(objeto));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ModeloCobolException(e.getMessage());
		}
	}
	
	private String tipoChar(String objeto, String tamanho) {
		String valor = "";
		Integer tam = Integer.parseInt(tamanho);
		objeto = (objeto == null) ? "" : objeto;
		valor = objeto.toString();
		if (valor.length() > tam) {
			valor = valor.substring(0, tam);
		} else {
			valor = StringUtils.rightPad(valor.toString(), tam);
		}
		return valor;
	}

	private String tipoPic(String objeto, String tamanho) {
		String valor = "";
		Integer obj = Integer.parseInt(objeto);
		Integer tam = Integer.parseInt(tamanho);
		obj = (Integer) ((obj == null) ? "0" : obj);
		if (obj instanceof Number) {
			Number numero = (Number) obj;
			valor = numero.toString();
			valor = valor.replaceAll("[.,]", "");
		} else {
			valor = String.valueOf(objeto);
		}
		valor = StringUtils.leftPad(valor, "0", tam);
		return valor;
	}
	
	private <T> String addComFormato(T objeto) {
		StringBuilder sb = new StringBuilder();
		DadosEmpresa obj = (DadosEmpresa)objeto;
		sb.append(obj.getCodigoEmpresa());
		sb.append(obj.getBranco1());
		sb.append(obj.getDataMovimento());
		return sb.toString();
	}

	public List<String> getItens() {
		return itens;
	}
	
	public String get() {
		return "";
	}

}
