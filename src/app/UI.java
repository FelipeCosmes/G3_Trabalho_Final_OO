package app;

import javax.swing.JOptionPane;

import entidades.Categoria;
import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;
import exceptions.CategoriaNaoInformadaException;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.RendimentoInvalidoException;
import exceptions.ValorNaoInformadoException;

public class UI {
	
	private static void cadastroPessoa(Republica republica) {

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
		
		try {
			republica.cadastroPessoa(nome, email, renda);
		} catch (DadosPessoaisIncompletosException e) {
			JOptionPane.showMessageDialog(null, "Dados pessoais inválidos. Tente novamente!");
		} catch (RendimentoInvalidoException e) {
			JOptionPane.showMessageDialog(null, "Rendimento inválido. Tente novamente!");
		}
	}
	
	private static void cadastroDespesa(Republica republica) {
		
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

		try {
			republica.cadastroDespesa(descricao, categoria, valor);
		} catch (CategoriaNaoInformadaException e) {
			JOptionPane.showMessageDialog(null, "Categoria não informada. Tente novamente!");
		} catch (DescricaoNaoInformadaException e) {
			JOptionPane.showMessageDialog(null, "Descrição não informada. Tente novamente!");
		} catch (ValorNaoInformadoException e) {
			JOptionPane.showMessageDialog(null, "Valor não informado. Tente novamente!");
		}
	}
	
	private static void excluirPessoa(Republica republica, Pessoa p) {
		// TODO
	}
	
	private static void excluirDespesa(Republica republica, Despesa d) {
		// TODO
	}
	
	public static void menu(Republica republica) {
		int opcao = 0;

        do {
            String menu = "Informe a opcao desejada: \n" 
                        + "1 - Cadastrar pessoa \n" 
                        + "2 - Cadastrar despesa \n"
                        + "3 - Excluir pessoa \n" 
                        + "4 - Excluir despesa \n" 
                        + "5 - Calcular Divida \n"
                        + "0 - Sair";

            String strOpcao = JOptionPane.showInputDialog(menu);

    		boolean parse;
    		
    		do { 
    			try {
    				opcao = Integer.parseInt(strOpcao);
    				parse = true;
    			} catch (NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "Opção informada não é um número.\n"
    						+ "Em seguida informe a opção novamente.");
    				strOpcao = JOptionPane.showInputDialog(menu);
    				parse = false;
    			}
    		} while (!parse);

            switch (opcao) {
                case 1:
                    cadastroPessoa(republica);
                    republica.gravarPessoas();
                    break;
                    
                case 2:
                    cadastroDespesa(republica);
                    republica.gravarDespesas();
                    break;
                    
                case 3:
                	// TODO
                	excluirPessoa(republica, null);
                    break;

                case 4:
                	// TODO
                	excluirDespesa(republica, null);
                    break;

                case 5:
                	// TODO
                    break;
                    
                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while (opcao != 0);
	}
}
