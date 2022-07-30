package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.LaboratorioWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class LaboratorioDAOWalterPaulo {

	public void incluir(LaboratorioWalterPaulo laboratorio) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO laboratorios (numero, descricao) "
				+ "VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, laboratorio.getNumero());
		ps.setString(2, laboratorio.getDescricao().trim());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(LaboratorioWalterPaulo laboratorio) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE laboratorios "
				+ "SET numero = ?, "
				+ "descricao = ? "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, laboratorio.getNumero());
		ps.setString(2, laboratorio.getDescricao().trim());
		ps.setLong(3, laboratorio.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(LaboratorioWalterPaulo laboratorio) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM laboratorios WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, laboratorio.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public LaboratorioWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, descricao FROM laboratorios "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		LaboratorioWalterPaulo retorno = new LaboratorioWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNumero(rs.getInt("numero"));
			retorno.setDescricao(rs.getString("descricao").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<LaboratorioWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, descricao FROM laboratorios";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<LaboratorioWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			LaboratorioWalterPaulo laboratorio = new LaboratorioWalterPaulo();
			laboratorio.setId(rs.getLong("id"));
			laboratorio.setNumero(rs.getInt("numero"));
			laboratorio.setDescricao(rs.getString("descricao").trim());
			listaRetorno.add(laboratorio);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
	public LaboratorioWalterPaulo validarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, descricao FROM laboratorios "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		LaboratorioWalterPaulo retorno = new LaboratorioWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
}
