package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.Exemplar;
import br.ufms.factory.ConnectionFactory;

public class DaoExemplar {
	private final Connection connection;

	public DaoExemplar() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean incluirExemplar(Exemplar exemplar) {
		try {
			String sql = "INSERT INTO exemplar (status, campus, codigocategoriaexemplar, codigoRecurso) VALUES(?,?,?,?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);

			psstmt.setBoolean(1, exemplar.getStatus());
			psstmt.setString(2, exemplar.getCampus());
			psstmt.setLong(3, exemplar.getCategoriaExemplar().getCodigo());
			psstmt.setLong(4, exemplar.getCodigoRecurso());

			psstmt.execute();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}

	public Exemplar buscarExemplar(long codigoRecurso, String campus) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM exemplar where codigoRecurso = '" + codigoRecurso 
					+ "' and campus = '" + campus + "';";
			ResultSet rs = stmt.executeQuery(sql);

			Exemplar exemplar = new Exemplar();
			DaoCategoriaExemplar daoCategoriaExemplar = new DaoCategoriaExemplar();
			DaoRecurso daoRecurso = new DaoRecurso();
			if (rs.next()) {
				exemplar.setCodigo(rs.getLong("codigo"));
				exemplar.setStatus(rs.getBoolean("status"));
				exemplar.setCampus(rs.getString("campus"));
				exemplar.setCategoriaExemplar(daoCategoriaExemplar.buscarCategoriaExemplar(rs.getLong("codigoCategoriaExemplar")));
				exemplar.setCodigoRecurso(rs.getInt("codigoRecurso"));

			}
			return exemplar;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Exemplar buscarExemplarDisponivel(int codigoRecurso, String campus) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM exemplar where codigoRecurso = '" + codigoRecurso
					+ "' AND status = true AND campus = '" + campus + "';";
			ResultSet rs = stmt.executeQuery(sql);
			Exemplar exemplar = new Exemplar();
			DaoCategoriaExemplar daoCategoriaExemplar = new DaoCategoriaExemplar();
			DaoRecurso daoRecurso = new DaoRecurso();
			if (rs.next()) {
				exemplar.setCodigo(rs.getLong("codigo"));
				exemplar.setStatus(rs.getBoolean("status"));
				exemplar.setCampus(rs.getString("campus"));
				exemplar.setCategoriaExemplar(daoCategoriaExemplar
						.buscarCategoriaExemplar(rs
								.getLong("codigoCategoriaExemplar")));
				exemplar.setCodigoRecurso(rs.getInt("codigoRecurso"));
			}
			return exemplar;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public Exemplar buscarExemplarEmptestado(int codigoRecurso, String campus) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM exemplar where codigoRecurso = '" + codigoRecurso
					+ "' AND status = false AND campus = '" + campus + "';";
			ResultSet rs = stmt.executeQuery(sql);
			Exemplar exemplar = new Exemplar();
			DaoCategoriaExemplar daoCategoriaExemplar = new DaoCategoriaExemplar();
			DaoRecurso daoRecurso = new DaoRecurso();
			if (rs.next()) {
				exemplar.setCodigo(rs.getLong("codigo"));
				exemplar.setStatus(rs.getBoolean("status"));
				exemplar.setCampus(rs.getString("campus"));
				exemplar.setCategoriaExemplar(daoCategoriaExemplar
						.buscarCategoriaExemplar(rs
								.getLong("codigoCategoriaExemplar")));
				exemplar.setCodigoRecurso(rs.getInt("codigoRecurso"));
			}
			return exemplar;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	public void marcarExemplarEmprestado(Exemplar exemplar) {
		try {
			String sql = "UPDATE exemplar SET status = false "
					+ "WHERE codigoRecurso = '" + exemplar.getCodigoRecurso()
					+ "' AND codigo = '" + exemplar.getCodigo() + "';";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			psstmt.execute();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void marcarExemplarDisponivel(Exemplar exemplar) {
		try {
			String sql = "UPDATE exemplar SET status = true "
					+ "WHERE codigoRecurso = '" + exemplar.getCodigoRecurso()
					+ "' AND codigo = '" + exemplar.getCodigo() + "';";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			psstmt.execute();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
