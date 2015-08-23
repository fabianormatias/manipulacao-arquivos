package br.com.waiso.leitura.escrita.exemplo1;

import java.io.IOException;

public class Principal {
	
	public static void main(String args[]) throws IOException {
		
		String path = "//Users/fabianorodriguesmatias/Developer/testes/testes/teste1.txt"; 
		ManipuladorArquivo.escritor(path);
		ManipuladorArquivo.leitor(path);
		ManipuladorArquivo.pesquisar(path);
		ManipuladorArquivo.quantidadeLinhas(path);
		
	}
	
}