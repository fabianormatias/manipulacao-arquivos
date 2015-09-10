package br.com.waiso.derose;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ManipuladorArquivo {
	
	private static ManipuladorArquivo instance = new ManipuladorArquivo();

	public static ManipuladorArquivo getInstance() {
		return instance;
	}

	public String leitor(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
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

	public void escritor(String path, SortedSet<String> chaves) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (String chave : chaves) {
			buffWrite.append(chave + "\n");
		}
		buffWrite.close();
	}
	
	public void escritorJson(String path, SortedSet<String> chaves) throws IOException {
		JsonParser parser = new JsonParser();
		
		//Map que irá receber os objetos agrupados
		Map<String, List<Object>> agruparChaves = new HashMap<String, List<Object>>();
		
		for (String chave : chaves) {
			String chaveDividida[] = chave.toString().split("\\.");
			
			//TODO VALIDAR ESSA MERDA!!!
			if (chaveDividida.length == 5) {
				System.out.println("Ajustar...");
			}
			
			//Criar o map para depois adicionar
			if (!agruparChaves.containsKey(chaveDividida[1])) {
				agruparChaves.put(chaveDividida[1], new ArrayList<Object>());
			}
			
			JsonObject jsonObject = new JsonObject();
			String valor = "\"" + chaveDividida[3] + "\"";
			JsonElement jsonElement = parser.parse(valor);
			
			jsonObject.add(chaveDividida[2], jsonElement);
			
			//Adicionar ao map criado
			agruparChaves.get(chaveDividida[1]).add(jsonObject);
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(agruparChaves);

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.write(json);
		buffWrite.close();
	}
	
	public void escritorTmx(String path, SortedSet<String> chaves) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		
		buffWrite.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE tmx SYSTEM \"tmx14.dtd\"><tmx version=\"1.4\"><body>");
		for (String chave : chaves) {
			String chaveDividida[] = chave.toString().split(">");
			buffWrite.append("<tu tuid=\"" + chaveDividida[0] + "\">");
			buffWrite.append("<tuv xml:lang=\"pt_BR\"><seg>" + chaveDividida[1] + "</seg></tuv></tu>");
		}
		buffWrite.append("</body></tmx>");
		buffWrite.close();
	}
	
	public List<String> buscaArquivosHtmlDiretorios(String pasta, String extensao, String... diretorios) {
		List<String> arquivos = new ArrayList<String>();
		
		if (diretorios != null) {
			for (String diretorio : diretorios) {
				arquivos = buscaArquivosHtmlDiretorio(pasta, extensao, diretorio);
			}
		} else {
			arquivos = buscaArquivosHtmlDiretorio(pasta, extensao, null);
		}
		return arquivos;
	}
	
	public List<String> buscaArquivosHtmlDiretorio(String pasta, String extensao, String diretorio) {
		List<String> arquivos = new ArrayList<String>();
		
		File dir1 = new File(pasta);
		File[] files1 = dir1.listFiles();
		for (File file1 : files1) {
			if (file1.isDirectory() && diretorio != null) {
				if (diretorio.equals(file1.getName())) {
					File dir2 = new File(file1.getPath());
					File[] files2 = dir2.listFiles();
					for (File file2 : files2) {
						if (file2.getName().endsWith(extensao)) {
							arquivos.add(file2.getPath());
						}
					}
				}
			} else {
				if (file1.getName().endsWith(extensao)) {
					arquivos.add(file1.getPath());
				}
			}
		}
		return arquivos;
	}
	
}