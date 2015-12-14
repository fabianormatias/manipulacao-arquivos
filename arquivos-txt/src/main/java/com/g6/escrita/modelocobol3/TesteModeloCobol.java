package com.g6.escrita.modelocobol3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteModeloCobol {

	public static void main(String[] args) {
		try {
			Map<String, List<String>> agruparLayouts = new HashMap<String, List<String>>();

			DadosEmpresa dados1 = new DadosEmpresa("1", "", "141215");
			
			LayoutModeloCobol layout1 = new LayoutModeloCobol();
			layout1.add(dados1);
			agruparLayouts.put("1", layout1.getItens());

			LayoutModeloCobol layout2 = new LayoutModeloCobol();
			layout2.add(dados1);
			agruparLayouts.put("2", layout2.getItens());

			LayoutModeloCobol layout3 = new LayoutModeloCobol();
			layout3.add(dados1);
			agruparLayouts.put("3", layout3.getItens());

			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, List<String>> layouts : agruparLayouts.entrySet()) {
				List<String> layoutsAgrupados = layouts.getValue();
				for (String dadosEmpresa : layoutsAgrupados) {
					sb.append(dadosEmpresa);
					sb.append("\n");
				}
			}

			System.out.println(sb.toString());
		} catch (ModeloCobolException e) {
			e.printStackTrace();
		}
	}

}
