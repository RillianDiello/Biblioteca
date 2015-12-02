package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.CategoriaUsuario;
import br.ufms.factory.ConnectionFactory;

public class DaoCategoriaUsuario {
	private final Connection connection;

	public DaoCategoriaUsuario() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean incluirCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaUsuario where descricao = '"
					+ categoriaUsuario.getDescricao() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			}
			sql = "INSERT INTO categoriaUsuario (descricao, numeroexemplares, "
					+ "tempoemprestimo) "
					+ "VALUES(?,?,?)";
			
//			sql = "INSERT INTO categoriaUsuario (descricao, numeroexemplares, "
//					+ "tempoemprestimo, quantidadediaspenalidade) "
//					+ "VALUES(?,?,?,?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			
			psstmt.setString(1, categoriaUsuario.getDescricao());
			psstmt.setInt(2, categoriaUsuario.getNumeroExemplares());
			psstmt.setInt(3, categoriaUsuario.getTempoEmprestimo());
			//psstmt.setInt(4, categoriaUsuario.getQuantidadeDiasPenalidade());
			
			psstmt.execute();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}
	
    public CategoriaUsuario buscarCategoriaUsuario(String descricao) {
        try {
            Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaUsuario where descricao = '"
					+ descricao + "'";
			ResultSet rs = stmt.executeQuery(sql);
			CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
            if (rs.next()) {
                categoriaUsuario.setCodigo(rs.getLong("codigo"));
                categoriaUsuario.setDescricao(rs.getString("descricao"));
                categoriaUsuario.setNumeroExemplares(rs.getInt("numeroExemplares"));
                //categoriaUsuario.setQuantidadeDiasPenalidade(rs.getInt("quantidadeDiasPenalidade"));
                categoriaUsuario.setTempoEmprestimo(rs.getInt("tempoEmprestimo"));
            }
            return categoriaUsuario;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public CategoriaUsuario buscarCategoriaUsuario(long codigo) {
        try {
            Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaUsuario where codigo = '"
					+ codigo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
            if (rs.next()) {
                categoriaUsuario.setCodigo(rs.getLong("codigo"));
                categoriaUsuario.setDescricao(rs.getString("descricao"));
                categoriaUsuario.setNumeroExemplares(rs.getInt("numeroExemplares"));
                //categoriaUsuario.setQuantidadeDiasPenalidade(rs.getInt("quantidadeDiasPenalidade"));
                categoriaUsuario.setTempoEmprestimo(rs.getInt("tempoEmprestimo"));
            }
            return categoriaUsuario;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
