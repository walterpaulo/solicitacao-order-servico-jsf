package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;
import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;
import br.edu.faculdadedelta.modelo.FuncionarioWalterPaulo;
import br.edu.faculdadedelta.modelo.LaboratorioWalterPaulo;
import br.edu.faculdadedelta.modelo.RetornoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.modelo.SolicitacaoWalterPaulo;
import br.edu.faculdadedelta.modelo.StatusRetornoWalterPaulo;
import br.edu.faculdadedelta.modelo.TipoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;



public class RetornoSolicitacaoDAOWalterPaulo {

	public void incluir(RetornoSolicitacaoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO retorno_solicitacao("
				+ "descricao, data_solicitacao, "
				+ "id_funcionario, id_status_retorno,"
				+ "id_solicitacao)"
				+ "VALUES (?, ?, ?, ?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, statusRetorno.getDescricao());
		ps.setDate(2, new java.sql.Date(statusRetorno.getDataRetorno().getTime()));
		ps.setLong(3, statusRetorno.getFuncionario().getId());
		ps.setLong(4, statusRetorno.getStatusRetorno().getId());
		ps.setLong(5, statusRetorno.getSolicitacao().getId());
		ps.executeUpdate();
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(RetornoSolicitacaoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE public.retorno_solicitacao SET "
				+ "descricao=?, data_solicitacao=?, "
				+ "id_funcionario=?, id_status_retorno=?, "
				+ "id_solicitacao=? WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, statusRetorno.getDescricao());
		ps.setDate(2, new java.sql.Date(statusRetorno.getDataRetorno().getTime()));
		ps.setLong(3, statusRetorno.getFuncionario().getId());
		ps.setLong(4, statusRetorno.getStatusRetorno().getId());
		ps.setLong(5, statusRetorno.getSolicitacao().getId());
		ps.setLong(6, statusRetorno.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(RetornoSolicitacaoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM retorno_solicitacao WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, statusRetorno.getId());
		ps.executeUpdate();
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public List<RetornoSolicitacaoWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT rs.id AS idRetornoSolicitacao,s.id AS idSolicitacao, s.data_solicitacao AS dataSolicitacao, "
				+ "a.nome AS nomeAluno, a.matricula AS matriculaAluno, "
				+ "l.numero AS numeroLaboratorio, c.numero AS numeroComputador, "
				+ "t.nome AS nomeTipoSolicitacao, s.descricao AS descricaoSolicitacao, "
				+ "f.nome AS nomeFuncionario, rs.data_solicitacao AS dataRetorno, "
				+ "sr.descricao AS descricaoStatus,rs.descricao AS descricaoRetorno "
				+ "FROM solicitacoes s "
				+ "INNER JOIN laboratorios l ON s.id_laboratorio = l.id "
				+ "INNER JOIN tipo_solicitacao t ON s.id_tipo_solicitacao = t.id "
				+ "INNER JOIN computadores c ON s.id_computador = c.id "
				+ "INNER JOIN alunos a ON s.id_aluno = a.id "
				+ "LEFT JOIN retorno_solicitacao rs ON rs.id_solicitacao = s.id "
				+ "LEFT JOIN status_retorno sr ON rs.id_status_retorno = sr.id "
				+ "LEFT JOIN funcionarios f ON rs.id_funcionario = f.id ORDER BY s.id" + 
				"";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<RetornoSolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			RetornoSolicitacaoWalterPaulo statusRetorno = new RetornoSolicitacaoWalterPaulo();
			SolicitacaoWalterPaulo solicitacao = new SolicitacaoWalterPaulo();
			statusRetorno.setId(rs.getLong("idRetornoSolicitacao"));
			solicitacao.setId(rs.getLong("idSolicitacao"));
			solicitacao.setDataSolicitacao(rs.getDate("dataSolicitacao"));
			solicitacao.setDataSolicitacao(rs.getDate("dataSolicitacao"));
			
			AlunoWalterPaulo aluno = new AlunoWalterPaulo();
			aluno.setNome(rs.getString("nomeAluno"));
			aluno.setMatricula(rs.getString("matriculaAluno"));
			solicitacao.setAluno(aluno);
			
			LaboratorioWalterPaulo laboratorio = new LaboratorioWalterPaulo();
			laboratorio.setNumero(rs.getInt("numeroLaboratorio"));
			solicitacao.setLaboratorio(laboratorio);
			
			ComputadorWalterPaulo computador = new ComputadorWalterPaulo();
			computador.setNumero(rs.getInt("numeroComputador"));
			solicitacao.setComputador(computador);
			
			TipoSolicitacaoWalterPaulo tipoSolicitacao = new TipoSolicitacaoWalterPaulo();
			tipoSolicitacao.setNome(rs.getString("nomeTipoSolicitacao"));
			solicitacao.setTipoSolicitacao(tipoSolicitacao);
			
			solicitacao.setDescricao(rs.getString("descricaoSolicitacao"));
			
			FuncionarioWalterPaulo funcionario = new FuncionarioWalterPaulo();
			funcionario.setNome(rs.getString("nomeFuncionario"));
			statusRetorno.setFuncionario(funcionario);
			
			statusRetorno.setDataRetorno(rs.getDate("dataRetorno"));
			
			StatusRetornoWalterPaulo status = new StatusRetornoWalterPaulo();
			status.setDescricao(rs.getString("descricaoStatus"));
			statusRetorno.setStatusRetorno(status);
			
			statusRetorno.setDescricao(rs.getString("descricaoRetorno"));
			statusRetorno.setSolicitacao(solicitacao);
			
			
			listaRetorno.add(statusRetorno);
		}

		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno; 
	}
	
	
}
