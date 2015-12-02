package br.ufms.bean;

import br.ufms.dao.DaoExemplar;

public class Exemplar {
	private long codigo;
	CategoriaExemplar categoriaExemplar;
	private int Codigorecurso;
	private boolean status;
	private String campus;

	public Exemplar() {
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public CategoriaExemplar getCategoriaExemplar() {
		return categoriaExemplar;
	}

	public void setCategoriaExemplar(CategoriaExemplar categoriaExemplar) {
		this.categoriaExemplar = categoriaExemplar;
	}
	
	
	public int getCodigoRecurso() {
		return Codigorecurso;
	}

	public void setCodigoRecurso(int Codigorecurso) {
		this.Codigorecurso= Codigorecurso;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public boolean incluirExemplar(Exemplar exemplar){
		DaoExemplar daoExemplar = new DaoExemplar();
		return daoExemplar.incluirExemplar(exemplar);
	}

	
	public Exemplar buscarExemplarDisponivel(Exemplar exemplar){
		DaoExemplar daoExemplar = new DaoExemplar();
		return daoExemplar.buscarExemplarDisponivel(Codigorecurso, campus);
	}
	
	public void marcarExemplarEmprestado(Exemplar exemplar){
		DaoExemplar daoExemplar = new DaoExemplar();
		daoExemplar.marcarExemplarEmprestado(exemplar);
	}
	
	public boolean situacaoExemplarDisponivel(){
		if (getCodigo() == 0){
			System.out.println("Não foi possível localizar esse exemplar.");
			return false;
		}
		
		DaoExemplar daoExemplar = new DaoExemplar();
		Exemplar e = daoExemplar.buscarExemplarDisponivel(Codigorecurso, campus);

		if (e.getCodigo() == 0){
			System.out.println("Recurso não possui exemplares disponíveis para emprestimo");
			return false;
		}
		
		this.codigo = e.codigo;
		this.categoriaExemplar = e.categoriaExemplar;
		this.Codigorecurso = e.Codigorecurso;
		this.status = e.status;
		this.campus = e.campus;
		

		return true;
	}
	
	
}
