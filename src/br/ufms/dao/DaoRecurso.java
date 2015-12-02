package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.Recurso;
import br.ufms.factory.ConnectionFactory;

public class DaoRecurso {
	private final Connection connection;

	public DaoRecurso() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean incluirRecurso(Recurso recurso) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM recurso where isbn = '"
					+ recurso.getISBN() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			}
			sql = "INSERT INTO recurso (titulo, classificacao,"
					+ " isbn, autores, edicao, editora, ano, assunto, palavraschave) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);

			psstmt.setString(1, recurso.getTitulo());
			psstmt.setString(2, recurso.getClassificacao());
			psstmt.setLong(3, recurso.getISBN());
			psstmt.setString(4, recurso.getAutores());
			psstmt.setInt(5, recurso.getEdicao());
			psstmt.setString(6, recurso.getEditora());
			psstmt.setInt(7, recurso.getAno());
			psstmt.setString(8, recurso.getAssunto());
			psstmt.setString(9, recurso.getPalavrasChave());

			psstmt.execute();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}

	public Recurso buscarRecurso(long isbn) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM recurso where isbn = '" + isbn + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Recurso recurso = new Recurso();
			if (rs.next()) {
				recurso.setCodigo(rs.getLong("codigo"));
				recurso.setTitulo(rs.getString("titulo"));
				recurso.setClassificacao(rs.getString("classificacao"));
				recurso.setISBN(rs.getLong("isbn"));
				recurso.setAutores(rs.getString("autores"));
				recurso.setEdicao(rs.getInt("edicao"));
				recurso.setEditora(rs.getString("editora"));
				recurso.setAno(rs.getInt("ano"));
				recurso.setAssunto(rs.getString("assunto"));
				recurso.setPalavrasChave("palavrasChave");
			}
			return recurso;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
