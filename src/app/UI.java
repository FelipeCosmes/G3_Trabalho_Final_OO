package app;

import java.util.List;

import javax.swing.JOptionPane;

import entidades.Categoria;
import entidades.Despesa;
import entidades.Pessoa;
import entidades.SubCategoria;

public class UI {

	// Metodo generico para selecionar um objeto de uma lista utilizando JOptionPane
	@SuppressWarnings("unchecked")
	public static <T> T selecionar(List<T> list, String titulo) {
		if (list.isEmpty()) {
			alerta("Lista vazia!", titulo);
			return null;
		}

		return (T) JOptionPane.showInputDialog(null, "Selecione a pessoa:", titulo, JOptionPane.INFORMATION_MESSAGE,
				null, list.toArray(), list.get(0));
	}

	// Metodo para selecionar a regra de calculo das dividas individuais
	public static String selecionarRegra() {
		String[] regra = { "Igualitaria", "Proporcional" };
		return (String) JOptionPane.showInputDialog(null, "Forma de divisao para as despesas:", "Divisao de despesa",
				JOptionPane.INFORMATION_MESSAGE, null, regra, regra[0]);
	}

	// Metodo para selecionar o mes
	public static String selecionarMes() {
		String[] opMes = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		return (String) JOptionPane.showInputDialog(null, "Escolha o mes:", "Data do cadastro",
				JOptionPane.INFORMATION_MESSAGE, null, opMes, opMes[0]);
	}

	// Metodo para selecionar o ano
	public static String selecionarAno() {
		String[] opAno = new String[131];
		for (int i = 1970; i <= 2100; i++) {
			opAno[i - 1970] = Integer.toString(i);
		}

		return (String) JOptionPane.showInputDialog(null, "Escolha o Ano:", "Data do cadastro",
				JOptionPane.INFORMATION_MESSAGE, null, opAno, opAno[0]);
	}

	// Metodo para adquirir os dados da pessoa cadastrada
	public static Pessoa criaPessoa() {

		// cadastro do nome
		String nome = JOptionPane.showInputDialog("Nome: ");
		if (nome == null) {
			nome = "";
		}

		// cadastro email
		String email = JOptionPane.showInputDialog("Email: ");
		if (email == null) {
			email = "";
		}

		// cadastro renda
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		float renda = Float.parseFloat(strRenda);

		return new Pessoa(nome, email, renda);
	}

	// Metodo para adquirir os dados da despesa cadastrada
	public static Despesa criaDespesa() {


		// cadastro descricao
		String descricao = JOptionPane.showInputDialog("Descricao: ");
		if (descricao == null) {
			descricao = "";
		}

		String strCat = JOptionPane.showInputDialog("Categoria: ");
		if (strCat == null) {
			strCat = "";
		}

		Categoria categoria = new Categoria(strCat);
		int sub = JOptionPane.showConfirmDialog(null, "Deseja cadastrar subcategoria?");
		if (sub == 0) {
			String strSubCat = JOptionPane.showInputDialog("Sub-categoria: ");

			categoria = new SubCategoria(strSubCat, categoria);
		}

		// cadastro do valor
		String strValor = JOptionPane.showInputDialog("Valor: ");
		float valor = Float.parseFloat(strValor);

		return new Despesa(descricao, categoria, valor);
	}

	// Metodo para mostrar o menu
	public static int showMenu() {
		int opcao = 0;

		String strOpcao = JOptionPane.showInputDialog(Main.menu);

		boolean parse;

		do {
			if (strOpcao == null || strOpcao.isEmpty()) {
				opcao = 0;
				parse = true;
			} else {
				try {
					opcao = Integer.parseInt(strOpcao);
					parse = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Opcao informada nao e um numero.\n" + "Em seguida informe a opcao novamente.");
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
	public static void mensagem(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	// Metodos para mostrar uma alerta
	public static void alerta(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ATENCAO!", JOptionPane.WARNING_MESSAGE);
	}

	public static void alerta(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}

}