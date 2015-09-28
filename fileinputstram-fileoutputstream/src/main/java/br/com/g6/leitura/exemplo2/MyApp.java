package br.com.g6.leitura.exemplo2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * O FileInputStream nos permite ler um arquivo qualquer e retornar os dados em
 * byte.
 * 
 * @author fabianorodriguesmatias
 *
 */
public class MyApp {

	public static void main(String[] args) {
		InputStream inputstream;
		try {
			// Cria uma instância de FileInputStream passando como argumento a
			// localização do Stream quegostaríamosde ler
			inputstream = new FileInputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt");
			// O método read() do InputStream retorna um valor inteiro que
			// contém obyte correspondente que foi lido
			int data = inputstream.read();
			while (data != -1) {
				System.out.println((char) data);
				data = inputstream.read();
			}
			inputstream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
