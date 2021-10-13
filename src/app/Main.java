package app;

import javax.swing.JOptionPane;

import entidades.Categoria;
import entidades.SubCategoria;
import entidades.Despesa;
import entidades.Pessoa;

public class Main {
	
	static Pessoa pessoas[] = new Pessoa[0];
	static Despesa despesas[] = new Despesa[0];
	static Categoria categorias[] = new Categoria[0];
	
	public static void cadastroPessoa() {
		String nome = JOptionPane.showInputDialog("Informe o nome da pessoa.");
		String email = JOptionPane.showInputDialog("Informe o email da pessoa");
		String strValor = JOptionPane.showInputDialog("Informe a renda da pessoa, em R$");
		float valor = Float.parseFloat(strValor);
		
		//criar uma pessoa
		Pessoa clone = new Pessoa(nome,email, valor);

		//Armazenar pessoa em clone
		// Copiar pessoas para vetor clone
		Pessoa[] clonePessoas = new Pessoa[pessoas.length + 1];
		for (int i=0; i<pessoas.length; i++)
			clonePessoas[i] = pessoas[i];
		// Armazenar clone em clonePessoas
		clonePessoas[clonePessoas.length-1] = clone;
		//Atualizar a referencia pessoas
		pessoas = clonePessoas;

	}
	
	
	public static void main(String[] args) {

		int opcao = 0;

		do {
			String menu = "Informe a opção desejada: \n"
					+ "1 - Cadastrar pessoa \n"
					+ "2 - Cadastrar despesa  \n"
					+ "0 - Sair";

			String strOpcao = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
			case 1: 
				cadastroPessoa();
				break;


			} 
		}
		while (opcao != 0);

		return;
	}
	

}
