package br.com.g6.escrita.exemplo2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyApp {
	
	public static void main(String[] args) {
		try {
			OutputStream output = new FileOutputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt");
			byte[] bytes = new byte[] { 'A', 'B', 'D', 'E' };
			int count = bytes.length;
			while (count >= 0) {
				output.write(bytes);
				count--;
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}