package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ComputadorWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class ComputadorDAOWalterPaulo {

	public void incluir(ComputadorWalterPaulo computador) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO computadores (numero, descricao) "
				+ "VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, computador.getNumero());
		ps.setString(2, computador.getDescricao());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(ComputadorWalterPaulo computador) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE computadores "
				+ "SET numero = ?, "
				+ "descricao = ? "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, computador.getNumero());
		ps.setString(2, computador.getDescricao().trim());
		ps.setLong(3, computador.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(ComputadorWalterPaulo computador) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM computadores WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, computador.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public ComputadorWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, descricao FROM computadores "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		ComputadorWalterPaulo retorno = new ComputadorWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNumero(rs.getInt("numero"));
			retorno.setDescricao(rs.getString("descricao").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<ComputadorWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, descricao FROM computadores";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<ComputadorWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			ComputadorWalterPaulo computador = new ComputadorWalterPaulo();
			computador.setId(rs.getLong("id"));
			computador.setNumero(rs.getInt("numero"));
			computador.setDescricao(rs.getString("descricao").trim());
			listaRetorno.add(computador);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
}
