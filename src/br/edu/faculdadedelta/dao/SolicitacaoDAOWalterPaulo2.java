package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;
import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;
import br.edu.faculdadedelta.modelo.LaboratorioWalterPaulo;
import br.edu.faculdadedelta.modelo.SolicitacaoWalterPaulo;
import br.edu.faculdadedelta.modelo.TipoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;



public class SolicitacaoDAOWalterPaulo2 {

	public void incluir(SolicitacaoWalterPaulo solicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO solicitacoes("
				+ "descricao, data_solicitacao, "
				+ "id_laboratorio, id_tipo_solicitacao,"
				+ "id_aluno,id_computador)"
				+ "VALUES (?, ?, ?,?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, solicitacao.getDescricao());
		ps.setDate(2, new java.sql.Date(solicitacao.getDataSolicitacao().getTime()));
		ps.setLong(3, solicitacao.getLaboratorio().getId());
		ps.setLong(4, solicitacao.getTipoSolicitacao().getId());
		ps.setLong(5, solicitacao.getAluno().getId());
		ps.setLong(6, solicitacao.getComputador().getId());
		ps.executeUpdate();
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(SolicitacaoWalterPaulo solicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE solicitacoes SET "
				+ "descricao=?, data_solicitacao=?, "
				+ "id_laboratorio=?, id_tipo_solicitacao=?, "
				+ "id_aluno=?, id_computador=? "
				+ "WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, solicitacao.getDescricao());
		ps.setDate(2, new java.sql.Date(solicitacao.getDataSolicitacao().getTime()));
		ps.setLong(3, solicitacao.getLaboratorio().getId());
		ps.setLong(4, solicitacao.getTipoSolicitacao().getId());
		ps.setLong(5, solicitacao.getAluno().getId());
		ps.setLong(6, solicitacao.getComputador().getId());
		ps.setLong(7, solicitacao.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(SolicitacaoWalterPaulo solicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM solicitacoes WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, solicitacao.getId());
		ps.executeUpdate();
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public List<SolicitacaoWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT s.id AS idSolicitacao,"
				+ "s.descricao AS descricaoSolicitacao,"
				+ " s.data_solicitacao AS dataSolicitacao,"
				+ "a.nome AS nomeAluno,"
				+ "l.numero AS numeroLaboratorio,"
				+ "c.numero AS numeroComputador,"
				+ "t.nome AS nomeTipoSolicitacao "
				+ "FROM solicitacoes s "
				+ "INNER JOIN laboratorios l ON s.id_laboratorio = l.id "
				+ "INNER JOIN tipo_solicitacao t ON s.id_tipo_solicitacao = t.id "
				+ "INNER JOIN computadores c ON s.id_computador = c.id "
				+ "INNER JOIN alunos a ON s.id_aluno = a.id;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<SolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			SolicitacaoWalterPaulo solicitacao = new SolicitacaoWalterPaulo();
			solicitacao.setId(rs.getLong("idSolicitacao"));
			solicitacao.setDescricao(rs.getString("descricaoSolicitacao"));
			solicitacao.setDataSolicitacao(rs.getDate("dataSolicitacao"));
			
			AlunoWalterPaulo aluno = new AlunoWalterPaulo();
			aluno.setNome(rs.getString("nomeAluno"));
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
			
		
			
			listaRetorno.add(solicitacao);
		}

		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
}
