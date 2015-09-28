package br.com.g6.leitura.exemplo4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyApp {
	
	public static void main(String[] args) {
		InputStream inputstream;
		try {
			// Cria uma instância de FileInputStream passando como argumento a
			// localização do Stream quegostaríamosde ler
			inputstream = new BufferedInputStream(new FileInputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt"));
			byte[] dataAsByte = new byte[2];
			// O método read() do InputStream retorna um valor inteiro que
			// contém obyte correspondente que foi lido
			// criar um array de byte chamado dataAsByte com tamanho igual a 2
			inputstream.read(dataAsByte);
			for (int i = 0; i < 2; i++) {
				System.out.println((char) dataAsByte[i]);
			}
			inputstream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}