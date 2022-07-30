package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import br.edu.faculdadedelta.dao.LaboratorioDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.LaboratorioWalterPaulo;
import br.edu.faculdadedelta.util.FacesUtilWalterPaulo;


@ManagedBean
@SessionScoped
public class LaboratorioControllerWalterPaulo {

	private LaboratorioWalterPaulo laboratorio = new LaboratorioWalterPaulo();
	private LaboratorioDAOWalterPaulo dao = new LaboratorioDAOWalterPaulo();


	public LaboratorioWalterPaulo getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(LaboratorioWalterPaulo laboratorio) {
		this.laboratorio = laboratorio;
	}

	public void limparCampos() {
		laboratorio = new LaboratorioWalterPaulo();
	}
	
	
	public String salvar() {
		try {
			if (laboratorio.getId() == null) {
				dao.incluir(laboratorio);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");	
			} else {
				dao.alterar(laboratorio);
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
		
		return "cadastroLaboratorio.xhtml";
	}
	
	public String editar() {
		return "cadastroLaboratorio.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(laboratorio);
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo
			.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		
		return "listaLaboratorio.xhtml";
	}
	
	public List<LaboratorioWalterPaulo> getLista() {
		List<LaboratorioWalterPaulo> listaRetorno = new ArrayList<>();
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
