package br.com.g6.escrita.exemplo4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyApp {

	private static Socket socket;

	public static void main(String[] args) throws IOException {
		String s = "enviando dados com Socket usando OutputStream";
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(s.getBytes());
		outputStream.flush();
	}

}
