package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.FuncionarioDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.FuncionarioWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class FuncionarioControllerWalterPaulo {

	private FuncionarioWalterPaulo funcionario = new FuncionarioWalterPaulo();
	private FuncionarioDAOWalterPaulo dao = new FuncionarioDAOWalterPaulo();

	public FuncionarioWalterPaulo getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioWalterPaulo funcionario) {
		this.funcionario = funcionario;
	}

	public void limparCampos() {
		funcionario = new FuncionarioWalterPaulo();
	}

	public String salvar() {
		try {
			if (funcionario.getId() == null) {
				dao.incluir(funcionario);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(funcionario);
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
		return "cadastroFuncionario.xhtml";
	}

	public String editar() {
		return "cadastroFuncionario.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(funcionario);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return "listaFuncionario.xhtml";
	}
	
	public List<FuncionarioWalterPaulo> getLista() {
		List<FuncionarioWalterPaulo> listaRetorno = new ArrayList<>();
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
