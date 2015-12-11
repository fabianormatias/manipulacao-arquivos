package com.g6.escrita.modelocobol2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteModeloCobol {

	public static void main(String[] args) {
		
		Map<String, List<LayoutModeloCobol>> agruparLayouts = new HashMap<String, List<LayoutModeloCobol>>();
		
		String arquivoProperties = "/teste.properties";
		
		try {
			LayoutModeloCobol layout1 = new LayoutModeloCobol(arquivoProperties);
			layout1.add("codigo.empresa", "1234");
			layout1.add("branco.1", "");
			layout1.add("data.movimento", "250385");
			agruparLayouts.put("1", layout1.getItens());

			LayoutModeloCobol layout2 = new LayoutModeloCobol(arquivoProperties);
			layout2.add("codigo.empresa", "12");
			layout2.add("branco.1", "");
			layout2.add("data.movimento", "2503");
			agruparLayouts.put("2", layout2.getItens());

			LayoutModeloCobol layout3 = new LayoutModeloCobol(arquivoProperties);
			layout3.add("codigo.empresa", "1245");
			layout3.add("branco.1", "");
			layout3.add("data.movimento", "250319");
			agruparLayouts.put("3", layout3.getItens());
		} catch (ModeloCobolException e) {
			System.out.println("Arquivo properties não encontrado!");
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, List<LayoutModeloCobol>> layouts : agruparLayouts.entrySet()) {
			List<LayoutModeloCobol> layoutsAgrupados = layouts.getValue();
			for (LayoutModeloCobol layoutModeloCobol : layoutsAgrupados) {
				sb.append(layoutModeloCobol.getValor());
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		//TODO Salvar no arquivo
		String diretorio = "/Users/fabianorodriguesmatias/Developer/testes";
		String arquivo = "teste.txt";
		
		JavaIOUtils.createTextFile(diretorio, arquivo, sb.toString(), false);

	}
}
