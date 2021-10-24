package entidades;

import java.util.List;

public abstract class CalculoDivida {

    public float rendaTotal(List<Pessoa> listaPessoas) {
		float total = 0;
		for (Pessoa pessoa : listaPessoas) {
			total += pessoa.getRenda();
		}
		return total;
	}

    public float despesaTotal(List<Despesa> listaDespesas) {
		float total = 0;
		for (Despesa despesa : listaDespesas) {
			total += despesa.getValor();
		}
		return total;
	}

    public void calcularDiv(Object tipo){
    	

	}

}