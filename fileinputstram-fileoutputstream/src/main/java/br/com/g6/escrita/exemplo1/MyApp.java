package br.com.g6.escrita.exemplo1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyApp {

	public static void main(String[] args) {
		try {
			OutputStream output = new FileOutputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt");
			String s = "ABCDE";
			int count = s.length() - 1;
			while (count >= 0) {
				output.write(s.charAt(count));
				count--;
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}