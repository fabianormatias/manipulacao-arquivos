package br.com.waiso.test;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import br.com.waiso.derose.ManipuladorArquivo;

public class HtmlTest {

	private String diretorio;
	private String arquivo;
	private List<String> arquivos;
	private SortedSet<String> chaves;
	private Pattern pattern;
	private Matcher matcher;
	
	private String DESTINO_CHAVES = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose.txt";
	private String DESTINO_JSON = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose-i18n.json";
	private String DESTINO_TMX = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose-i18n-pt_BR.tmx";
	private String DESTINO_TABELA = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose-i18n.txt";
	
	// Explicando a expressão regular:  
    // data-i18n - serve para bater com o "data-i18n"  
    // ()  - serve para agrupar o que desejo achar para poder usar group(1)  
    // .   - bate com qualquer caracter  
    // *?  - Serve para repetir a expressão anterior (no caso ".") 0 a infinitas vezes   
	private String PADRAO = "data-i18n(.*?)<";
	private String PADRAO_CHAVES = "\"(.*?)<";
	
	@Test
	public void testCapturaChaves() {
		try {
			pattern = Pattern.compile(PADRAO, Pattern.CASE_INSENSITIVE);
			
			diretorio = "//Users/fabianorodriguesmatias/Developer/testes/alterada";

			// Busca dos arquivos existentes no diretório
			arquivos = ManipuladorArquivo.getInstance().buscaArquivosHtmlDiretorio(diretorio, ".html", "phone");
			
			chaves = new TreeSet<String>();
			// Chave arquivo web
			for (String arq : arquivos) {
				arquivo = ManipuladorArquivo.getInstance().leitor(arq);
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
	public void testGeraJson() {
		try {
			pattern = Pattern.compile(PADRAO_CHAVES, Pattern.CASE_INSENSITIVE);
			chaves = new TreeSet<String>();
			
			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
			matcher = pattern.matcher(arquivo);
			while (matcher.find()) {
				String match = matcher.group();
				match = match.replace("\"", "");
				match = match.replace(">", ".");
				match = match.replace("<", "");
				chaves.add(match);
			}
			
			ManipuladorArquivo.getInstance().escritorJson(DESTINO_JSON, chaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGeraTmx() {
		try {
			pattern = Pattern.compile(PADRAO_CHAVES, Pattern.CASE_INSENSITIVE);
			chaves = new TreeSet<String>();
			
			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
			matcher = pattern.matcher(arquivo);
			while (matcher.find()) {
				String match = matcher.group();
				match = match.replace("\"", "");
				match = match.replace("<", "");
				chaves.add(match);
			}
			
			ManipuladorArquivo.getInstance().escritorTmx(DESTINO_TMX, chaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGeraTabela() {
		try {
			pattern = Pattern.compile(PADRAO_CHAVES, Pattern.CASE_INSENSITIVE);
			chaves = new TreeSet<String>();
			
			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
			matcher = pattern.matcher(arquivo);
			while (matcher.find()) {
				String match = matcher.group();
				match = match.replace("\"", "");
				match = match.replace(">", "=");
				match = match.replace("<", "");
				chaves.add(match);
			}
			
			ManipuladorArquivo.getInstance().escritor(DESTINO_TABELA, chaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}