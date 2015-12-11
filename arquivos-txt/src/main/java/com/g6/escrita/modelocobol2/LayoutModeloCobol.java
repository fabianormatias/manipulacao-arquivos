package com.g6.escrita.modelocobol2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LayoutModeloCobol {

	private String chave;
	private String valor;

	private Properties props = new Properties();

	private List<LayoutModeloCobol> itens = new ArrayList<LayoutModeloCobol>();

	public LayoutModeloCobol(String properties) throws ModeloCobolException {
		try {
			load(properties);
		} catch (Exception e) {
			String mensagem = "Arquivo properties não encontrado!";
			throw new ModeloCobolException(mensagem, e.getCause());
		}
	}

	public LayoutModeloCobol(String chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}
	
    private void load(String properties) throws Exception {
		InputStream file = LayoutModeloCobol.class.getResourceAsStream(properties);
		props.load(file);
    }
    
	public void add(String chave, Object objeto) {
		String valor = "";
		String info = props.getProperty(chave);
		String[] infos = info.split("[*]");
		String tipo = infos[0].trim();
		Integer tamanho = Integer.parseInt(infos[1].trim());
		if (tipo.equals("CHAR")) {
			objeto = (objeto == null) ? "" : objeto;
			valor = objeto.toString();
			if (valor.length() > tamanho) {
				valor = valor.substring(0, tamanho);
			} else {
				valor = StringUtils.rightPad(valor.toString(), tamanho);
			}
		} else if ((tipo.equals("PIC"))) {
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
