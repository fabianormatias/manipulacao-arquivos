package br.com.g6.leitura.exemplo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class MyApp {
	
	public static void main(String[] args) {
		String s = "ABCDE";
		StringReader sr = null;
		BufferedReader br = null;
		try {
			sr = new StringReader(s);
			br = new BufferedReader(sr);
			System.out.println((char) br.read());
			System.out.println((char) br.read());
			br.mark(0);
			System.out.println("mark() invoked");
			System.out.println((char) br.read());
			System.out.println((char) br.read());
			br.reset();
			System.out.println("reset() invoked");
			System.out.println((char) br.read());
			System.out.println((char) br.read());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sr != null)
				sr.close();
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}