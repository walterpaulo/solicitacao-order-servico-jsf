package br.edu.faculdadedelta.util;import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtilWalterPaulo {

	public static void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public static void exibirMensagemSucess(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
		
	}
}
	