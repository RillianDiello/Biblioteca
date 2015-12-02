package br.ufms.bean;

import br.ufms.dao.DaoRecurso;

public class Recurso {
	private long codigo;
	private String titulo;
	private String classificacao;
	private long ISBN;
	private String autores;
	private int edicao;
	private String editora;
	private int ano;
	private String assunto;
	private String palavrasChave;


	public Recurso() {
	}

	public Recurso(String titulo) {
		this.titulo = titulo;
	}
	
	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public boolean incluirRecurso(Recurso recurso){
		DaoRecurso daoRecurso = new DaoRecurso();
		return daoRecurso.incluirRecurso(recurso);
	}
	
	public Recurso buscarRecurso(Recurso recurso){
		DaoRecurso daoRecurso = new DaoRecurso();
		return daoRecurso.buscarRecurso(recurso.getISBN());
	}
}
