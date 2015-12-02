package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import br.ufms.bean.Emprestimo;
import br.ufms.bean.Exemplar;
import br.ufms.bean.Funcionario;
import br.ufms.bean.Usuario;
import br.ufms.factory.ConnectionFactory;

public class DaoEmprestimo {
	private final Connection connection;

	public DaoEmprestimo() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean emprestimosPendentes(long codigo) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM emprestimo where codigousuario = '"
					+ codigo + "' AND status = true";
			ResultSet rs = stmt.executeQuery(sql);
			return rs.first();
			
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Emprestimo buscarEmprestimo(Emprestimo emprestimo) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM emprestimo where codigoExemplar = '"
					+ emprestimo.getExemplar().getCodigo()
					+ "' AND codigoUsuario = '"
					+ emprestimo.getUsuario().getCodigo()
					+ "' AND status = true";
			ResultSet rs = stmt.executeQuery(sql);
			Emprestimo emprestimoRetorno = new Emprestimo();
			if (rs.next()) {
				emprestimoRetorno.setCodigo(rs.getLong("codigo"));
				emprestimoRetorno.setDataEmprestimo(rs
						.getDate("dataEmprestimo"));
				emprestimoRetorno.setDataPresvistaDevolucao(rs
						.getDate("dataPrevistaDevolucao"));
				emprestimoRetorno.setStatus(rs.getBoolean("status"));
				emprestimoRetorno.setDataDevolucao(rs.getDate("dataDevolucao"));
			}
			return emprestimoRetorno;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Emprestimo buscarUltimoEmprestimo(Emprestimo emprestimo) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM emprestimo where codigoExemplar = '"
					+ emprestimo.getExemplar().getCodigo()
					+ "' AND codigoUsuario = '"
					+ emprestimo.getUsuario().getCodigo() + "' AND status = false";
					
			
			
			ResultSet rs = stmt.executeQuery(sql);
			Emprestimo emprestimoRetorno = new Emprestimo();
			if (rs.last()) {
				emprestimoRetorno.setCodigo(rs.getLong("codigo"));
				emprestimoRetorno.setDataEmprestimo(rs
						.getTimestamp("dataEmprestimo"));
				emprestimoRetorno.setDataPresvistaDevolucao(rs
						.getTimestamp("dataPrevistaDevolucao"));
				emprestimoRetorno.setStatus(rs.getBoolean("status"));
				emprestimoRetorno.setDataDevolucao(rs.getTimestamp("dataDevolucao"));
			}
			return emprestimoRetorno;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public int quantidadeExemplaresEmprestados(long codigoUsuario) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM emprestimo where codigousuario = '"
					+ codigoUsuario + "' AND status = true";
			ResultSet rs = stmt.executeQuery(sql);
			int cont = 0;
			while (rs.next()) {
				cont++;
			}
			return cont;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@SuppressWarnings("deprecation")
	public boolean realizarEmprestimo(Usuario usuario, Exemplar exemplar, Funcionario funcionario) {
		try {

			java.util.Date dataE = new java.util.Date();
			java.util.Date dataPD = new java.util.Date();
			dataPD.setDate(dataE.getDate()
					+ usuario.getCategoriaUsuario().getTempoEmprestimo());
			
			String sql = "INSERT INTO emprestimo (dataEmprestimo , dataPrevistaDevolucao, status, codigousuario, codigoexemplar, codigoFuncionario) VALUES(?,?,?,?,?,?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);

			psstmt.setTimestamp(1, new Timestamp(dataE.getTime()));
			psstmt.setTimestamp(2, new Timestamp(dataPD.getTime()));
			psstmt.setBoolean(3, true);
			psstmt.setLong(4, usuario.getCodigo());
			psstmt.setLong(5, exemplar.getCodigo());
			psstmt.setLong(6, funcionario.getCodigo());
			psstmt.execute();

			DaoExemplar daoExemplar = new DaoExemplar();
			daoExemplar.marcarExemplarEmprestado(exemplar);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public boolean realizarDevolucao(Emprestimo emprestimo) {
		try {
			java.util.Date dataEmprestimo = new java.util.Date();
			Timestamp param = new java.sql.Timestamp(dataEmprestimo.getTime());

			String sql = "UPDATE emprestimo SET status = false, dataDevolucao = '"
					+ param
					+ "' WHERE codigo = '"
					+ emprestimo.getCodigo()
					+ "'";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			psstmt.execute();

			DaoExemplar daoExemplar = new DaoExemplar();
			daoExemplar.marcarExemplarDisponivel(emprestimo.getExemplar());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}
}
