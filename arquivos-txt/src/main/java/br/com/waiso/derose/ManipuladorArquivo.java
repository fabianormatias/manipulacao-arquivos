package br.com.waiso.derose;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
//					buffWrite.append(" \"" + chave.toString() + "\": ");
					buffWrite.append(" \"" + chave.toString() + "\" ");
				} else {
//					buffWrite.append(" ,\"" + chave.toString() + "\": ");
					buffWrite.append(" ,\"" + chave.toString() + "\" ");
				}
				cont2++;
			}
			cont1++;
			buffWrite.append("}");
		}
		buffWrite.append("}");
		buffWrite.close();
	}
	
	public void escritorJson2(String path, Map<String, JsonObject> agruparChaves) throws IOException {
//		JsonWriter jsonWrite = new JsonWriter(new FileWriter(path));
//		jsonWrite.beginObject();
		Gson gson = new GsonBuilder().create();
//		gson.toJson(agruparChaves);
		System.out.println(gson.toJson(agruparChaves));
//		for (Map.Entry<String, SortedSet<String>> chaves : agruparChaves.entrySet()) {
////			jsonWrite.name(chaves.getKey());
////			jsonWrite.
//			SortedSet<String> chavesAgrupadas = chaves.getValue();
//			for (String chave : chavesAgrupadas) {
////				jsonWrite.value(chave);
//			}
//		}
//		jsonWrite.close();
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
