package br.com.waiso.derose;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.google.gson.stream.JsonWriter;

public class ManipuladorArquivo {

	private static ManipuladorArquivo instance = new ManipuladorArquivo();

	public static ManipuladorArquivo getInstance() {
		return instance;
	}

	/**
	 * Retorna uma String com as informações do arquivo
	 * 
	 * @param diretorio
	 * @param arquivo
	 * @throws IOException
	 */
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

	/**
	 * Pega a lista com os dados e salva no arquivo
	 * 
	 * @param path
	 * @param chaves
	 * @throws IOException
	 */
	public void escritor(String path, SortedSet<String> chaves) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		for (String chave : chaves) {
			buffWrite.append(chave + "\n");
		}
		buffWrite.close();
	}
	
	/**
	 * 
	 * @param path
	 * @param chaves
	 * @throws IOException
	 */
	public void escritorJson(String path, Map<String, SortedSet<String>> agruparChaves) throws IOException {
		int cont1 = 0;
		int cont2 = 0;
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.append("{ ");
		for (Map.Entry<String, SortedSet<String>> chaves : agruparChaves.entrySet()) {
			cont2 = 0;
			if (cont1 == 0) {
				buffWrite.append(" \"" + chaves.getKey() + "\": {");
			} else {
				buffWrite.append(" ,\"" + chaves.getKey() + "\": {");
			}
			
			SortedSet<String> chavesAgrupadas = chaves.getValue();
			for (String chave : chavesAgrupadas) {
				if (cont2 == 0) {
					buffWrite.append(" \"" + chave.toString() + "\": ");
				} else {
					buffWrite.append(" ,\"" + chave.toString() + "\": ");
				}
				cont2++;
			}
			cont1++;
			buffWrite.append("}");
		}
		buffWrite.append("}");
		buffWrite.close();
	}
	
	/**
	 * 
	 * @param path
	 * @param chaves
	 * @throws IOException
	 */
	public void escritorJson2(String path, Map<String, SortedSet<String>> agruparChaves) throws IOException {
		JsonWriter jsonWrite = new JsonWriter(new FileWriter(path));
		jsonWrite.beginObject();
		for (Map.Entry<String, SortedSet<String>> chaves : agruparChaves.entrySet()) {
//			jsonWrite.name(chaves.getKey());
//			jsonWrite.
			SortedSet<String> chavesAgrupadas = chaves.getValue();
			for (String chave : chavesAgrupadas) {
//				jsonWrite.value(chave);
			}
		}
		jsonWrite.close();
	}
	
	/**
	 * 
	 * 
	 * @param diretorio
	 * @return
	 */
	public List<String> buscaArquivosHtmlDiretorio(String diretorio) {
		List<String> arquivos = new ArrayList<String>();

		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return file.getName().endsWith(".html");
			}
		};

		File dir = new File(diretorio);
		File[] files = dir.listFiles(filter);
		for (File file : files) {
			if (file.exists()) {
				arquivos.add(file.getName());
			}
		}
		return arquivos;
	}

}
