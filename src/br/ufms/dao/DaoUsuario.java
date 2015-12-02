package br.ufms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.Usuario;
import br.ufms.factory.ConnectionFactory;

public class DaoUsuario {
	private final Connection connection;

	public DaoUsuario() {
		connection = new ConnectionFactory().getConnection();
	}

	public boolean incluirUsuario(Usuario usuario) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM usuario where cpf = '"
					+ usuario.getCPF() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return false;
			}

			Date dataNascimento = new Date(usuario.getDataNascimento()
					.getTime());
			// Date dataLiberacao = new
			// Date(usuario.getDataLiberacao().getTime());

			sql = "INSERT INTO usuario (nome, endereco, cidade, estado, "
					+ "telefone, sexo, cpf, codigoregistro, email, dataNascimento, "
					+ "bloqueado, motivoBloqueio, codigoCategoriaUsuario) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// sql = "INSERT INTO usuario (nome, endereco, cidade, estado, "
			// + "telefone, sexo, cpf, codigoregistro, email, dataNascimento, "
			// +
			// "bloqueado, motivoBloqueio, codigoCategoriaUsuario, dataLiberacao) "
			// + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psstmt = connection.prepareStatement(sql);

			psstmt.setString(1, usuario.getNome());
			psstmt.setString(2, usuario.getEndereco());
			psstmt.setString(3, usuario.getCidade());
			psstmt.setString(4, usuario.getEstado());
			psstmt.setString(5, usuario.getTelefone());
			psstmt.setString(6, usuario.getSexo());
			psstmt.setLong(7, usuario.getCPF());
			psstmt.setLong(8, usuario.getCodigoRegistro());
			psstmt.setString(9, usuario.getEmail());
			psstmt.setDate(10, dataNascimento);
			psstmt.setBoolean(11, usuario.getBloqueado());
			psstmt.setString(12, usuario.getMotivoBloqueio());
			psstmt.setLong(13, usuario.getCategoriaUsuario().getCodigo());
			// psstmt.setDate(14, dataLiberacao);

			psstmt.execute();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}

	public Usuario buscarUsuario(long codigoRegistro) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM usuario where codigoregistro = '"
					+ codigoRegistro + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Usuario usuario = new Usuario();
			DaoCategoriaUsuario daoCategoriaUsuario = new DaoCategoriaUsuario();
			if (rs.next()) {
				usuario.setCodigo(rs.getLong("codigo"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setCPF(rs.getLong("cpf"));
				usuario.setCodigoRegistro(rs.getLong("codigoregistro"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDataNascimento(rs.getDate("dataNascimento"));
				usuario.setBloqueado(rs.getBoolean("bloqueado"));
				usuario.setMotivoBloqueio(rs.getString("motivoBloqueio"));
				// usuario.setDataLiberacao(rs.getDate("dataLiberacao"));
				usuario.setCategoriaUsuario(daoCategoriaUsuario
						.buscarCategoriaUsuario(rs
								.getLong("codigoCategoriaUsuario")));
			}
			return usuario;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	// @SuppressWarnings("deprecation")
	// public boolean bloquearUsuarioComMulta(long codigoRegistro,
	// java.util.Date dataDevolucao) {
	// try {
	// Usuario u = buscarUsuario(codigoRegistro);
	// java.util.Date dataLiberacao = new java.util.Date();
	// dataLiberacao.setDate(dataDevolucao.getDate()
	// + u.getCategoriaUsuario().getQuantidadeDiasPenalidade());
	//
	// String sql = "UPDATE usuario SET bloqueado = true, "
	// + "motivoBloqueio = 'Atraso na devolucao',"
	// + "dataliberacao = '"
	// + new Timestamp(dataLiberacao.getTime())
	// + "' WHERE codigoRegistro = '" + codigoRegistro + "'";
	// PreparedStatement psstmt = connection.prepareStatement(sql);
	// psstmt.execute();
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// return true;
	// }

	public boolean bloquearUsuarioSemMulta(long codigoRegistro) {
		try {
			String sql = "UPDATE usuario SET bloqueado = true, "
					+ "motivoBloqueio = 'Atraso na devolucao e possui pendências'"
					+ " WHERE codigoRegistro = '" + codigoRegistro + "'";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			psstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public boolean desbloquearUsuario(long codigoRegistro) {
		try {
			String sql = "UPDATE usuario SET bloqueado = false WHERE codigoRegistro = '"
					+ codigoRegistro + "'";
			PreparedStatement psstmt = connection.prepareStatement(sql);
			psstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;

	}
}
