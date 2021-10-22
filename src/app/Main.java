package app;

import entidades.RegraIgualitaria;
import entidades.Republica;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Republica republica = new Republica();
        RegraIgualitaria regraIgualitaria = new RegraIgualitaria();

        int opcao = 0;
        
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
                        republica.lerPessoas();
                        republica.cadastroPessoa();
                        republica.gravarPessoas();
                        break;
    
                    case 2:
                        republica.lerDespesas();
                        republica.cadastroDespesa();
                        republica.gravarDespesas();
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
                    /* Object[] divisao = {"Igualitária","Proporcional"};
                    Object tipoDiv= JOptionPane.showInputDialog(null, "Forma de divisão para as despesas:", "Divisão de despesa", JOptionPane.INFORMATION_MESSAGE,null,divisao,divisao[0]);
                    JOptionPane.showMessageDialog(null, regraIgualitaria.divIgual()); */
                    
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

}
