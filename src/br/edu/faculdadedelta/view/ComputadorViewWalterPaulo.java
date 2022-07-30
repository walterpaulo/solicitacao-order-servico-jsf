package br.edu.faculdadedelta.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

@ManagedBean (name="computadorViewWalterPaulo")
public class ComputadorViewWalterPaulo {
	
	public void viewComputador() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
        options.put("width", 870);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		PrimeFaces.current().dialog().openDynamic("listaComputador", options, null);
	}
	public void viewAdicionar() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
        options.put("width", 400);
        options.put("height", 250);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		
		PrimeFaces.current().dialog().openDynamic("cadastroComputador", options, null);
	}
}
