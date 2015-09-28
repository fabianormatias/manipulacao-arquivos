package br.com.g6.leitura.exemplo3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Um exemplo prático: Se o seu arquivo possui 32768 bytes, para que um
 * FileInputStream possa ler ele por completo, ele precisará fazer 32768
 * chamadas ao Sistema Operacional. Com um BufferedInputStream você precisará de
 * apenas quadro chamadas, isso porque o BufferedInputStream armazena 8192 bytes
 * em um buffer e os utiliza quando precisa. Resumindo, você deve usar o
 * BufferedInputStream como um wrapper para o FileInputStream quando desejar
 * ganhar mais velocidade.
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
			inputstream = new BufferedInputStream(new FileInputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt"));
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