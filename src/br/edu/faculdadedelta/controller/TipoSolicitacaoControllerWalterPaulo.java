package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.TipoSolicitacaoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.TipoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class TipoSolicitacaoControllerWalterPaulo {

	private TipoSolicitacaoWalterPaulo tipoSolicitacao = new TipoSolicitacaoWalterPaulo();
	private TipoSolicitacaoDAOWalterPaulo dao = new TipoSolicitacaoDAOWalterPaulo();

	public TipoSolicitacaoWalterPaulo getTipoSolicitacao() {
		return tipoSolicitacao;
	}


	public void setTipoSolicitacao(TipoSolicitacaoWalterPaulo tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}


	public void limparCampos() {
		tipoSolicitacao = new TipoSolicitacaoWalterPaulo();
	}
	
	
	public String salvar() {
		try {
			if (tipoSolicitacao.getId() == null) {
				dao.incluir(tipoSolicitacao);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");	
			} else {
				dao.alterar(tipoSolicitacao);
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
		
		return "cadastroTipoSolicitacao.xhtml";
	}
	
	public String editar() {
		return "cadastroTipoSolicitacao.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(tipoSolicitacao);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		
		return "listaTipoSolicitacao.xhtml";
	}
	
	public List<TipoSolicitacaoWalterPaulo> getLista() {
		List<TipoSolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
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
