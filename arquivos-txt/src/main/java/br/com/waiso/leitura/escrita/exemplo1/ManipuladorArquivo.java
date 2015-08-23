package br.com.waiso.leitura.escrita.exemplo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ManipuladorArquivo {

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				System.out.println(linha);
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void pesquisar(String path) throws IOException {
		String linha = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String pal = JOptionPane.showInputDialog("Digite o nome da palavra a procurar:");
		while ((linha = br.readLine()) != null) {
			if (linha.contains(pal)) {
				System.out.println(linha);
			}
		}
	}

	//TODO Fazer funcionar
//	Não está funcionando
	public static void quantidadeLinhas(String path) throws IOException {
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(path));
		linhaLeitura.skip(path.length());
		int qtdLinha = linhaLeitura.getLineNumber();
		System.out.println("Quantidade de linhas: " + qtdLinha);
	}

}