package servicos;

import entidades.Despesa;
import entidades.Pessoa;
import entidades.Republica;

public abstract class CalculoDivida {
	
	protected Republica republica;
	
	public CalculoDivida(Republica republica) {
		this.republica = republica;
	}

    public float rendaTotal() {
		float total = 0;
		for (Pessoa pessoa : republica.getListPessoas()) {
			total += pessoa.getRenda();
		}
		return total;
	}

    public float despesaTotal() {
		float total = 0;
		for (Despesa despesa : republica.getListaDespesas()) {
			total += despesa.getValor();
		}
		return total;
	}

	public abstract float divida(Pessoa p);

}