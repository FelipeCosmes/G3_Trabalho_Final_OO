package app;

import java.util.List;

import javax.swing.JOptionPane;

import entidades.Categoria;
import entidades.Despesa;
import entidades.Pessoa;
import exceptions.*;

public class UI {
	
	// Metodo generico para selecionar um objeto de uma lista utilizando JOptionPane
	@SuppressWarnings("unchecked")
	public static <T> T selecionar(List<T> list, String titulo) {
		if (list.isEmpty()) {
			alerta("Lista vazia!", titulo);
			return null;
		}
		
		return (T) JOptionPane.showInputDialog(null, "Selecione a pessoa:", 
				titulo, JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(0));
	}
	
	// Metodo para selecionar a regra de calculo das dividas individuais
	public static String selecionarRegra() {
		String[] regra = {"Igualitaria", "Proporcional"};
        return (String) JOptionPane.showInputDialog(null, "Forma de divisao para as despesas:", 
        		"Divisao de despesa", JOptionPane.INFORMATION_MESSAGE, null, regra, regra[0]);
	}
	
	//Metodo para selecionar o m�s
	public static String selecionarMes() {
		String[] opMes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return (String) JOptionPane.showInputDialog(null, "Escolha o mes:", 
        		"Data do cadastro", JOptionPane.INFORMATION_MESSAGE, null, opMes, opMes[0]);
	}
	
	//Metodo para selecionar o ano
	public static String selecionarAno() {
		String[] opAno = new String [131];
		for (int i = 1970; i<=2100; i++) {
			opAno[i-1970] = Integer.toString(i);
		}
		
        return (String) JOptionPane.showInputDialog(null, "Escolha o Ano:", 
        		"Data do cadastro", JOptionPane.INFORMATION_MESSAGE, null, opAno, opAno[0]);
	}
	
	// Metodo para adquirir os dados da pessoa cadastrada
	public static Pessoa criaPessoa() {

		boolean parseNome;
		boolean parseRenda;
		boolean parseEmail;

		//cadastro do nome
		String nome = JOptionPane.showInputDialog("Nome: ");
		do {
			try{
				parseNome = true;
				if(nome.isBlank()|| nome.matches(".*\\d.*")){
					throw new DadosPessoaisIncompletosException();
				}
			}
			catch(DadosPessoaisIncompletosException e){
//				JOptionPane.showMessageDialog(null, "Valor de renda informado nao e um numero.\n"
//						+ "Em seguida informe a renda novamente.");
//				strRenda = JOptionPane.showInputDialog("Renda: ");
//				parseRenda = false;
				JOptionPane.showMessageDialog(null, "Digite apenas letras.\nNome nao pode ser nulo\n"+e);
				nome = JOptionPane.showInputDialog("Nome: ");
				parseNome = false;
			}
		} while (!parseNome);


		//cadastro email
		String email = JOptionPane.showInputDialog("Email: ");
		do {
			try{
				parseEmail = true;
				if(email.isBlank()){
					throw new DadosPessoaisIncompletosException();
				}
			}
			catch(DadosPessoaisIncompletosException e){
				JOptionPane.showMessageDialog(null, "Email nao pode ser nulo\n"+e);
				email = JOptionPane.showInputDialog("Email: ");
				parseEmail = false;
			}
		} while (!parseEmail);


		//cadastro renda
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		float renda = 0;
		do { 
			try {
				renda = Float.parseFloat(strRenda);
				parseRenda = true;
				if(renda<0){
					throw new RendimentoInvalidoException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor de renda informado nao e um numero.\n"
						+ "Em seguida informe a renda novamente.");
				strRenda = JOptionPane.showInputDialog("Renda: ");
				parseRenda = false;
			} catch (RendimentoInvalidoException e){
				JOptionPane.showMessageDialog(null, "valor do rendimento nao pode ser negativo\n"+e);
				strRenda = JOptionPane.showInputDialog("Renda: ");
				parseRenda = false;
			}

		} while (!parseRenda);
		
		return new Pessoa(nome, email, renda);
	}
	
	// Metodo para adquirir os dados da despesa cadastrada
	public static Despesa criaDespesa() {

		boolean parseCategoria;
		boolean parseDescricao;
		boolean parseValue;

		//cadastro descricao
		String descricao = JOptionPane.showInputDialog("Descricao: ");
		do {
			try{
				parseDescricao = true;
				if(descricao.isBlank()|| descricao.matches(".*\\d.*")){
					throw new DescricaoNaoInformadaException();
				}
			}
			catch(DescricaoNaoInformadaException e){
				JOptionPane.showMessageDialog(null, "Descricao contem somente letras e nao deve ser nula!!\n"+e);
				descricao = JOptionPane.showInputDialog("Descricao: ");
				parseDescricao = false;
			}
		} while (!parseDescricao);

		String strCat = JOptionPane.showInputDialog("Categoria: ");		do {
			try{
				parseCategoria = true;
				if(strCat.isBlank()|| strCat.matches(".*\\d.*")){
					throw new CategoriaNaoInformadaException();
				}
			}
			catch(CategoriaNaoInformadaException e){
				JOptionPane.showMessageDialog(null, "Categoria contem somente letras e nao deve ser nula!!\n"+e);
				strCat = JOptionPane.showInputDialog("Categoria: ");
				parseCategoria = false;
			}
		} while (!parseCategoria);
		Categoria categoria = new Categoria(strCat);


		//cadastro do valor
		String strValor = JOptionPane.showInputDialog("Valor: ");
		float valor = 0;
		do { 
			try {
				valor = Float.parseFloat(strValor);
				parseValue = true;
				if(valor<0){
					throw new ValorNaoInformadoException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "valor nao pode se nulo e deve ser um numero positivo.\n"
						+ "Em seguida informe o valor novamente.\n"+e);
				strValor = JOptionPane.showInputDialog("Valor: ");
				parseValue = false;
			}
			catch(ValorNaoInformadoException e){
				JOptionPane.showMessageDialog(null, "valor nao pode se negativo\n"+ e);
				strValor = JOptionPane.showInputDialog("Valor: ");
				parseValue = false;

			}
		} while (!parseValue);


		
		return new Despesa(descricao, categoria, valor);
	}
	
	// Metodo para mostrar o menu
	public static int showMenu() {
		int opcao = 0;

        String strOpcao = JOptionPane.showInputDialog(Main.menu);

		boolean parse;
		
		do { 
    		if(strOpcao == null||strOpcao.isEmpty()) {
				opcao = 0;
				parse = true;
			} else {
    			try {
    				opcao = Integer.parseInt(strOpcao);
    				parse = true;
    			} catch (NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "Opcao informada nao e um numero.\n"
    						+ "Em seguida informe a opcao novamente.");
    				strOpcao = JOptionPane.showInputDialog(Main.menu);
    				parse = false;
    			}
			}	
		} while (!parse);
		
		return opcao;
	}
	
	// Metodo para receber uma String do usuario
	public static String getString(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	// Metodo para mostrar uma mensagem
	public static void mensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	// Metodos para mostrar uma alerta	
	public static void alerta(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ATENCAO!", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void alerta(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}

}