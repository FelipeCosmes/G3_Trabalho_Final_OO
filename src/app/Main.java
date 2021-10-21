package app;

import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        Despesa despesa = new Despesa();
        Republica republica = new Republica();

        int opcao = 0;
        int dividaProp;
        Data();
        do {
            String menu = "Informe a opcao desejada: \n" 
                        + "1 - Cadastrar pessoa \n" 
                        + "2 - Cadastrar despesa \n"
                        + "3 - Excluir pessoa \n" 
                        + "4 - Excluir despesa \n" 
                        + "5 - Calcular Divida \n"
                        + "0 - Sair";

            String strOpcao = JOptionPane.showInputDialog(menu);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    republica.cadastroPessoa();
                    break;

                case 2:
                    republica.cadastroDespesa();
                    break;

                case 3:
                    String nome = JOptionPane.showInputDialog(null,"Nome do cadastro que deseja remover: ");
                    republica.excluirPessoa(nome);
                    break;

                case 4:
                    String nomeDespesa = JOptionPane.showInputDialog(null,"Despesa do cadastro que deseja remover: ");
                    republica.excluirDespesa(nomeDespesa);
                    break;

                case 5:
                    String strDividaProp = JOptionPane.showInputDialog(null, "0 se quiser proporcional e 1 se quiser comunismo:");
                    dividaProp = Integer.parseInt(strDividaProp);
                    if(dividaProp == 0){
                        republica.setDivisaoProporcional(false);
                    }
                    else {
                        republica.setDivisaoProporcional(true);
                    }
                    String divida = Float.toString(pessoa.divida());
                    JOptionPane.showMessageDialog(null, divida);
                    break;
                    
                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while (opcao != 0);

    }

    public static void Data(){
        String strMes = JOptionPane.showInputDialog("Mês do cadastro: ");
        String strAno = JOptionPane.showInputDialog("Ano do cadastro: ");
    }
}
