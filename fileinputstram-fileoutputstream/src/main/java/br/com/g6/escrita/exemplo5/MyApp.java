package br.com.g6.escrita.exemplo5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyApp {

	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(12345);
		Socket cliente = servidor.accept();
		Scanner entrada = new Scanner(cliente.getInputStream());
		while (entrada.hasNextLine()) {
			System.out.println(entrada.nextLine());
		}
		entrada.close();
		servidor.close();
	}

}