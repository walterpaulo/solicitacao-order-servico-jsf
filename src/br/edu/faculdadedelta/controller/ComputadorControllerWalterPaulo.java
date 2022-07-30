package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.ComputadorDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class ComputadorControllerWalterPaulo {

	private ComputadorWalterPaulo computador = new ComputadorWalterPaulo();
	private ComputadorDAOWalterPaulo dao = new ComputadorDAOWalterPaulo();

	public ComputadorWalterPaulo getComputador() {
		return computador;
	}

	public void setComputador(ComputadorWalterPaulo computador) {
		this.computador = computador;
	}

	public void limparCampos() {
		computador = new ComputadorWalterPaulo();
	}

	public String salvar() {
		try {
			if (computador.getId() == null) {
				dao.incluir(computador);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(computador);
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
		return "cadastroComputador.xhtml";
	}

	public String editar() {
		return "cadastroComputador.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(computador);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return "listaComputador.xhtml";
	}
	
	public List<ComputadorWalterPaulo> getLista() {
		List<ComputadorWalterPaulo> listaRetorno = new ArrayList<>();
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
