package br.ufms.bean;

import br.ufms.dao.DaoCategoriaUsuario;

public class CategoriaUsuario {
	private long codigo;
	private String descricao;
	private int numeroExemplares;
	private int tempoEmprestimo;
//	private int quantidadeDiasPenalidade;
	
	public CategoriaUsuario(){
	}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumeroExemplares() {
		return numeroExemplares;
	}
	public void setNumeroExemplares(int numeroExemplares) {
		this.numeroExemplares = numeroExemplares;
	}
	public int getTempoEmprestimo() {
		return tempoEmprestimo;
	}
	public void setTempoEmprestimo(int tempoEmprestimo) {
		this.tempoEmprestimo = tempoEmprestimo;
	}
//	public int getQuantidadeDiasPenalidade() {
//		return quantidadeDiasPenalidade;
//	}
//	public void setQuantidadeDiasPenalidade(int quantidadeDiasPenalidade) {
//		this.quantidadeDiasPenalidade = quantidadeDiasPenalidade;
//	}
	
	public boolean incluirCategoriaUsuario(CategoriaUsuario categoriaUsuario){
		DaoCategoriaUsuario daoCategoriaExemplar = new DaoCategoriaUsuario();
		return daoCategoriaExemplar.incluirCategoriaUsuario(categoriaUsuario);
	}
	
	public CategoriaUsuario buscarCategoriaUsuario(CategoriaUsuario categoriaUsuario){
		DaoCategoriaUsuario daoCategoriaExemplar = new DaoCategoriaUsuario();
		return daoCategoriaExemplar.buscarCategoriaUsuario(categoriaUsuario.getDescricao());
	}
	
}
