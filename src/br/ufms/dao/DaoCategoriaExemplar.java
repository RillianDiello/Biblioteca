package br.ufms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.CategoriaExemplar;
import br.ufms.factory.ConnectionFactory;

public class DaoCategoriaExemplar {
	private final Connection connection;

	public DaoCategoriaExemplar() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean incluirCategoriaExemplar(CategoriaExemplar categoriaExemplar) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaExemplar where descricao = '"
					+ categoriaExemplar.getDescricao() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			}
			sql = "INSERT INTO categoriaExemplar (descricao) VALUES(?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			
			psstmt.setString(1, categoriaExemplar.getDescricao());
			
			psstmt.execute();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}
	
    public CategoriaExemplar buscarCategoriaExemplar(String descricao) {
        try {
            Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaExemplar where descricao = '"
					+ descricao + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			CategoriaExemplar categoriaExemplar = new CategoriaExemplar();
            if (rs.next()) {
            	categoriaExemplar.setCodigo(rs.getLong("codigo"));
                categoriaExemplar.setDescricao(rs.getString("descricao"));
            }
            return categoriaExemplar;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public CategoriaExemplar buscarCategoriaExemplar(long codigo) {
        try {
            Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM categoriaExemplar where codigo = '"
					+ codigo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			CategoriaExemplar categoriaExemplar = new CategoriaExemplar();
            if (rs.next()) {
            	categoriaExemplar.setCodigo(rs.getLong("codigo"));
                categoriaExemplar.setDescricao(rs.getString("descricao"));
            }
            return categoriaExemplar;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
	
}