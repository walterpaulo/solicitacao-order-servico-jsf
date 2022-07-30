package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.StatusRetornoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.StatusRetornoWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class StatusRetornoControllerWalterPaulo {

	private StatusRetornoWalterPaulo statusRetorno = new StatusRetornoWalterPaulo();
	private StatusRetornoDAOWalterPaulo dao = new StatusRetornoDAOWalterPaulo();



	public StatusRetornoWalterPaulo getStatusRetorno() {
		return statusRetorno;
	}


	public void setStatusRetorno(StatusRetornoWalterPaulo statusRetorno) {
		this.statusRetorno = statusRetorno;
	}


	public void limparCampos() {
		statusRetorno = new StatusRetornoWalterPaulo();
	}
	
	
	public String salvar() {
		try {
			if (statusRetorno.getId() == null) {
				dao.incluir(statusRetorno);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");	
			} else {
				dao.alterar(statusRetorno);
				limparCampos();
				FacesUtilWalterPaulo
				.exibirMensagem("Alteração realizada com sucesso!");				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		
		return "cadastroStatusRetorno.xhtml";
	}
	
	public String editar() {
		return "cadastroStatusRetorno.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(statusRetorno);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		
		return "listaStatusRetorno.xhtml";
	}
	
	public List<StatusRetornoWalterPaulo> getLista() {
		List<StatusRetornoWalterPaulo> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return listaRetorno;
	}
}
