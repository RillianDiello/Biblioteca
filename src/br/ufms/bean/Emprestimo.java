package br.ufms.bean;

import java.util.Date;

import br.ufms.dao.DaoEmprestimo;
import br.ufms.dao.DaoExemplar;
import br.ufms.dao.DaoUsuario;

public class Emprestimo {
	private long codigo;
	private Date dataEmprestimo;
	private Date dataPresvistaDevolucao;
	private boolean status;
	private Date dataDevolucao;
	private Usuario usuario;
	private Exemplar exemplar;
	private Funcionario funcionario;

	public Emprestimo() {

	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataPresvistaDevolucao() {
		return dataPresvistaDevolucao;
	}

	public void setDataPresvistaDevolucao(Date dataPesvistaDevolucao) {
		this.dataPresvistaDevolucao = dataPesvistaDevolucao;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Emprestimo buscarEmprestimo(Emprestimo emprestimo) {
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		return daoEmprestimo.buscarEmprestimo(emprestimo);
	}

	public int quantidadeExemplaresEmprestados(Usuario usuario) {
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		return daoEmprestimo.quantidadeExemplaresEmprestados(usuario
				.getCodigoRegistro());
	}

	public boolean realizarEmprestimo(Usuario u, Exemplar e, Funcionario f) {
		if (u.situacaoUsuarioDisponivel() && e.situacaoExemplarDisponivel()) {
			setUsuario(u);
			setExemplar(e);
			setFuncionario(f);
			DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
			return daoEmprestimo.realizarEmprestimo(usuario, exemplar, funcionario);
		}
		return false;
	}

	public void emitirComprovanteEmprestimo() {
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		Emprestimo e = daoEmprestimo.buscarUltimoEmprestimo(this);

		System.out.println("\n-----------------------------------");
		System.out.println("Emprestimo realizado com sucesso!");
		System.out.println("Código do Exemplar: " + getExemplar().getCodigo());
		System.out.println("Descricao do Exemplar: "
				+ getExemplar().getCodigoRecurso());//.getTitulo());
		System.out.println("Data prevista de devolução: "
				+ e.dataPresvistaDevolucao);
		System.out.println("-----------------------------------\n");
	}

	public boolean realizarDevolucao(Usuario u, Exemplar e, Funcionario f) {
		DaoExemplar daoExemplar = new DaoExemplar();
		Exemplar exemplar = daoExemplar.buscarExemplarEmptestado(e.getCodigoRecurso(), e.getCampus());
		setUsuario(u);
		setExemplar(exemplar);
		setFuncionario(f);
		if (exemplar.getCodigo() == 0){
			System.out.println("Emprestimo não localizado.");
			return false;
		}
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		Emprestimo aux = daoEmprestimo.buscarEmprestimo(this);
		if (aux.getCodigo() == 0 || !aux.getStatus()) {
			System.out.println("Emprestimo não localizado.");
			return false;
		}
		setCodigo(aux.getCodigo());
		return daoEmprestimo.realizarDevolucao(this);
	}

	// public void emitirComprovanteDevolucaoComMulta() {
	// DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
	// Emprestimo e = daoEmprestimo.buscarUltimoEmprestimo(this);
	//
	// System.out.println("\n-----------------------------------");
	// System.out.println("Devolucao realizada com sucesso!");
	// System.out.println("Descricao do Exemplar: "
	// + getExemplar().getRecurso().getTitulo());
	// System.out.println("Data de emprestimo: " + e.dataEmprestimo);
	// System.out.println("Data prevista de devolução: "
	// + e.dataPresvistaDevolucao);
	// System.out.println("Data de devolução: " + e.dataDevolucao);
	// System.out.println("Nome do Usuário: " + getUsuario().getNome());
	// System.out.println("Codigo do Usuário: "
	// + getUsuario().getCodigoRegistro());
	// System.out.println("-----------------------------------\n");
	//
	// if (e.dataDevolucao.after(e.dataPresvistaDevolucao)) {
	// DaoUsuario daoUsuario = new DaoUsuario();
	// Usuario usuario = daoUsuario.buscarUsuario(getUsuario()
	// .getCodigoRegistro());
	// if (usuario.bloquearUsuarioComMulta(e.dataDevolucao)) {
	// System.out
	// .println("O usuário foi bloqueado devido atraso na devolução por "
	// + usuario.getCategoriaUsuario()
	// .getQuantidadeDiasPenalidade()
	// + " dias.");
	// }
	//
	// }
	// }

	public void emitirComprovanteDevolucao() {
		DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
		Emprestimo e = daoEmprestimo.buscarUltimoEmprestimo(this);

		System.out.println("\n-----------------------------------");
		System.out.println("Devolucao realizada com sucesso!");
		System.out.println("Codigo do Recurso: "
				+ getExemplar().getCodigoRecurso());
		System.out.println("Data de emprestimo: " + e.dataEmprestimo);
		System.out.println("Data prevista de devolução: "
				+ e.dataPresvistaDevolucao);
		System.out.println("Data de devolução: " + e.dataDevolucao);
		System.out.println("Nome do Usuário: " + getUsuario().getNome());
		System.out.println("Codigo do Usuário: "
				+ getUsuario().getCodigoRegistro());
		System.out.println("-----------------------------------\n");

		if (e.dataDevolucao.after(e.dataPresvistaDevolucao)) {
			DaoUsuario daoUsuario = new DaoUsuario();
			Usuario usuario = daoUsuario.buscarUsuario(getUsuario()
					.getCodigoRegistro());
			if (daoEmprestimo.emprestimosPendentes(usuario.getCodigo())) {
				if (usuario.bloquearUsuarioSemMulta()) {
					System.out
							.println("O usuário foi bloqueado devido atraso na devolução e possuir pendencias");
				}
			} else {
				if (usuario.getBloqueado()) {
					if (usuario.desbloquearUsuario()) {
						System.out.println("O usuário foi desbloqueado");
					}
				}
			}
		}
	}

}
