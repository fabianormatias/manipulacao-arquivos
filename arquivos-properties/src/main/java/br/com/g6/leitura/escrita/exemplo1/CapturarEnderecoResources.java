package br.com.g6.leitura.escrita.exemplo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CapturarEnderecoResources {

	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			File jarPath = new File(CapturarEnderecoResources.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			String propertiesPath = jarPath.getParentFile().getAbsolutePath();
			System.out.println(" propertiesPath-" + propertiesPath);
			
			//TODO Abrir arquivo
			
			prop.load(new FileInputStream(propertiesPath + "/exemplo1.properties"));
			
			// Variavel que guardará o login do servidor.
			String login;
			// Variavel que guardará o host do servidor.
			String host;
			// Variável que guardará o password do usúario.
			String password;
			Properties props = getProp();
			login = props.getProperty("prop.server.login");
			host = props.getProperty("prop.server.host");
			password = props.getProperty("prop.server.password");
			System.out.println("Login = " + login);
			System.out.println("Host = " + host);
			System.out.println("Password = " + password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream file = Manipulador.class.getResourceAsStream("/exemplo1.properties");
		props.load(file);
		return props;
	}

}
