package br.com.waiso.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.waiso.exception.InternacionalizacaoException;

public class ManipuladorArquivo {
	
	private Map<String, String> chaves;
	
	private String PADRAO_CHAVE = "\"(.*?)>";
	private String PADRAO_VALOR = ">(.*?)<";
	
	private static ManipuladorArquivo instance = new ManipuladorArquivo();

	public static ManipuladorArquivo getInstance() {
		return instance;
	}

	public String leitor(String diretorio) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader buffRead = new BufferedReader(new FileReader(diretorio));
		String linha = "";
		while (true) {
			if (linha != null) {
				sb.append(linha);
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		return sb.toString();
	}

	public void escritor(String diretorio, SortedSet<String> chaves) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(diretorio));
		for (String chave : chaves) {
			buffWrite.append(chave + "\n");
		}
		buffWrite.close();
	}
	
	public List<String> buscaArquivosHtmlDiretorios(String diretorio, String extensao) throws InternacionalizacaoException {
		List<String> arquivos = new ArrayList<String>();
		if (diretorio != null) {
			File dir = new File(diretorio);
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.getName().endsWith(extensao)) {
					arquivos.add(file.getPath());
				}
			}
		}
		validarExisteArquivos(arquivos, extensao);
		return arquivos;
	}
	
	public void escritorJson(String diretorio, Map<String, String> chaves) throws IOException {
		JsonParser parser = new JsonParser();
		
		//Map que irá receber os objetos agrupados
		Map<String, List<Object>> agruparChaves = new HashMap<String, List<Object>>();

		for (Map.Entry<String, String> chavesValores : chaves.entrySet()) {
			String chaveDividida[] = chavesValores.getKey().split("\\.");
			
			//Criar o map para depois adicionar
			if (!agruparChaves.containsKey(chaveDividida[1])) {
				agruparChaves.put(chaveDividida[1], new ArrayList<Object>());
			}
			
			JsonObject jsonObject = new JsonObject();
			String valor = "\"" + chavesValores.getValue() + "\"";
			JsonElement jsonElement = parser.parse(valor);
			
			jsonObject.add(chaveDividida[2], jsonElement);
			
			//Adicionar ao map criado
			agruparChaves.get(chaveDividida[1]).add(jsonObject);
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(agruparChaves);

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(diretorio));
		buffWrite.write(json);
		buffWrite.close();
	}
	
	public void escritorTmx(String diretorio, Map<String, String> chaves) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(diretorio));
		
		buffWrite.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE tmx SYSTEM \"tmx14.dtd\"><tmx version=\"1.4\"><body>");
		for (Map.Entry<String, String> chavesValores : chaves.entrySet()) {
			buffWrite.append("<tu tuid=\"" + chavesValores.getKey() + "\">");
			buffWrite.append("<tuv xml:lang=\"pt_BR\"><seg>" + chavesValores.getValue() + "</seg></tuv></tu>");
		}
		buffWrite.append("</body></tmx>");
		buffWrite.close();
	}
	
	public Map<String, String> capturaChavesEValores(String diretorio, String regex) throws InternacionalizacaoException {
		chaves = new HashMap<String, String>();
		try {
			SortedSet<String> chaveValorIncompleto = new TreeSet<String>();
			Pattern padrao = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			List<String> arquivos = buscaArquivosHtmlDiretorios(diretorio, ".html");
			for (String arq : arquivos) {
				String arquivo = leitor(arq);
				Matcher matcher = padrao.matcher(arquivo);
				while (matcher.find()) {
					chaveValorIncompleto.add(matcher.group());
				}
			}
			chaves = excluirChavesRepetidas(chaveValorIncompleto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chaves;
	}
	
	public Map<String, String> excluirChavesRepetidas(SortedSet<String> chaveValorIncompleto) throws InternacionalizacaoException {
		Pattern padraoChave = Pattern.compile(PADRAO_CHAVE, Pattern.CASE_INSENSITIVE);
		Pattern padraoValor = Pattern.compile(PADRAO_VALOR, Pattern.CASE_INSENSITIVE);
		for (String chaveValor : chaveValorIncompleto) {
			Matcher matcherChave = padraoChave.matcher(chaveValor);
			String chave = null;
			while (matcherChave.find()) {
				chave = matcherChave.group().replace(">", "").replace("\"", "").trim();
				validarTamanhoChave(chave);
			}
			
			Matcher matcherValor = padraoValor.matcher(chaveValor);
			String valor = null;
			while (matcherValor.find()) {
				valor = matcherValor.group().replace(">", "").replace("<", "").trim();
			}
			chaves.put(chave, valor);
		}
		return chaves;
	}
	
	private void validarTamanhoChave(String chave) throws InternacionalizacaoException {
		String chaveDividida[] = chave.toString().split("\\.");
		if (chaveDividida.length > 3) {
			throw new InternacionalizacaoException("Chave com tamanho acima do permitido: " + chave);
		}
	}
	
	private void validarExisteArquivos(List<String> arquivos, String extensao) throws InternacionalizacaoException {
		if (arquivos.isEmpty()) {
			throw new InternacionalizacaoException("Não tem arquivos com a extensão " + extensao + " no diretório informado!");
		}
	}
	
}