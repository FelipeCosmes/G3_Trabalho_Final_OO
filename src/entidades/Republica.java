package entidades;

import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	private boolean divisaoProporcional;

	public Republica() {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		divisaoProporcional = false;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void cadastroPessoa() {
		String nome = JOptionPane.showInputDialog("Nome: ");
		String email = JOptionPane.showInputDialog("Email: ");
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		float renda = Float.parseFloat(strRenda);
		listaPessoas.add(new Pessoa(nome, email, renda, this));
	}

	public void cadastroDespesa() {
		String descricao = JOptionPane.showInputDialog("Descrição: ");
		String strCat = JOptionPane.showInputDialog("Categoria: ");
		Categoria categoria = new Categoria(strCat);
		String strValor = JOptionPane.showInputDialog("Valor: ");
		float valor = Float.parseFloat(strValor);
		//String resp = JOptionPane.showInputDialog("Subcategoria: ");

		listaDespesas.add(new Despesa(descricao, valor, categoria));
	}

	public void excluirPessoa(Pessoa pessoa) {
		listaPessoas.remove(pessoa);
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void excluirDespesa(Despesa despesa) {
		listaDespesas.remove(despesa);
	}

	public boolean getDivisaoProporcional() {
		return divisaoProporcional;
	}

	public void setDivisaoProporcional(boolean divisaoProporcional) {
		this.divisaoProporcional = divisaoProporcional;
	}

	public float rendaTotal() {
		float total = 0;
		for (Pessoa pessoa : listaPessoas) {
			total += pessoa.getRenda();
		}
		return total;
	}

	public float despesaTotal() {
		float total = 0;
		for (Despesa despesa : listaDespesas) {
			total += despesa.getValor();
		}
		return total;
	}

}
