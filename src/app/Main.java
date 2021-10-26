package app;

import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;
import exceptions.CategoriaNaoInformadaException;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.RendimentoInvalidoException;
import exceptions.ValorNaoInformadoException;
import servicos.CalculoDivida;
import servicos.RegraIgualitaria;
import servicos.RegraProporcional;

public class Main {
	
	public static final String menu = "Informe a opcao desejada: \n" 
						            + "1 - Cadastrar pessoa \n" 
						            + "2 - Cadastrar despesa \n"
						            + "3 - Excluir pessoa \n" 
						            + "4 - Excluir despesa \n" 
						            + "5 - Calcular Divida \n"
						            + "6 - Imprimir relatorio \n"
						            + "9 - Mudar regra de calculo \n"
						            + "0 - Sair";
	
    public static void main(String[] args) {
    	
    	
    	
    	String strMes = UI.selecionarMes();
    	String strAno = UI.selecionarAno();
    	/* boolean parse = true;
    	 * String strMes = UI.getString("Informe o m�s do cadastro: ");	
		
    	do{
    		
    		
        	if(strMes != null && !strMes.isEmpty()) {
        		
        		try {
        			
        			float valorMes = Float.parseFloat(strMes);
                	while (12< valorMes || valorMes <= 0 ) {
                		strMes = UI.getString("M�s invalido. Informe novamente o m�s do cadastro: ");
    	            	if(strMes != null && !strMes.isEmpty()) {
    	            		valorMes = Float.parseFloat(strMes);
    	            	} else {
    	            		break;
    	            	}

                	}
                	
		            if(strMes != null && !strMes.isEmpty()) {	
		        		try{
		    		        Integer.parseInt(strMes);
		    		        parse = true;
		    		    } catch (NumberFormatException ex)
		    		    {
		    		    	UI.alerta("M�s inv�lido.");
		    		    	strMes = UI.getString("Informe novamente o m�s do cadastro: ");
		    		    	parse = false;
		    		    }
		            }
        		} catch (NumberFormatException e) {
        			UI.alerta("Por favor, escreva um n�mero");
        			strMes = UI.getString("Informe novamente o m�s do cadastro: ");
    		    	parse = false;
        		}
	            
    		}
    	}while(!parse);
		    
    	
    	
    	
    	String strAno = UI.getString("Informe o ano do cadastro: ");
    	
    	do {
	    	if(strAno != null && !strAno.isEmpty()) {
	    		try {
		    		float valorAno = Float.parseFloat(strAno);
		        	
		        	while(1970> valorAno){
		        		strAno = UI.getString("Ano invalido. Informe novamente o ano do cadastro: ");
		        		if(strAno != null && !strAno.isEmpty()) {
		        			valorAno = Float.parseFloat(strAno);
		            	} else {
		            		break;
		            	}
		        	}
		        	if(strAno != null && !strAno.isEmpty()) {
		        		try{
		    		        Integer.parseInt(strAno);
		    		        parse = true;
		    		    } catch (NumberFormatException ex)
		    		    {
		    		    	UI.alerta("Ano inv�lido.");
		    		    	strAno = UI.getString("Informe novamente o ano do cadastro: ");
		    		    	parse = false;
		    		    }
		        	}
	    		} catch (NumberFormatException ex) {
	    			UI.alerta("Por favor, escreva um n�mero");
        			strAno = UI.getString("Informe novamente o ano do cadastro: ");
    		    	parse = false;
	    		}

	    	}
    	}while(!parse);
    	*/
    	
    	
    	Republica republica = new Republica(strMes, strAno);
    	
    	CalculoDivida igualitaria = new RegraIgualitaria(republica);
    	CalculoDivida proporcional = new RegraProporcional(republica);
    	
    	String regra = UI.selecionarRegra();
    	
    	if (regra == "Igualitaria") {
    		republica.calculoDivida = igualitaria;			
		} else if (regra == "Proporcional") {
			republica.calculoDivida = proporcional;
		}
    	
        republica.lerPessoas();
        republica.lerDespesas();
        
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
        			UI.alerta("Dados pessoais invalidos. Tente novamente!");
        		} catch (RendimentoInvalidoException e) {
        			UI.alerta("Rendimento invalido. Tente novamente!");
        		}
                republica.gravarPessoas();
                break;
                
            case 2:
            	try {
            		d = UI.criaDespesa();
            		republica.cadastroDespesa(d);
        		} catch (CategoriaNaoInformadaException e) {
        			UI.alerta("Categoria nao informada. Tente novamente!");
        		} catch (DescricaoNaoInformadaException e) {
        			UI.alerta("Descricao nao informada. Tente novamente!");
        		} catch (ValorNaoInformadoException e) {
        			UI.alerta("Valor nao informado. Tente novamente!");
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
            	UI.mensagem("A divida de " + p.getNome() + " e de R$ " + 
            			String.format("%.2f", divida));
                break;
                
            case 6:
            	String msg = "";
            	for(Pessoa pessoa : republica.getListPessoas()) {
            		msg += "Nome: " + pessoa.getNome()
            			 + ", renda: " + pessoa.getRenda()
            			 + ", divida: " + republica.calculoDivida.divida(pessoa)
            			 + ".\n";
            	}
            	if(republica.getListPessoas().size() == 0) {
            		UI.alerta("Lista vazia!", "Imprimir relatario");
            	} else {
            		UI.mensagem(msg);            		
            	}
                break;
                
            case 9:
            	regra = UI.selecionarRegra();
            	
            	if (regra == "Igualitaria") {
            		republica.calculoDivida = igualitaria;			
        		} else if (regra == "Proporcional") {
        			republica.calculoDivida = proporcional;
        		}
            	break;
                
            case 0:
                break;

            default:
                UI.alerta("Opcao Invalida!");
                break;
        	}
        } while (opcao != 0);
        
    }
}