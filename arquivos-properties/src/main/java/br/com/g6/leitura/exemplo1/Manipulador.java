package br.com.g6.leitura.exemplo1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Manipulador {
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream file = Manipulador.class.getResourceAsStream("/exemplo1.properties");
		props.load(file);
		return props;
	}

	public static void main(String args[]) throws IOException {
		// Variavel que guardará o login do servidor.
		String login;
		// Variavel que guardará o host do servidor.
		String host;
		// Variável que guardará o password do usúario.
		String password;
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		Properties prop = getProp();
		login = prop.getProperty("prop.server.login");
		host = prop.getProperty("prop.server.host");
		password = prop.getProperty("prop.server.password");
		System.out.println("Login = " + login);
		System.out.println("Host = " + host);
		System.out.println("Password = " + password);
	}
}
