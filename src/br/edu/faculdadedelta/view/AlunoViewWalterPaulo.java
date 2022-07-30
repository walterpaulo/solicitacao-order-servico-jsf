package br.edu.faculdadedelta.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

@ManagedBean (name="alunoViewWalterPaulo")
public class AlunoViewWalterPaulo {
	
	public void viewAluno() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 540);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		PrimeFaces.current().dialog().openDynamic("listaAluno", options, null);
	}
	public void viewAdicionar() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
        options.put("width", 870);
        options.put("height", 340);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
		
		PrimeFaces.current().dialog().openDynamic("cadastroAluno", options, null);
	}
}
