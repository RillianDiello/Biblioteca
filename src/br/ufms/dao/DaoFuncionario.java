package br.ufms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufms.bean.Funcionario;

import br.ufms.factory.ConnectionFactory;

public class DaoFuncionario {
	private final Connection connection;
	private Funcionario f;

	public DaoFuncionario(Funcionario f) {
		connection = new ConnectionFactory().getConnection();
		this.f = f;
	}

	public boolean salvar() {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Funcionario where cpf = '"
					+ f.getCpf() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs.next()) {
				return false;
			}

			String sqlInsert = "INSERT INTO Funcionario (nome, login, senha, cpf) VALUES(?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sqlInsert);
			ps.setString(1, f.getNome());
			ps.setString(2, f.getLogin());
			ps.setString(3, f.getSenha());
			ps.setString(4, f.getCpf());

			ps.execute();

			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean buscar() {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Funcionario where cpf = '"
					+ f.getCpf() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void retornaFunc() {
		try {
			
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Funcionario where cpf = '"
					+ f.getCpf() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs != null) {
				if (rs.next()) {
					f.setCodigo(rs.getLong("idfuncionario"));
					f.setCpf(rs.getString("cpf"));
					f.setNome(rs.getString("nome"));
					f.setLogin(rs.getString("login"));
					f.setSenha(rs.getString("senha"));
				}
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public void login(){	
		try {
			
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Funcionario where login = '"
					+ f.getLogin() + "' and senha = '" + f.getSenha() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (rs != null) {
				if (rs.next()) {
					f.setCodigo(rs.getLong("idfuncionario"));
					f.setCpf(rs.getString("cpf"));
					f.setNome(rs.getString("nome"));
					f.setLogin(rs.getString("login"));
					f.setSenha(rs.getString("senha"));
				}
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean excluir() {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT * FROM Funcionario where cpf = '"
					+ f.getCpf() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			if (!rs.next()) {
				return false;
			} else {

				String sqlDelete = "DELETE FROM Funcionario WHERE cpf = ?;";
				PreparedStatement ps = connection.prepareStatement(sqlDelete);
				ps.setString(1, f.getCpf());

				ps.execute();
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getId() {
		try {
			Statement stmt = connection.createStatement();
			String sqlConsulta = "SELECT (idfuncionario) FROM Funcionario where cpf = '"
					+ f.getCpf() + "';";
			ResultSet rs = stmt.executeQuery(sqlConsulta);
			return rs.getInt("idfuncionario");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
