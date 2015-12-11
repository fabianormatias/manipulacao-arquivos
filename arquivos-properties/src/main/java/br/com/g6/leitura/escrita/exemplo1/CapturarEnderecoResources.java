package br.com.g6.leitura.escrita.exemplo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//TODO Falta fazer escrever no arquivo

public class CapturarEnderecoResources {

	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			
//			File jarPath = new File(CapturarEnderecoResources.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//			String propertiesPath = jarPath.getParentFile().getAbsolutePath();
//			System.out.println(" propertiesPath-" + propertiesPath);
			
//			URL url = CapturarEnderecoResources.class.getClassLoader().getResource("exemplo1.properties");
			
			prop.setProperty("prop.server.login", "fabiano");
			
			//TODO Ele não acha o arquivo
			File f = new File("exemplo1.properties");
			FileOutputStream saida = new FileOutputStream(f);
			prop.store(saida, "Alteração properties");
			
			String login;
			String host;
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
		InputStream file = CapturarEnderecoResources.class.getResourceAsStream("/exemplo1.properties");
		props.load(file);
		return props;
	}

}
