package br.ufms.bean;

import java.util.Date;

import br.ufms.dao.DaoEmprestimo;
import br.ufms.dao.DaoUsuario;

public class Usuario {
	private long codigo;
	private String nome;
	private long CPF;
	private long codigoRegistro;
	private String endereco;
	private String cidade;
	private String estado;
	private String telefone;
	private String sexo;
	private String email;
	private Date dataNascimento;
	private boolean bloqueado;
	private String motivoBloqueio;
//	private Date dataLiberacao;
	private CategoriaUsuario categoriaUsuario;

	public Usuario() {
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCPF() {
		return CPF;
	}

	public void setCPF(long CPF) {
		this.CPF = CPF;
	}

	public long getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(long codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}

	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}

//	public Date getDataLiberacao() {
//		return dataLiberacao;
//	}
//
//	public void setDataLiberacao(Date dataLiberacao) {
//		this.dataLiberacao = dataLiberacao;
//	}

	public CategoriaUsuario getCategoriaUsuario() {
		return categoriaUsuario;
	}

	public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
		this.categoriaUsuario = categoriaUsuario;
	}
	
	public boolean incluirUsuario(Usuario usuario) {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.incluirUsuario(usuario);
	}

	public Usuario buscarUsuario(long codigoRegistro) {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.buscarUsuario(codigoRegistro);
	}
	
	public boolean situacaoUsuarioDisponivel() {
		if (codigo == 0) {
			System.out.println("Usuario não cadastrado no sistema.");
			return false;
		}
		return (!usuarioExeceuLimite() && !usuarioBloqueadoSemMulta());
	}
	
	public boolean usuarioExeceuLimite() {
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.buscarUsuario(codigoRegistro);
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		if (usuario.getCategoriaUsuario().getNumeroExemplares() <= daoEmprestimo
				.quantidadeExemplaresEmprestados(usuario.getCodigo())) {
			System.out
					.println("Usuário excedeu o limite de livros para empréstimo.");
			return true;
		}
		return false;
	}


//	public boolean usuarioBloqueadoComMulta() {
//		DaoUsuario daoUsuario = new DaoUsuario();
//		Usuario usuario = daoUsuario.buscarUsuario(codigoRegistro);
//		if (usuario.getBloqueado() && usuario.dataLiberacao.before(new Date())) {
//			if (usuario.desbloquearUsuario()) {
//				System.out.println("O usuário foi desbloqueado da penalidade");
//			}
//		} else {
//			if (usuario.getBloqueado()) {
//				System.out.println("O usuário está bloqueado até: "
//						+ usuario.getDataLiberacao() + " devido: "
//						+ usuario.getMotivoBloqueio());
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean usuarioBloqueadoSemMulta() {
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.buscarUsuario(codigoRegistro);
		if (usuario.getBloqueado()) {
			System.out.println("O usuário está bloqueado devido: "
					+ usuario.getMotivoBloqueio());
			return true;
		}
		return false;
	}

//	public boolean bloquearUsuarioComMulta(Date dataDevolucao) {
//		DaoUsuario daoUsuario = new DaoUsuario();
//		return daoUsuario
//				.bloquearUsuarioComMulta(codigoRegistro, dataDevolucao);
//	}

	public boolean bloquearUsuarioSemMulta() {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.bloquearUsuarioSemMulta(codigoRegistro);
	}

	public boolean desbloquearUsuario() {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.desbloquearUsuario(codigoRegistro);
	}

}
