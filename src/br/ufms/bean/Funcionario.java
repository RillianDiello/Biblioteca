package br.ufms.bean;

import br.ufms.dao.DaoFuncionario;


public class Funcionario {
	private long codigo;
	private String cpf;
	private String login;
	private String senha;
	private String nome;
	private DaoFuncionario df;
	
	public Funcionario(){
		this.cpf = "";
		this.login = "";
		this.senha = "";
		this.nome = "";
		
		this.df = new DaoFuncionario(this);
	}
	
	public Funcionario(String nome, String cpf, String login, String senha){
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		
		this.df = new DaoFuncionario(this);
	}
	
	public void cadastrarFuncionario(){
		
		if(df.salvar()){
			System.out.println("Funcionario inserido com sucesso!");
		}else{
			System.out.println("Funcionario já cadastrado!");
		}
		
	}
	
	public void buscarFuncionario(){
		
		if(df.buscar()){
			System.out.println("Funcionario existente!");
		}else{
			System.out.println("Funcionario não encontrado!");
		}
		
	}
	
	public void excluirFuncionario(){
		
		if(df.excluir()){
			System.out.println("Funcionario excluído com sucesso!");
		}else{
			System.out.println("Funcionario não existente!");
		}
		
	}
	
	public void editarFuncionario(){
		
	}
	
	public int getCodigoFuncionario(){
		return df.getId();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DaoFuncionario getDf() {
		return df;
	}

	public void setDf(DaoFuncionario df) {
		this.df = df;
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	

}