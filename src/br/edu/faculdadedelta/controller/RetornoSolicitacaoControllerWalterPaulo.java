package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.RetornoSolicitacaoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.FuncionarioWalterPaulo;
import br.edu.faculdadedelta.modelo.RetornoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.modelo.StatusRetornoWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;

/**
 * @author walter
 *
 */
/**
 * @author walter
 *
 */
@ManagedBean
@SessionScoped
public class RetornoSolicitacaoControllerWalterPaulo {

	private RetornoSolicitacaoDAOWalterPaulo dao = new RetornoSolicitacaoDAOWalterPaulo();
	private RetornoSolicitacaoWalterPaulo statusRetono = new RetornoSolicitacaoWalterPaulo();
	private StatusRetornoWalterPaulo statusRetornoSelecionado = new StatusRetornoWalterPaulo();
	private FuncionarioWalterPaulo funcionarioSelecionado = new FuncionarioWalterPaulo();
	
	public RetornoSolicitacaoWalterPaulo getStatusRetono() {
		return statusRetono;
	}

	public void setStatusRetono(RetornoSolicitacaoWalterPaulo statusRetono) {
		this.statusRetono = statusRetono;
	}
	

	public StatusRetornoWalterPaulo getStatusRetornoSelecionado() {
		return statusRetornoSelecionado;
	}

	public void setStatusRetornoSelecionado(StatusRetornoWalterPaulo statusRetornoSelecionado) {
		this.statusRetornoSelecionado = statusRetornoSelecionado;
	}
	
	

	public FuncionarioWalterPaulo getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(FuncionarioWalterPaulo funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public void limparCampos() {
		
	}

	public String salvar() {
		try {
			
			if (statusRetono.getId() == null || statusRetono.getId()==0) {
				statusRetono.getId();
				statusRetono.setFuncionario(funcionarioSelecionado);
				statusRetono.setStatusRetorno(statusRetornoSelecionado);
				dao.incluir(statusRetono);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				statusRetono.getId();
				statusRetono.setFuncionario(funcionarioSelecionado);
				statusRetono.setStatusRetorno(statusRetornoSelecionado);
				dao.alterar(statusRetono);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return "listaRetorno.xhtml";
	}
	
	public String editar() {
		funcionarioSelecionado = statusRetono.getFuncionario();
		statusRetornoSelecionado = statusRetono.getStatusRetorno();
		return "cadastroRetornoSolicitacao.xhtml";
	}
	
	public String excluir() {
		try {
			
			dao.excluir(statusRetono);
			limparCampos();
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());
		}
		return "listaRetorno.xhtml";
	}
	
	public List<RetornoSolicitacaoWalterPaulo> getLista() {
		List<RetornoSolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return listaRetorno;
	}
	
	
	
	
	
	
	
	
	
	
}
