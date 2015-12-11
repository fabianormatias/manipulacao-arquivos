package com.g6.mkyong.escrita.exemplo1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

//TODO Fazer funcionar a escrita

public class WritePropertyFile {

	public static void main(String[] args) {
		try (OutputStream output = new FileOutputStream("config.properties")) {
			Properties prop = new Properties();

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("username", "Codingeek");
			prop.setProperty("password", "Codingeek");

			// save properties to project root folder.
			prop.store(output, null);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}