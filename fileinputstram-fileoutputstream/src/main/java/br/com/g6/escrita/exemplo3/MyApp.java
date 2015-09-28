package br.com.g6.escrita.exemplo3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyApp {
	
	public static void main(String[] args) throws IOException {
		OutputStream output = null;
		try {
			output = new FileOutputStream("/Users/fabianorodriguesmatias/Developer/testes/testes/teste.txt");
			byte[] bytes = new byte[] { 'A', 'B', 'D', 'E' };
			int count = bytes.length;
			while (count >= 0) {
				output.write(bytes);
				count--;
			}
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
	
}