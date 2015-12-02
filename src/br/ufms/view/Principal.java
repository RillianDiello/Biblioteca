package br.ufms.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import br.ufms.bean.CategoriaExemplar;
import br.ufms.bean.CategoriaUsuario;
import br.ufms.bean.Emprestimo;
import br.ufms.bean.Exemplar;
import br.ufms.bean.Funcionario;
import br.ufms.bean.Recurso;
import br.ufms.bean.Usuario;
import br.ufms.dao.DaoExemplar;
import br.ufms.dao.DaoFuncionario;
import br.ufms.factory.ConnectionFactory;

public class Principal {
	
	private static Exemplar exemplar;
	private static CategoriaExemplar categoriaExemplar;
	private static Recurso recurso;
	private static Usuario usuario;
	private static CategoriaUsuario categoriaUsuario;
	private static Funcionario funcionario;

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException {
	
		try (Connection connection = new ConnectionFactory().getConnection()) {
			System.out.println("Conexão aberta!");
			connection.close();
		}
		
		Scanner s = new Scanner(System.in);
		int op;
		do{
			System.out.print("1 - Entrar. \n 2 - Sair. \n Opção: ");
			op = s.nextInt();
			switch(op){
				case 1 : if(login()){
							menu();
						 }else{
							 System.out.println("Senha Incorreta");
						 }break;
			}
				
		}while(op != 2);
				
/*
		 // Cadastra uma categoria de usuário
		 CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
		 categoriaUsuario.setDescricao("Servidor");
		 categoriaUsuario.setNumeroExemplares(3);
		 //categoriaUsuario.setQuantidadeDiasPenalidade(3);
		 categoriaUsuario.setTempoEmprestimo(3);
		 if (categoriaUsuario.incluirCategoriaUsuario(categoriaUsuario)) {
		 System.out.println("Categoria: " + categoriaUsuario.getDescricao()
		 + " cadastrada com successo!");
		 } else {
		 System.out.println("Problema no cadastro da categoria!");
		 }
		
		 //Cadastra uma categoria de exemplar
		 CategoriaExemplar categoriaExemplar = new CategoriaExemplar();
		 categoriaExemplar.setDescricao("Livro");
		 if (categoriaExemplar.incluirCategoriaExemplar(categoriaExemplar)) {
		 System.out.println("Categoria: " + categoriaExemplar.getDescricao()
		 + " cadastrada com successo!");
		 } else {
		 System.out.println("Problema no cadastro da categoria!");
		 }
		
		 // Cadastra um novo recurso
		 Recurso recurso = new Recurso();
		 recurso.setTitulo("Guidorize");
		 recurso.setClassificacao("12 anos");
		 recurso.setISBN(123456789);
		 recurso.setAutores("Paulo, Edilson");
		 recurso.setEdicao(3);
		 recurso.setEditora("UFMS");
		 recurso.setAno(2015);
		 recurso.setAssunto("Matemática, cálculo");
		 if (recurso.incluirRecurso(recurso)) {
		 System.out.println("Recurso: " + recurso.getTitulo()
		 + " cadastrado com successo!");
		 } else {
		 System.out.println("Problema no cadastro do recurso!");
		 }
		
		 // Cadastra um novo usuario
		 Usuario usuario = new Usuario();
		 usuario.setNome("Paulo");
		 usuario.setCPF(435922193);
		 usuario.setCodigoRegistro(437621123);
		 usuario.setEndereco("Rua anhanguera, n 213");
		 usuario.setCidade("Campo Grande");
		 usuario.setEstado("MS");
		 usuario.setTelefone("(67) 8291-1292");
		 usuario.setSexo("Masculino");
		 usuario.setEmail("email@ufms.br");
		 usuario.setDataNascimento(new Date(1993, 10, 15));
		 usuario.setBloqueado(false);
		 usuario.setMotivoBloqueio("Nenhum");
		 //usuario.setDataLiberacao(new Date(2015, 10, 15));
		 usuario.setCategoriaUsuario(categoriaUsuario
		 .buscarCategoriaUsuario(categoriaUsuario));
		 if (usuario.incluirUsuario(usuario)) {
		 System.out.println("Usuário: " + usuario.getNome()
		 + " cadastrado com successo!");
		 } else {
		 System.out.println("Problema no cadastro do usuário!");
		 }
		
		 // Cadastra um novo exemplar
		 Exemplar exemplar = new Exemplar();
		 exemplar.setStatus(true);
		 exemplar.setCampus("CG");
		 exemplar.setCategoriaExemplar(categoriaExemplar
		 .buscarCategoriaExemplar(categoriaExemplar));
		 exemplar.setRecurso(recurso);
		 if (exemplar.incluirExemplar(exemplar)) {
		 System.out.println("Exemplar: " + exemplar.getRecurso().getTitulo()
		 + " cadastrado com successo!");
		 } else {
		 System.out.println("Problema no cadastro do exemplar!");
		 }

		Usuario u = new Usuario();
		u = u.buscarUsuario(437621123);

		DaoExemplar DaoE = new DaoExemplar();
		Exemplar e = new Exemplar();
		e = DaoE.buscarExemplar(123456789, "CG");

		// Realiza um novo emprestimo
		Emprestimo emprestimo = new Emprestimo();
		if (emprestimo.realizarEmprestimo(u, e)) {
			emprestimo.emitirComprovanteEmprestimo();
		} else {
			System.out.println("Problema no cadastro do emprestimo!");
		}

		
		// Realiza uma devolução
		// Emprestimo emprestimo = new Emprestimo();
		if (emprestimo.realizarDevolucao(u, e)) {
			emprestimo.emitirComprovanteDevolucao();
		} else {
			System.out.println("Problema no cadastro da devolucao!");
		}
    */
	}
	
	public static boolean login(){
		
		Scanner s = new Scanner(System.in);
		funcionario = new Funcionario();
		
		System.out.print("Login: ");
		funcionario.setLogin(s.nextLine());
		
		System.out.print("Senha: ");
		funcionario.setSenha(s.nextLine());
		
		DaoFuncionario DaoF = new DaoFuncionario(funcionario);
		
		DaoF.login();
		
		if(!funcionario.getCpf().equals("")){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void menu(){
		Scanner s = new Scanner(System.in);
		int op;
		do { 
			System.out.println("1 - Adicionar. \n 2 - Empréstimo de exemplares. \n 3 - Devolução de exemplares. \n 0 - Sair.");
			op = s.nextInt();
			switch(op){
				case 1 :  System.out.println("1 - Adicionar Categoria. \n 2 - Adicionar Exemplar. \n 3 - Adicionar Recurso. \n" +
						" 4 - Adicionar Usuário. \n 5 - Adicionar Categoria de Usuário. \n 6 - Cadastrar Funcionario. ");
						  switch(s.nextInt()){
						  		case 1 : cadastrarCategoria();		  				
						  				 break;
						  		case 2 : cadastrarExemplar();
						  				break;
						  		case 3 : cadastrarRecurso();
						  				break;
						  		case 4 : cadastrarUsuario();
						  				break;
						  		case 5 : cadastrarCategoriaUsuario();
						  				break;
						  		case 6: cadastrarFuncionario();
						  				break;
						  } break;
				case 2 : emprestimoExemplar();
						 break;
				case 3 : devolucaoExemplar();
						 break;
			}
		}while(op!=0);
		
			
	}
	
	public static void cadastrarRecurso(){
		// Adicionar novo atributo
		 Scanner s = new Scanner(System.in);
		 recurso = new Recurso();
		 
		 System.out.println("Titulo: ");
		 recurso.setTitulo(s.nextLine());
		 
		 System.out.println("Classificação: ");	 
		 recurso.setClassificacao(s.nextLine());
		 
		 System.out.println("ISBN: ");	
		 long l = Long.parseLong(s.nextLine());		
		 recurso.setISBN(l);
		 
		 System.out.println("Autores: ");	
		 String autores = s.nextLine();
		 recurso.setAutores(autores);
		 
		 System.out.println("Edição: ");
		 int ed = Integer.parseInt(s.nextLine());
		 recurso.setEdicao(ed);
		 
		 System.out.println("Editora: ");		 
		 recurso.setEditora(s.nextLine());
		 
		 System.out.println("Ano: ");	
		 int year = Integer.parseInt(s.nextLine());
		 recurso.setAno(year);
		 
		 System.out.println("Assunto: ");	
		 recurso.setAssunto(s.nextLine());
		 
		 System.out.println("Palavras-Chave: ");
		 recurso.setPalavrasChave(s.nextLine());
		 
		 if (recurso.incluirRecurso(recurso)) {
			 System.out.println("Recurso: " + recurso.getTitulo()
			 + " cadastrado com successo!");
		 } else {
			 System.out.println("Problema no cadastro do recurso!");
		 }
	}
	
	public static void cadastrarCategoria(){		
		Scanner s = new Scanner(System.in);	
		categoriaExemplar = new CategoriaExemplar();
		
		 System.out.println("Descrição: ");
		 categoriaExemplar.setDescricao(s.nextLine());
		 
		 if (categoriaExemplar.incluirCategoriaExemplar(categoriaExemplar)) {
			 System.out.println("Categoria: " + categoriaExemplar.getDescricao()
			 + " cadastrada com successo!");
		 } else {
			 System.out.println("Problema no cadastro da categoria!");
		 }
	}
	
	public static void cadastrarExemplar(){
		Scanner s = new Scanner(System.in);	
		exemplar = new Exemplar();
		
		 //System.out.println("Status: ");
		 exemplar.setStatus(true);
		 
		 System.out.println("Campus: ");
		 exemplar.setCampus(s.nextLine());
		 
		// Variabilidade	 
		 System.out.println("Descricao Categoria: ");
		 String cat = s.nextLine();
		 //System.out.println("cat: " + cat);
		 CategoriaExemplar categoriaExemplar = new CategoriaExemplar();
		 exemplar.setCategoriaExemplar(categoriaExemplar.buscarCategoriaExemplar(cat));
		 
		 System.out.println("Codigo Recurso: ");
		 int cod = Integer.parseInt(s.nextLine());
		 exemplar.setCodigoRecurso(cod);
		 
		 if (exemplar.incluirExemplar(exemplar)) {
			 System.out.println("Exemplar: " + exemplar.getCodigoRecurso()
			 + " cadastrado com successo!");
		 } else {
			 System.out.println("Problema no cadastro do exemplar!");
		 }
	}
	
	public static void cadastrarUsuario(){
		Scanner s = new Scanner(System.in);	
		usuario = new Usuario();
		CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
		 
		 System.out.println("Nome: ");
		 usuario.setNome(s.nextLine());
		 
		 System.out.println("CPF: ");
		 long l = Long.parseLong(s.nextLine());	
		 usuario.setCPF(l);
		 
		 System.out.println("Codigo Registro: ");
		 l = Long.parseLong(s.nextLine());	
		 usuario.setCodigoRegistro(l);
		 
		 System.out.println("Endereco: ");
		 usuario.setEndereco(s.nextLine());
		 
		 System.out.println("Cidade: ");
		 usuario.setCidade(s.nextLine());
		 
		 System.out.println("Estado: ");
		 usuario.setEstado(s.nextLine());
		 
		 System.out.println("Telefone: ");
		 usuario.setTelefone(s.nextLine());
		 
		 System.out.println("Sexo");
		 usuario.setSexo(s.nextLine());
		 
		 System.out.println("Email: ");
		 usuario.setEmail(s.nextLine());
		 
		 System.out.print("Data: ");
		 
		 /*int ano = Integer.parseInt(s.nextLine());		
		 System.out.print("/");
		 int mes = Integer.parseInt(s.nextLine());
		 System.out.print("/");
		 int dia = Integer.parseInt(s.nextLine());*/
		 
		 String data[] = s.nextLine().split("/");
		 
		 
		 usuario.setDataNascimento(new Date(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0])));
		 
		 usuario.setBloqueado(false);
		 usuario.setMotivoBloqueio("Nenhum");
		 
		 System.out.println("Informe Categoria do Usuario: ");
		 categoriaUsuario.setDescricao(s.nextLine());
		 
		 usuario.setCategoriaUsuario(categoriaUsuario
		 .buscarCategoriaUsuario(categoriaUsuario));
		 if (usuario.incluirUsuario(usuario)) {
		 System.out.println("Usuário: " + usuario.getNome()
		 + " cadastrado com successo!");
		 } else {
		 System.out.println("Problema no cadastro do usuário!");
		 }
		
	}
	
	public static void cadastrarCategoriaUsuario(){
		Scanner s = new Scanner(System.in);	
		categoriaUsuario = new CategoriaUsuario();
		
		System.out.print("Descriçãao da Categoria: ");
		 categoriaUsuario.setDescricao(s.nextLine());
		 
		 System.out.print("Numero de exemplares: ");
		 categoriaUsuario.setNumeroExemplares(s.nextInt());
		 
		 //categoriaUsuario.setQuantidadeDiasPenalidade(3);
		 System.out.print("Tempo de Emprestimo: ");
		 categoriaUsuario.setTempoEmprestimo(s.nextInt());
		 
		 if (categoriaUsuario.incluirCategoriaUsuario(categoriaUsuario)) {
			 System.out.println("Categoria: " + categoriaUsuario.getDescricao()
			 + " cadastrada com successo!");
		 } else {
			 System.out.println("Problema no cadastro da categoria!");
		 }	
	}
	
	public static void cadastrarFuncionario(){
		Scanner s = new Scanner(System.in);	
		Funcionario func = new Funcionario();
		
		System.out.println("Nome: ");
		func.setNome(s.nextLine());
		 
		 System.out.println("CPF: ");
		 func.setCpf(s.nextLine());
		 
		 System.out.println("Login: ");
		 func.setLogin(s.nextLine());
		 
		 System.out.println("Password: ");
		 func.setSenha(s.next());
		 
		 func.cadastrarFuncionario();
	}
	
	public static void emprestimoExemplar(){
		Scanner s = new Scanner(System.in);	
		Usuario u = new Usuario();
		System.out.println("Codigo Registro do Usuário: ");
		long us = Long.parseLong(s.nextLine());
		u = u.buscarUsuario(us);
		 
		DaoExemplar DaoE = new DaoExemplar();
		Exemplar e = new Exemplar();
		System.out.println("Codigo do Recurso: ");
		long codigoRecurso = Long.parseLong(s.nextLine());
		
		System.out.println("Campus: ");
		String campus = s.nextLine();
		e = DaoE.buscarExemplar(codigoRecurso, campus);
		
		//mudar
		/*Funcionario f = new Funcionario();
		System.out.println("CPF do Funcionario: ");
		f.setCpf(s.nextLine());
		DaoFuncionario DaoF = new DaoFuncionario(f);
		
		DaoF.retornaFunc();*/
		
		
		// Realiza um novo emprestimo
		Emprestimo emprestimo = new Emprestimo();
		if (emprestimo.realizarEmprestimo(u, e, funcionario)) {
			emprestimo.emitirComprovanteEmprestimo();
		} else {
			System.out.println("Problema no cadastro do emprestimo!");
		}	
	}
	
	public static void devolucaoExemplar(){
		
		Scanner s = new Scanner(System.in);	
		Usuario u = new Usuario();
		System.out.println("Codigo Registro do Usuário: ");
		long us = Long.parseLong(s.nextLine());
		u = u.buscarUsuario(us);
		
		DaoExemplar DaoE = new DaoExemplar();
		Exemplar e = new Exemplar();
		System.out.println("Codigo do Recurso: ");
		long codigoRecurso = Long.parseLong(s.nextLine());
		
		System.out.println("Campus: ");
		String campus = s.nextLine();
		
		e = DaoE.buscarExemplar(codigoRecurso, campus);
		
		Emprestimo emprestimo = new Emprestimo();
		if (emprestimo.realizarDevolucao(u, e, funcionario)) {
			emprestimo.emitirComprovanteDevolucao();
		} else {
			System.out.println("Problema no cadastro da devolucao!");
		}
		
	}
}
