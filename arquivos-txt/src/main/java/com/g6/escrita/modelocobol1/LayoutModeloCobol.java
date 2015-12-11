package com.g6.escrita.modelocobol1;

import java.util.ArrayList;
import java.util.List;

public class LayoutModeloCobol {

	private String chave;
	private String valor;

	private List<LayoutModeloCobol> itens = new ArrayList<LayoutModeloCobol>();

	public LayoutModeloCobol() {
	}

	public LayoutModeloCobol(String chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public void add(String chave, TipoModeloCobol tipo, Integer tamanho, Object objeto) {
		String valor = "";
		if (tipo.equals(TipoModeloCobol.CHAR)) {
			objeto = (objeto == null) ? "" : objeto;
			valor = objeto.toString();
			if (valor.length() > tamanho) {
				valor = valor.substring(0, tamanho);
			} else {
				valor = StringUtils.rightPad(valor.toString(), tamanho);
			}
		} else if ((tipo.equals(TipoModeloCobol.PIC))) {
			objeto = (objeto == null) ? "0" : objeto;
			if (objeto instanceof Number) {
				Number numero = (Number) objeto;
				valor = numero.toString();
				valor = valor.replaceAll("[.,]", "");
			} else {
				valor = String.valueOf(objeto);
			}
			valor = StringUtils.leftPad(valor, "0", tamanho);
		}
		itens.add(new LayoutModeloCobol(chave, valor));
	}

	public List<LayoutModeloCobol> getItens() {
		return itens;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LayoutModeloCobol [chave=" + chave + ", valor=" + valor + "]";
	}

}
