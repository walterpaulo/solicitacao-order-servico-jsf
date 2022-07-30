package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class AlunoDAOWalterPaulo {

	public void incluir(AlunoWalterPaulo aluno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO alunos (nome, matricula,cpf,datanascimento,email) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aluno.getNome().trim());
		ps.setString(2, aluno.getMatricula().trim());
		ps.setString(3, aluno.getCpf().trim());
		ps.setDate(4, new Date(aluno.getDataNascimento().getTime()));
		ps.setString(5, aluno.getEmail().trim());
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(AlunoWalterPaulo aluno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE alunos "
				+ "SET nome = ?, "
				+ "matricula = ?, "
				+ "cpf = ?,"
				+ "datanascimento =?,"
				+ "email = ?"
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aluno.getNome().trim());
		ps.setString(2, aluno.getMatricula().trim());
		ps.setString(3, aluno.getCpf().trim());
		ps.setDate(4, new Date(aluno.getDataNascimento().getTime()));
		ps.setString(5, aluno.getEmail().trim());
		ps.setLong(6, aluno.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(AlunoWalterPaulo aluno) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM alunos WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, aluno.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public AlunoWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome, matricula, cpf, datanascimento,email FROM alunos "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		AlunoWalterPaulo retorno = new AlunoWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome").trim());
			retorno.setMatricula(rs.getString("matricula").trim());
			retorno.setCpf(rs.getString("cpf").trim());
			retorno.setDataNascimento(rs.getDate("datanascimento"));
			retorno.setEmail(rs.getString("email").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<AlunoWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome,matricula,cpf,datanascimento,email FROM alunos";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<AlunoWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			AlunoWalterPaulo aluno = new AlunoWalterPaulo();
			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome").trim());
			aluno.setMatricula(rs.getString("matricula").trim());
			aluno.setCpf(rs.getString("cpf").trim());
			aluno.setDataNascimento(rs.getDate("datanascimento"));
			aluno.setEmail(rs.getString("email"));
			listaRetorno.add(aluno);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
}
