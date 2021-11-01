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

	public static final String menu = "Informe a opcao desejada: \n" + "1 - Cadastrar pessoa \n"
			+ "2 - Cadastrar despesa \n" + "3 - Excluir pessoa \n" + "4 - Excluir despesa \n" + "5 - Calcular Divida \n"
			+ "6 - Imprimir relatorio \n" + "7 - Mudar regra de calculo \n" + "0 - Sair";

	public static void main(String[] args) {

		String strMes = UI.selecionarMes();
		String strAno = UI.selecionarAno();

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
			if (strAno == null || strMes == null || regra == null) {
				break;
			}

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
				} catch (NumberFormatException e) {
					UI.alerta("Valor da renda nao informado corretamente. Tente novamente!");
				}
				break;

			case 2:
				try {
					d = UI.criaDespesa();
					republica.cadastroDespesa(d);
				} catch (CategoriaNaoInformadaException e) {
					UI.alerta("Categoria ou Subcategoria nao informada corretamente. Tente novamente!");
				} catch (DescricaoNaoInformadaException e) {
					UI.alerta("Descricao nao informada corretamente. Tente novamente!");
				} catch (ValorNaoInformadoException e) {
					UI.alerta("Valor da despesa nao informado corretamente. Tente novamente!");
				} catch (NumberFormatException e) {
					UI.alerta("Valor da despesa informado nao e um numero. Tente novamente!");
				}
				break;

			case 3:
				p = UI.selecionar(republica.getListPessoas(), "Excluir Pessoa");
				if (p != null)
					republica.excluirPessoa(p);
				break;

			case 4:
				d = UI.selecionar(republica.getListaDespesas(), "Excluir Despesa");
				if (d != null)
					republica.excluirDespesa(d);
				break;

			case 5:
				p = UI.selecionar(republica.getListPessoas(), "Calcular Divida");
				float divida = republica.calculoDivida.divida(p);
				UI.mensagem(p.getNome() + " deve R$ " + String.format("%.2f", divida), "Divisao " + regra + " | " + strMes+"/"+strAno);
				break;

			case 6:
				String msg = "";
				for (Pessoa pessoa : republica.getListPessoas()) {
					msg += String.format("\nNome: %s\nRenda: R$ %.2f\nDivida: R$ %.2f\n", pessoa.getNome(),
							pessoa.getRenda(), republica.calculoDivida.divida(pessoa));

				}
				msg += String.format("\nDespesa total: R$ %.2f", republica.calculoDivida.despesaTotal());
				if (republica.getListPessoas().size() == 0) {
					UI.alerta("Lista vazia!", "Imprimir relatario");
				} else {
					UI.mensagem(msg, "Divisao " + regra + " | " + strMes+"/"+strAno);
				}
				break;

			case 7:
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

		republica.gravarPessoas();
		republica.gravarDespesas();

	}
}
