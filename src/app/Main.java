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
        Data();
        
        try {
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
                    Object[] divisao = {"Igualitária","Proporcional"};
                    Object tipoDiv= JOptionPane.showInputDialog(null, "Forma de divisão para as despesas:", "Divisão de despesa", JOptionPane.INFORMATION_MESSAGE,null,divisao,divisao[0]);
                    republica.calcularDiv(tipoDiv);
                    
                        break;
                        
                    case 0:
                        break;
    
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            opcao = 0;
        }
        

    }

    public static void Data(){
        String strMes = JOptionPane.showInputDialog("Mês do cadastro: ");
        String strAno = JOptionPane.showInputDialog("Ano do cadastro: ");
    }
}
