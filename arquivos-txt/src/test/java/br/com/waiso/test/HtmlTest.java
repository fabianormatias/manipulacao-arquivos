package br.com.waiso.test;

import org.junit.Before;
import org.junit.Test;

import br.com.waiso.utils.JavaIO;

public class HtmlTest {

	private StringBuilder padrao;
	
	@Test
	public void test() {
		
		boolean mac = JavaIO.isMac();
		System.out.println("É mac? " + mac);
		
		
	}
	
	
	@Before
	public void padrao() {
		padrao = new StringBuilder();
		padrao.append("data-i18n=\"");
		padrao.append("([a-zA-Z_0-9]*|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*)");
		padrao.append("\\.");
		padrao.append("([a-zA-Z_0-9]*|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*|[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*\\-[a-zA-Z_0-9]*)");
		padrao.append("\"");
	}

}
