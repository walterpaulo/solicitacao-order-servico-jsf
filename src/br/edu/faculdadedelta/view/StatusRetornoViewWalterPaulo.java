package br.edu.faculdadedelta.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

@ManagedBean(name = "statusRetornoViewWalterPaulo")
public class StatusRetornoViewWalterPaulo {
	
	
	public void viewAdicionar() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
        options.put("width", 870);
        options.put("height", 500);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		
		PrimeFaces.current().dialog().openDynamic("cadastroStatusRetorno", options, null);
	}
}
