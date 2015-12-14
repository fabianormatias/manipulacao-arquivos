package com.g6.escrita.modelocobol3;

import com.g6.escrita.modelocobol3.ModeloCobol.TipoModelo;

public class DadosEmpresa {

	@ModeloCobol(tipoModelo = TipoModelo.CHAR, tamanho = "4")
	private String codigoEmpresa;

	@ModeloCobol(tipoModelo = TipoModelo.CHAR, tamanho = "6")
	private String branco1;

	@ModeloCobol(tipoModelo = TipoModelo.PIC, tamanho = "6")
	private String dataMovimento;

	public DadosEmpresa() {
	}

	public DadosEmpresa(String codigoEmpresa, String branco1, String dataMovimento) {
		this.codigoEmpresa = codigoEmpresa;
		this.branco1 = branco1;
		this.dataMovimento = dataMovimento;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getBranco1() {
		return branco1;
	}

	public void setBranco1(String branco1) {
		this.branco1 = branco1;
	}

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
}