package br.com.g6.leitura.escrita.exemplo2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MyApp {

	public static void main(String[] args) {

		String caminho = "C://temp//";
		String arquivo = "exemplo1.properties";

		ManipuladorProperties mp = new ManipuladorProperties();
		Properties configSistema = mp.carregarPropriedades(caminho + arquivo);

		// Lista as propriedades
		mp.imprimir(configSistema);

		// Recupera uma propriedade específica
		System.out.println(mp.getPropriedade(configSistema, "prop.server.login"));

		// Inclui uma propriedade nova
		mp.incluirPropriedade("data", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), configSistema);

		// Grava o arquivo, neste caso com outro nome
		mp.salvar(configSistema, caminho + "novas_propriedades.properties");
	}

}
