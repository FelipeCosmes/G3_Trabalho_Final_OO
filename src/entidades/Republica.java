package entidades;

import java.util.List;
import java.util.ArrayList;

public class Republica {
	
	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	private boolean divisaoProporcional;
	
	public Republica () {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		divisaoProporcional = false;
	}
	
	public List<Pessoa> getListaPessoas () {
		return listaPessoas;
	}
	
	public void cadastroPessoa (String nome, String email, float renda) {
		listaPessoas.add(new Pessoa(nome, email, renda, this));
	}
	
	public void excluirPessoa (Pessoa pessoa) {
		listaPessoas.remove(pessoa);
	}
	
	public List<Despesa> getListaDespesas () {
		return listaDespesas;
	}
	
	public void cadastroDespesa (String descricao, float valor, Categoria categoria) {
		listaDespesas.add(new Despesa(descricao, valor, categoria));
	}
	
	public void excluirDespesa (Despesa despesa) {
		listaDespesas.remove(despesa);
	}
	
	public boolean getDivisaoProporcional () {
		return divisaoProporcional;
	}
	
	public void setDivisaoProporcional (boolean divisaoProporcional) {
		this.divisaoProporcional = divisaoProporcional;
	}
	
	public float rendaTotal () {
		float total = 0;
		for (Pessoa pessoa: listaPessoas) {
			total += pessoa.getRenda();
		}
		return total;
	}
	
	public float despesaTotal () {
		float total = 0;
		for (Despesa despesa : listaDespesas) {
			total += despesa.getValor();
		}
		return total;
	}
}
