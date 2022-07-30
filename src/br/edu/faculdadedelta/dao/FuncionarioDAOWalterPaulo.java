package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.FuncionarioWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class FuncionarioDAOWalterPaulo {

	public void incluir(FuncionarioWalterPaulo funcionario) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO funcionarios (nome,cpf,datanascimento,email) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, funcionario.getNome().trim());
		ps.setString(2, funcionario.getCpf().trim());
		ps.setDate(3, new Date(funcionario.getDataNascimento().getTime()));
		ps.setString(4, funcionario.getEmail().trim());
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(FuncionarioWalterPaulo funcionario) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE funcionarios "
				+ "SET nome = ?, "
				+ "cpf = ?,"
				+ "datanascimento =?,"
				+ "email = ?"
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, funcionario.getNome().trim());
		ps.setString(2, funcionario.getCpf().trim());
		ps.setDate(3, new Date(funcionario.getDataNascimento().getTime()));
		ps.setString(4, funcionario.getEmail().trim());
		ps.setLong(5, funcionario.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(FuncionarioWalterPaulo funcionario) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM funcionarios WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, funcionario.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public FuncionarioWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome,cpf, datanascimento,email FROM funcionarios "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		FuncionarioWalterPaulo retorno = new FuncionarioWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome").trim());
			retorno.setCpf(rs.getString("cpf").trim());
			retorno.setDataNascimento(rs.getDate("datanascimento"));
			retorno.setEmail(rs.getString("email").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<FuncionarioWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome,cpf,datanascimento,email FROM funcionarios";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<FuncionarioWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			FuncionarioWalterPaulo funcionario = new FuncionarioWalterPaulo();
			funcionario.setId(rs.getLong("id"));
			funcionario.setNome(rs.getString("nome").trim());
			funcionario.setCpf(rs.getString("cpf").trim());
			funcionario.setDataNascimento(rs.getDate("datanascimento"));
			funcionario.setEmail(rs.getString("email").trim());
			listaRetorno.add(funcionario);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
}
