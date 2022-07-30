package br.edu.faculdadedelta.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RetornoSolicitacaoWalterPaulo {

	private Long id;
	private String descricao;
	private Date dataRetorno;
	private FuncionarioWalterPaulo funcionario;
	private StatusRetornoWalterPaulo statusRetorno;
	private SolicitacaoWalterPaulo solicitacao;
	public RetornoSolicitacaoWalterPaulo() {
		super();
	}
	public RetornoSolicitacaoWalterPaulo(Long id, String descricao, Date dataRetorno,
			FuncionarioWalterPaulo funcionario, StatusRetornoWalterPaulo statusRetorno,
			SolicitacaoWalterPaulo solicitacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataRetorno = dataRetorno;
		this.funcionario = funcionario;
		this.statusRetorno = statusRetorno;
		this.solicitacao = solicitacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public FuncionarioWalterPaulo getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioWalterPaulo funcionario) {
		this.funcionario = funcionario;
	}
	public StatusRetornoWalterPaulo getStatusRetorno() {
		return statusRetorno;
	}
	public void setStatusRetorno(StatusRetornoWalterPaulo statusRetorno) {
		this.statusRetorno = statusRetorno;
	}
	public SolicitacaoWalterPaulo getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(SolicitacaoWalterPaulo solicitacao) {
		this.solicitacao = solicitacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetornoSolicitacaoWalterPaulo other = (RetornoSolicitacaoWalterPaulo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Date getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date dataSolicitacao = new Date();
		calendar.setTime(dataSolicitacao);
		return calendar.getTime();
	}
	
}
