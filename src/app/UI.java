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
		String[] regra = {"Igualitária", "Proporcional"};
        return (String) JOptionPane.showInputDialog(null, "Forma de divisão para as despesas:", 
        		"Divisão de despesa", JOptionPane.INFORMATION_MESSAGE, null, regra, regra[0]);
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
				JOptionPane.showMessageDialog(null, "Valor de renda informado não é um número.\n"
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
				JOptionPane.showMessageDialog(null, "Valor da despesa informado não é um número.\n"
						+ "Em seguida informe o valor novamente.");
				strValor = JOptionPane.showInputDialog("Valor: ");
				parse = false;
			}
		} while (!parse);
		
		Categoria categoria = new Categoria(strCat);
		
		return new Despesa(descricao, categoria, valor);
	}
	
	public static void excluirDespesa(Despesa d) {
		// TODO
	}
	
	// Metodo para mostrar o menu
	public static int showMenu() {
		int opcao = 0;

        String strOpcao = JOptionPane.showInputDialog(Main.menu);

		boolean parse;
		
		do { 
			try {
				opcao = Integer.parseInt(strOpcao);
				parse = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção informada não é um número.\n"
						+ "Em seguida informe a opção novamente.");
				strOpcao = JOptionPane.showInputDialog(Main.menu);
				parse = false;
			}
		} while (!parse);
		
		return opcao;
	}
	
	// Metodo para mostrar uma mensagem
	public static void mensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	// Metodos para mostrar uma alerta	
	public static void alerta(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void alerta(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}

}
