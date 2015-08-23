package br.com.waiso.test;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.com.waiso.derose.ManipuladorArquivo;

public class HtmlTest {

	private StringBuilder padrao;
	private String diretorio;
	private String html;
	private List<String> arquivos;
	private SortedSet<String> chaves;
	private Pattern pattern;
	private Matcher matcher;
	
	private String DESTINO = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose.txt"; 

	@Test
	public void testCapturaChaves() {

		try {
			// Endereco que vai salvar as chaves
			pattern = Pattern.compile(padrao.toString(), Pattern.CASE_INSENSITIVE);
			
			diretorio = "//Users/fabianorodriguesmatias/Developer/testes/alterada";

			// Busca dos arquivos existentes no diretório
			arquivos = ManipuladorArquivo.getInstance().buscaArquivosHtmlDiretorio(diretorio);
			
			chaves = new TreeSet<String>();
			for (String arquivo : arquivos) {
				html = ManipuladorArquivo.getInstance().leitor(diretorio, arquivo);
				matcher = pattern.matcher(html);
				while (matcher.find()) {
					chaves.add(matcher.group());
				}
			}
			ManipuladorArquivo.getInstance().escritor(DESTINO, chaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void padrao() {
		padrao = new StringBuilder();
		padrao.append("data-i18n=\"");
		padrao.append("(");
		padrao.append("[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append(")");
		padrao.append("\\.");
		padrao.append("(");
		padrao.append("[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padrao.append(")");
		padrao.append("\"");
	}
}
