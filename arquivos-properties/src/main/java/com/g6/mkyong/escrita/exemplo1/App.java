package com.g6.mkyong.escrita.exemplo1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

//TODO Falta fazer escrever no arquivo

public class App {
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");

			// save properties to project root folder
			prop.store(output, null);
			
//			String database = prop.getProperty("database");
//			System.out.println("Database: " + database);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}