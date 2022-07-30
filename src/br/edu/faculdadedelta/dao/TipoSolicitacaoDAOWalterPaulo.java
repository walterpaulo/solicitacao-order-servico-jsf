package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.TipoSolicitacaoWalterPaulo;
import br.edu.faculdadedelta.util.ConexaoWalterPaulo;


public class TipoSolicitacaoDAOWalterPaulo {

	public void incluir(TipoSolicitacaoWalterPaulo tipoSolicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "INSERT INTO tipo_solicitacao (nome, descricao) "
				+ "VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, tipoSolicitacao.getNome().trim());
		ps.setString(2, tipoSolicitacao.getDescricao().trim());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void alterar(TipoSolicitacaoWalterPaulo tipoSolicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "UPDATE tipo_solicitacao "
				+ "SET nome = ?, "
				+ "descricao = ? "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, tipoSolicitacao.getNome().trim());
		ps.setString(2, tipoSolicitacao.getDescricao().trim());
		ps.setLong(3, tipoSolicitacao.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public void excluir(TipoSolicitacaoWalterPaulo tipoSolicitacao) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "DELETE FROM tipo_solicitacao WHERE id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, tipoSolicitacao.getId());
		
		ps.executeUpdate();
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, null);
	}
	
	public TipoSolicitacaoWalterPaulo pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome, descricao FROM tipo_solicitacao "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		TipoSolicitacaoWalterPaulo retorno = new TipoSolicitacaoWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome").trim());
			retorno.setDescricao(rs.getString("descricao").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
	
	public List<TipoSolicitacaoWalterPaulo> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome, descricao FROM tipo_solicitacao";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<TipoSolicitacaoWalterPaulo> listaRetorno = new ArrayList<>();
		
		while (rs.next()) {
			TipoSolicitacaoWalterPaulo tipoSolicitacao = new TipoSolicitacaoWalterPaulo();
			tipoSolicitacao.setId(rs.getLong("id"));
			tipoSolicitacao.setNome(rs.getString("nome").trim());
			tipoSolicitacao.setDescricao(rs.getString("descricao").trim());
			listaRetorno.add(tipoSolicitacao);
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return listaRetorno;
	}
	public TipoSolicitacaoWalterPaulo validarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoWalterPaulo.conectarNoBancoDeDados();
		String sql = "SELECT id, nome, descricao FROM tipo_solicitacao "
				+ "WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		TipoSolicitacaoWalterPaulo retorno = new TipoSolicitacaoWalterPaulo();
		if (rs.next()) {
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome").trim());
			retorno.setDescricao(rs.getString("descricao").trim());
		}
		
		ConexaoWalterPaulo.fecharConexao(ps, conn, rs);
		return retorno;
	}
}
