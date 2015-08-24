package br.com.waiso.test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import br.com.waiso.derose.ManipuladorArquivo;

public class HtmlTest {

	private String diretorio;
	private String arquivo;
	private List<String> arquivos;
	private SortedSet<String> chaves;
	private JsonParser parser;
	private Pattern pattern;
	private Matcher matcher;
	
	private String DESTINO_CHAVES = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose.txt";
	private String DESTINO_TMX = "//Users/fabianorodriguesmatias/Developer/testes/testes/derose-i18n.json";
	
	// Explicando a expressão regular:  
    // data-i18n - serve para bater com o "data-i18n"  
    // ()  - serve para agrupar o que desejo achar para poder usar group(1)  
    // .   - bate com qualquer caracter  
    // *?  - Serve para repetir a expressão anterior (no caso ".") 0 a infinitas vezes,   
    //       mas usando o o método "não-ganancioso" (ou seja, bate com a expressão mais curta, não a mais longa possível).
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
	public void testAjustaChaves() {
		try {
			pattern = Pattern.compile(PADRAO_CHAVES, Pattern.CASE_INSENSITIVE);
			chaves = new TreeSet<String>();
			parser = new JsonParser();
			
			//Map que irá receber os objetos agrupados
			Map<String, JsonObject> agruparChaves = new HashMap<String, JsonObject>();
			
			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
			matcher = pattern.matcher(arquivo);
			while (matcher.find()) {
				String match = matcher.group();
//				match = match.replace("\"", "");
				match = match.replace(">", ".");
				match = match.replace("<", "");
				chaves.add(match);
			}
			
			for (String chave : chaves) {
				String chaveDividida[] = chave.toString().split("\\.");
				
				//Criar o map para depois adicionar
				if (!agruparChaves.containsKey(chaveDividida[0])) {
					agruparChaves.put(chaveDividida[0], new JsonObject());
				}
				
				JsonObject json = new JsonObject();
//				json.add(chaveDividida[1], parser.parse(chaveDividida[2]));
//				json.add(chaveDividida[1], chaveDividida[2]);
				
				//Adicionar ao map criado
				agruparChaves.get(chaveDividida[0]).add(chaveDividida[0], json);
			}
			
			ManipuladorArquivo.getInstance().escritorJson2(DESTINO_TMX, agruparChaves);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testAjustaChaves() {
//		try {
//			pattern = Pattern.compile(PADRAO_CHAVES, Pattern.CASE_INSENSITIVE);
//			chaves = new TreeSet<String>();
//			
//			//Map que irá receber os objetos agrupados
//			Map<String, SortedSet<String>> agruparChaves = new HashMap<String, SortedSet<String>>();
//			
//			arquivo = ManipuladorArquivo.getInstance().leitor(DESTINO_CHAVES);
//			matcher = pattern.matcher(arquivo);
//			while (matcher.find()) {
//				String match = matcher.group();
//				match = match.replace("\"", "");
//				match = match.replace(">", "\":\"");
//				match = match.replace("<", "");
//				chaves.add(match);
//			}
//			
//			for (String chave : chaves) {
//				String chaveDividida[] = chave.toString().split("\\.");
//				
//				//Criar o map para depois adicionar
//				if (!agruparChaves.containsKey(chaveDividida[0])) {
//					agruparChaves.put(chaveDividida[0], new TreeSet<String>());
//				}
//				
//				//Adicionar ao map criado
//				agruparChaves.get(chaveDividida[0]).add(chaveDividida[1]);
//			}
//			
//			ManipuladorArquivo.getInstance().escritorJson(DESTINO_TMX, agruparChaves);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	
	public static void main(String[] args) {
		Map<String, List<String>> myMap = new HashMap<String, List<String>>();
		myMap.put("one", new ArrayList<String>());
		myMap.get("one").add("hello");
		myMap.get("one").add("hello2");
		
		myMap.put("two", new ArrayList<String>());
		myMap.get("two").add("hello");
		

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(myMap);

		System.out.println(json);

//		Type typeOfHashMap = new TypeToken<Map<String, String>>() { }.getType();
//		Map<String, String> newMap = gson.fromJson(json, typeOfHashMap); // This type must match TypeToken
//		System.out.println(newMap.get("one"));
//		System.out.println(newMap.get("two"));
	}
	
}
