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
import java.util.SortedSet;

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
	public String leitor(String diretorio, String arquivo) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		File dir = new File(diretorio);
		File arq = new File(dir, arquivo);

		BufferedReader buffRead = new BufferedReader(new FileReader(arq));
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
