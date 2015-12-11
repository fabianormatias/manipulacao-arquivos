package com.g6.escrita.modelocobol1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteModeloCobol {

	public static void main(String[] args) {
		
		Map<String, List<LayoutModeloCobol>> agruparLayouts = new HashMap<String, List<LayoutModeloCobol>>();
		
		LayoutModeloCobol layout1 = new LayoutModeloCobol();
		layout1.add("CODIGO_EMPRESA", TipoModeloCobol.CHAR, 4, "1234");
		layout1.add("BRANCO", TipoModeloCobol.CHAR, 6, "");
		layout1.add("DATA_MOVIMENTO", TipoModeloCobol.PIC, 6, "250385");
		agruparLayouts.put("1", layout1.getItens());

		LayoutModeloCobol layout2 = new LayoutModeloCobol();
		layout2.add("CODIGO_EMPRESA", TipoModeloCobol.CHAR, 4, "12");
		layout2.add("BRANCO", TipoModeloCobol.CHAR, 6, "");
		layout2.add("DATA_MOVIMENTO", TipoModeloCobol.PIC, 6, "2503");
		agruparLayouts.put("2", layout2.getItens());
		
		LayoutModeloCobol layout3 = new LayoutModeloCobol();
		layout3.add("CODIGO_EMPRESA", TipoModeloCobol.CHAR, 4, "1245");
		layout3.add("BRANCO", TipoModeloCobol.CHAR, 6, "");
		layout3.add("DATA_MOVIMENTO", TipoModeloCobol.PIC, 6, "250319");
		agruparLayouts.put("3", layout3.getItens());
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, List<LayoutModeloCobol>> layouts : agruparLayouts.entrySet()) {
			List<LayoutModeloCobol> layoutsAgrupados = layouts.getValue();
			for (LayoutModeloCobol layoutModeloCobol : layoutsAgrupados) {
				sb.append(layoutModeloCobol.getValor());
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
