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
        do {
            String menu = "Informe a opcao desejada: \n"
                    + "1 - Cadastrar pessoa \n"
                    + "2 - Excluir pessoa \n"
                    + "3 - Calcular Divida \n"
                    + "4 - Cadastrar despesa \n"
                    + "5 - Excluir despesa \n"
                    + "0 - Sair";

            String strOpcao = JOptionPane.showInputDialog(menu);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    republica.cadastroPessoa();
                    break;

//                case 2:
//                    republica.cadastroDespesa();
//                    break;

                case 3:
                    String divida = Float.toString(pessoa.divida());
                    JOptionPane.showMessageDialog(null, divida);
                    break;

//                case 4:
//                    republica.excluirPessoa();
//                    break;
//
//                case 5:
//                    republica.excluirDespesa();

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
                    break;
            }
        } while (opcao != 0);


    }
}
