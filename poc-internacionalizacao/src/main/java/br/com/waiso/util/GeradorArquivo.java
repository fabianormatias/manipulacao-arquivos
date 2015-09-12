package br.com.waiso.util;

import java.io.IOException;
import java.util.Map;

import br.com.waiso.exception.InternacionalizacaoException;

public class GeradorArquivo {

	private Map<String, String> chaves;

	// Explicando a expressão regular:
	// data-i18n - serve para bater com o "data-i18n"
	// () - serve para agrupar o que desejo achar para poder usar group(1)
	// . - bate com qualquer caracter
	// *? - Serve para repetir a expressão anterior (no caso ".") 0 a infinitas
	// vezes
	private String PADRAO = "data-i18n(.*?)<";

	private String ARQUIVO_JSON = "/derose-i18n.json";
	private String ARQUIVO_TMX = "/derose-i18n-pt_BR.tmx";

	private static GeradorArquivo instance = new GeradorArquivo();

	public static GeradorArquivo getInstance() {
		return instance;
	}

	public void geraJson(String diretorio) throws InternacionalizacaoException, IOException {
		chaves = ManipuladorArquivo.getInstance().capturaChavesEValores(diretorio, PADRAO);
		ManipuladorArquivo.getInstance().escritorJson(diretorio + ARQUIVO_JSON, chaves);
	}

	public void geraTmx(String diretorio) throws InternacionalizacaoException, IOException {
		chaves = ManipuladorArquivo.getInstance().capturaChavesEValores(diretorio, PADRAO);
		ManipuladorArquivo.getInstance().escritorTmx(diretorio + ARQUIVO_TMX, chaves);
	}

}