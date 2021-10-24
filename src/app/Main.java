package app;

import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;
import servicos.RegraIgualitaria;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Republica republica = new Republica();
        RegraIgualitaria regraIgualitaria = new RegraIgualitaria();

        int opcao = 0;
        republica.lerPessoas();
        republica.lerDespesas();
        
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
                        republica.gravarPessoas();
                        break;
    
                    case 2:
                        
                        republica.cadastroDespesa();
                        republica.gravarDespesas();
                        break;
    
                    case 3:
                        int i=0;
                        String[] a = new String[republica.getListPessoas().size()];
                        for(Pessoa pessoa : republica.getListPessoas()){
                            a[i]=pessoa.getNome();
                            i++;
                        }
                        Object[] nomes = a;
                        Object nomeDel = JOptionPane.showInputDialog(null, "Pessoa a ser excluida:", "Exclusao de pessoa", JOptionPane.INFORMATION_MESSAGE,null,nomes,nomes[0]);
                        String strNome = (String) nomeDel;
                        republica.excluirPessoa(strNome);
                        break;
    
                    case 4:
                        int j=0;
                        String[] b = new String[republica.getListaDespesas().size()];
                        for(Despesa despesa : republica.getListaDespesas()){
                            b[j]=despesa.getDescricao();
                            j++;
                        }
                        Object[] nomeDesp = b;
                        Object despDel = JOptionPane.showInputDialog(null, "Despesa a ser excluida:", "Exclusao de Despesa", JOptionPane.INFORMATION_MESSAGE,null,nomeDesp,nomeDesp[0]);
                        String strDesp = (String) despDel;
                        /* String descricao = JOptionPane.showInputDialog(null,"Despesa do cadastro que deseja remover: ");
                        String strCategoria = JOptionPane.showInputDialog(null,"Categoria da despesa que deseja remover: ");
                        String strValor = JOptionPane.showInputDialog(null,"Valor da despesa que deseja remover: ");
                        float valor = Float.parseFloat(strValor); */
                        republica.excluirDespesa(strDesp);
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
