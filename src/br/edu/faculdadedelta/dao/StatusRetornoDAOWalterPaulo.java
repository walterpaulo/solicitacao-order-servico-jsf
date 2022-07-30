package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.StatusRetornoWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class StatusRetornoDAOWalterPaulo {

	public void incluir(StatusRetornoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO status_retorno (descricao) "
				+ "VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, statusRetorno.getDescricao().trim());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(StatusRetornoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE status_retorno SET "
				+ "descricao=? "
				+ " WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, statusRetorno.getDescricao().trim());
		ps.setLong(2, statusRetorno.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(StatusRetornoWalterPaulo statusRetorno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM status_retorno WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, statusRetorno.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public StatusRetornoWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM status_retorno "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		StatusRetornoWalterPaulo retorno = new StatusRetornoWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setDescricao(rs.getString("descricao").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<StatusRetornoWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id,descricao FROM status_retorno";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<StatusRetornoWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			StatusRetornoWalterPaulo statusRetorno = new StatusRetornoWalterPaulo();
			statusRetorno.setId(rs.getLong("id"));
			statusRetorno.setDescricao(rs.getString("descricao").trim());
			listaRetorno.add(statusRetorno);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
	public StatusRetornoWalterPaulo validarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id,descricao FROM status_retorno "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		StatusRetornoWalterPaulo retorno = new StatusRetornoWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setDescricao(rs.getString("descricao"));
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	public StatusRetornoWalterPaulo mostarStatus(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT st.descricao AS descricaoStatus FROM retorno_solicitacao r " + 
				"INNER JOIN status_retorno st ON r.id_status_retorno = st.id " + 
				"INNER JOIN solicitacoes s ON r.id_solicitacao = s.id WHERE s.id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		StatusRetornoWalterPaulo mostrarStatus = new StatusRetornoWalterPaulo();
		while (rs.next()) {
			mostrarStatus.setDescricao(rs.getString("descricaoStatus"));
		}

		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return mostrarStatus; 
	}
}
