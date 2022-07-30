package br.edu.faculdadedelta.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SolicitacaoWalterPaulo {

	private Long id;
	private String descricao;
	private Date dataSolicitacao;
	private LaboratorioWalterPaulo laboratorio;
	private TipoSolicitacaoWalterPaulo tipoSolicitacao;
	private ComputadorWalterPaulo computador;
	private AlunoWalterPaulo aluno;
	
	public SolicitacaoWalterPaulo() {
		super();
	}
	public SolicitacaoWalterPaulo(Long id, String descricao, Date dataSolicitacao, LaboratorioWalterPaulo laboratorio,
			TipoSolicitacaoWalterPaulo tipoSolicitacao, ComputadorWalterPaulo computador, AlunoWalterPaulo aluno) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataSolicitacao = dataSolicitacao;
		this.laboratorio = laboratorio;
		this.tipoSolicitacao = tipoSolicitacao;
		this.computador = computador;
		this.aluno = aluno;
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
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public LaboratorioWalterPaulo getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(LaboratorioWalterPaulo laboratorio) {
		this.laboratorio = laboratorio;
	}
	public TipoSolicitacaoWalterPaulo getTipoSolicitacao() {
		return tipoSolicitacao;
	}
	public void setTipoSolicitacao(TipoSolicitacaoWalterPaulo tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
	public ComputadorWalterPaulo getComputador() {
		return computador;
	}
	public void setComputador(ComputadorWalterPaulo computador) {
		this.computador = computador;
	}
	public AlunoWalterPaulo getAluno() {
		return aluno;
	}
	public void setAluno(AlunoWalterPaulo aluno) {
		this.aluno = aluno;
	}
	public Date getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date dataSolicitacao = new Date();
		calendar.setTime(dataSolicitacao);
		return calendar.getTime();
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
		SolicitacaoWalterPaulo other = (SolicitacaoWalterPaulo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
