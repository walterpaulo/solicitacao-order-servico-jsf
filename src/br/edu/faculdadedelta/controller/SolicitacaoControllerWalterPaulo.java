package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.SolicitacaoDAOWalterPaulo;
import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;
import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;
import br.edu.faculdadedelta.modelo.LaboratorioWalterPaulo;
import br.edu.faculdadedelta.modelo.SolicitacaoWalterPaulo;
import br.edu.faculdadedelta.modelo.TipoSolicitacaoWalterPaulo;
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
public class SolicitacaoControllerWalterPaulo {

	private SolicitacaoWalterPaulo solicitacao = new SolicitacaoWalterPaulo();
	private SolicitacaoDAOWalterPaulo dao = new SolicitacaoDAOWalterPaulo();
	private ComputadorWalterPaulo computadorSelecionado = new ComputadorWalterPaulo();
	private LaboratorioWalterPaulo laboratorioSelecionado = new LaboratorioWalterPaulo();
	private TipoSolicitacaoWalterPaulo tipoSolicitacaoSelecionado = new TipoSolicitacaoWalterPaulo();
	private AlunoWalterPaulo alunoSelecionado = new AlunoWalterPaulo();
	

	public SolicitacaoWalterPaulo getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoWalterPaulo solicitacao) {
		this.solicitacao = solicitacao;
	}

	public ComputadorWalterPaulo getComputadorSelecionado() {
		return computadorSelecionado;
	}

	public void setComputadorSelecionado(ComputadorWalterPaulo computadorSelecionado) {
		this.computadorSelecionado = computadorSelecionado;
	}

	public LaboratorioWalterPaulo getLaboratorioSelecionado() {
		return laboratorioSelecionado;
	}

	public void setLaboratorioSelecionado(LaboratorioWalterPaulo laboratorioSelecionado) {
		this.laboratorioSelecionado = laboratorioSelecionado;
	}

	public TipoSolicitacaoWalterPaulo getTipoSolicitacaoSelecionado() {
		return tipoSolicitacaoSelecionado;
	}

	public void setTipoSolicitacaoSelecionado(TipoSolicitacaoWalterPaulo tipoSolicitacaoSelecionado) {
		this.tipoSolicitacaoSelecionado = tipoSolicitacaoSelecionado;
	}

	public AlunoWalterPaulo getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(AlunoWalterPaulo alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public void limparCampos() {
		solicitacao = new SolicitacaoWalterPaulo();
		computadorSelecionado = new ComputadorWalterPaulo();
		laboratorioSelecionado = new LaboratorioWalterPaulo();
		tipoSolicitacaoSelecionado = new TipoSolicitacaoWalterPaulo();
		alunoSelecionado = new AlunoWalterPaulo();
	}

	public String salvar() {
		try {
			solicitacao.setTipoSolicitacao(tipoSolicitacaoSelecionado);
			solicitacao.setComputador(computadorSelecionado);
			solicitacao.setLaboratorio(laboratorioSelecionado);
			solicitacao.setAluno(alunoSelecionado);
			if (solicitacao.getId() == null) {
				
				dao.incluir(solicitacao);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(solicitacao);
				limparCampos();
				FacesUtilWalterPaulo
					.exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());			
		}
		return "cadastroSolicitacao.xhtml";
	}
	
	public String editar() {
		computadorSelecionado = solicitacao.getComputador();
		laboratorioSelecionado = solicitacao.getLaboratorio();
		tipoSolicitacaoSelecionado = solicitacao.getTipoSolicitacao();
		alunoSelecionado = solicitacao.getAluno();
		return "cadastroSolicitacao.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(solicitacao);
			limparCampos();
			FacesUtilWalterPaulo
				.exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtilWalterPaulo.exibirMensagem("Erro ao realizar "
					+ "a operação: " + e.getMessage());
		}
		return "listaSolicitacao.xhtml";
	}
	
	public List<SolicitacaoWalterPaulo> getLista() {
		List<SolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
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
