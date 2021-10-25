package app;

import java.util.List;

import javax.swing.JOptionPane;

import entidades.Categoria;
import entidades.Despesa;
import entidades.Pessoa;

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
		String[] regra = {"Igualit�ria", "Proporcional"};
        return (String) JOptionPane.showInputDialog(null, "Forma de divis�o para as despesas:", 
        		"Divis�o de despesa", JOptionPane.INFORMATION_MESSAGE, null, regra, regra[0]);
	}
	
	//Metodo para selecionar o m�s
	public static String selecionarMes() {
		String[] opMes = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        return (String) JOptionPane.showInputDialog(null, "Escolha o m�s:", 
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
		
		String nome = JOptionPane.showInputDialog("Nome: ");
		String email = JOptionPane.showInputDialog("Email: ");
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		
		float renda = 0;
		boolean parse;
		
		do { 
			try {
				renda = Float.parseFloat(strRenda);
				parse = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor de renda informado n�o � um n�mero.\n"
						+ "Em seguida informe a renda novamente.");
				strRenda = JOptionPane.showInputDialog("Renda: ");
				parse = false;
			}
		} while (!parse);
		
		return new Pessoa(nome, email, renda);
	}
	
	// Metodo para adquirir os dados da despesa cadastrada
	public static Despesa criaDespesa() {
		
		String descricao = JOptionPane.showInputDialog("Descricao: ");
		String strCat = JOptionPane.showInputDialog("Categoria: ");
		String strValor = JOptionPane.showInputDialog("Valor: ");
		
		float valor = 0;
		boolean parse;
		
		do { 
			try {
				valor = Float.parseFloat(strValor);
				parse = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor da despesa informado n�o � um n�mero.\n"
						+ "Em seguida informe o valor novamente.");
				strValor = JOptionPane.showInputDialog("Valor: ");
				parse = false;
			}
		} while (!parse);
		
		Categoria categoria = new Categoria(strCat);
		
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
    				JOptionPane.showMessageDialog(null, "Op��o informada n�o � um n�mero.\n"
    						+ "Em seguida informe a op��o novamente.");
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
		JOptionPane.showMessageDialog(null, msg, "ATEN��O!", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void alerta(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}

}