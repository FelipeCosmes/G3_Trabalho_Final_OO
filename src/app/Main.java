package app;

import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;
import exceptions.CategoriaNaoInformadaException;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.RendimentoInvalidoException;
import exceptions.ValorNaoInformadoException;

public class Main {
	
	public static final String menu = "Informe a opcao desejada: \n" 
						            + "1 - Cadastrar pessoa \n" 
						            + "2 - Cadastrar despesa \n"
						            + "3 - Excluir pessoa \n" 
						            + "4 - Excluir despesa \n" 
						            + "5 - Calcular Divida \n"
						            + "6 - Imprimir relat�rio \n"
						            + "0 - Sair";
	
    public static void main(String[] args) {
    	
    	String regra = UI.selecionarRegra();

        Republica republica = new Republica(regra);
        
        // republica.lerPessoas();
        // republica.lerDespesas();
        
        int opcao = 0;
        
        Pessoa p;
        Despesa d;
        
        do {
        	opcao = UI.showMenu();
        	
        	switch (opcao) {
            case 1:
            	try {
            		p = UI.criaPessoa();
            		republica.cadastroPessoa(p);
        		} catch (DadosPessoaisIncompletosException e) {
        			UI.alerta("Dados pessoais inv�lidos. Tente novamente!");
        		} catch (RendimentoInvalidoException e) {
        			UI.alerta("Rendimento inv�lido. Tente novamente!");
        		}
                republica.gravarPessoas();
                break;
                
            case 2:
            	try {
            		d = UI.criaDespesa();
            		republica.cadastroDespesa(d);
        		} catch (CategoriaNaoInformadaException e) {
        			UI.alerta("Categoria n�o informada. Tente novamente!");
        		} catch (DescricaoNaoInformadaException e) {
        			UI.alerta("Descri��o n�o informada. Tente novamente!");
        		} catch (ValorNaoInformadoException e) {
        			UI.alerta("Valor n�o informado. Tente novamente!");
        		}
                republica.gravarDespesas();
                break;
                
            case 3:
            	p = UI.selecionar(republica.getListPessoas(), "Excluir Pessoa");
            	if(p != null) republica.excluirPessoa(p);
                break;

            case 4:
            	d = UI.selecionar(republica.getListaDespesas(), "Excluir Despesa");
            	if(d != null) republica.excluirDespesa(d);
                break;

            case 5:
            	p = UI.selecionar(republica.getListPessoas(), "Calcular Divida");
            	float divida = republica.calculoDivida.divida(p);
            	UI.mensagem("A d�vida de " + p.getNome() + " � de R$ " + 
            			String.format("%.2f", divida) + "!");
                break;
                
            case 6:
            	String msg = "";
            	for(Pessoa pessoa : republica.getListPessoas()) {
            		msg += "Nome: " + pessoa.getNome()
            			 + ", renda: " + pessoa.getRenda()
            			 + ", d�vida: " + republica.calculoDivida.divida(pessoa)
            			 + ".\n";
            	}
            	if(republica.getListPessoas().size() == 0) {
            		UI.alerta("Lista vazia!", "Imprimir relat�rio");
            	} else {
            		UI.mensagem(msg);            		
            	}
                break;
                
            case 0:
                break;

            default:
                UI.alerta("Op��o Inv�lida!");
                break;
        	}
        } while (opcao != 0);
        
    }
}
