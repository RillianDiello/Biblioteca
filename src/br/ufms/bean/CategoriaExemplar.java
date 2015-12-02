package br.ufms.bean;

import br.ufms.dao.DaoCategoriaExemplar;

public class CategoriaExemplar {
	private long codigo;
	private String descricao;

	public CategoriaExemplar() {
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
	
	public boolean incluirCategoriaExemplar(CategoriaExemplar categoriaExemplar){
		DaoCategoriaExemplar daoCategoriaExemplar = new DaoCategoriaExemplar();
		return daoCategoriaExemplar.incluirCategoriaExemplar(categoriaExemplar);
	}
	
	public CategoriaExemplar buscarCategoriaExemplar(String descricao){
		//System.out.println("desc" + descricao);
		DaoCategoriaExemplar daoCategoriaExemplar = new DaoCategoriaExemplar();
		return daoCategoriaExemplar.buscarCategoriaExemplar(descricao); 
	}
	

}
