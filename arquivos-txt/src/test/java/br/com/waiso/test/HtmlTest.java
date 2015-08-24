package br.com.waiso.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.com.waiso.derose.ManipuladorArquivo;

public class HtmlTest {

	private StringBuilder padrao;
	private StringBuilder padraoChaves;
	private String diretorioWeb;
	private String diretorioPhone;
	private String arquivo;
	private List<String> arquivosWeb;
	private List<String> arquivosPhone;
	private SortedSet<String> chaves;
	private Pattern pattern;
	private Matcher matcher;
	
	private String DESTINO_CHAVES = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose.txt";
	private String DESTINO_TMX = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose-chaves.txt";

	@Test
	public void testCapturaChaves() {
		try {
			pattern = Pattern.compile(padrao.toString(), Pattern.CASE_INSENSITIVE);
			
			diretorioWeb = "//Users/fabianorodriguesmatias/Developer/testes/alterada";
			diretorioPhone = "//Users/fabianorodriguesmatias/Developer/testes/alterada/phone";

			// Busca dos arquivos existentes no diretório
			arquivosWeb = ManipuladorArquivo.getInstance().buscaArquivosHtmlDiretorio(diretorioWeb);
			arquivosPhone = ManipuladorArquivo.getInstance().buscaArquivosHtmlDiretorio(diretorioPhone);
			
			chaves = new TreeSet<String>();
			// Chave arquivo web
			for (String arquivoWeb : arquivosWeb) {
				arquivo = ManipuladorArquivo.getInstance().leitor(diretorioWeb + "/" +  arquivoWeb);
				matcher = pattern.matcher(arquivo);
				while (matcher.find()) {
					chaves.add(matcher.group());
				}
			}
			// Chave arquivo web
			for (String arquivoPhone : arquivosPhone) {
				arquivo = ManipuladorArquivo.getInstance().leitor(diretorioPhone + "/" + arquivoPhone);
				matcher = pattern.matcher(arquivo);
				while (matcher.find()) {
					chaves.add(matcher.group());
				}
			}
			ManipuladorArquivo.getInstance().escritor(DESTINO_CHAVES, chaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAjustaChaves() {
		try {
			pattern = Pattern.compile(padraoChaves.toString(), Pattern.CASE_INSENSITIVE);
			chaves = new TreeSet<String>();
			
			//Map que irá receber os objetos agrupados
			Map<String, SortedSet<String>> agruparChaves = new HashMap<String, SortedSet<String>>();
			
			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
			matcher = pattern.matcher(arquivo);
			while (matcher.find()) {
//				chaves.add(matcher.group().replace("\"", " ") + "=");
				chaves.add(matcher.group().replace("\"", " "));
			}
			
			for (String chave : chaves) {
				String chaveDividida[] = chave.toString().split("\\.");
				
				//Criar o map para depois adicionar
				if (!agruparChaves.containsKey(chaveDividida[0])) {
					agruparChaves.put(chaveDividida[0], new TreeSet<String>());
				}
				
				//Adicionar ao map criado
				agruparChaves.get(chaveDividida[0]).add(chaveDividida[1]);
			}
			
			ManipuladorArquivo.getInstance().escritorJson2(DESTINO_TMX, agruparChaves);
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
	
	@Before
	public void padraoChaves() {
		padraoChaves = new StringBuilder();
		padraoChaves.append("[^\"]");
		padraoChaves.append("(");
		padraoChaves.append("[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append(")");
		padraoChaves.append("\\.");
		padraoChaves.append("(");
		padraoChaves.append("[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append("|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*");
		padraoChaves.append(")");
		padraoChaves.append("\"");
	}
}
