package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.AlunoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class AlunoControllerWalterPaulo {

	private AlunoWalterPaulo aluno = new AlunoWalterPaulo();
	private AlunoDAOWalterPaulo dao = new AlunoDAOWalterPaulo();

	public AlunoWalterPaulo getaluno() {
		return aluno;
	}

	public void setaluno(AlunoWalterPaulo aluno) {
		this.aluno = aluno;
	}

	public void limparCampos() {
		aluno = new AlunoWalterPaulo();
	}

	public String salvar() {
		try {
			if (aluno.getId() == null) {
				dao.incluir(aluno);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(aluno);
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
		return "cadastroAluno.xhtml";
	}

	public String editar() {
		return "cadastroAluno.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(aluno);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return "listaAluno.xhtml";
	}
	
	public List<AlunoWalterPaulo> getLista() {
		List<AlunoWalterPaulo> listaRetorno = new ArrayList<>();
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
