package entidades;

import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	//private CalculoDivida calculoDivida;
	private String nomeArquivoPessoa = "alunos.txt";
	

	public Republica() {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		/* calculoDivida.rendaTotal(listaPessoas);
		calculoDivida.despesaTotal(listaDespesas); */
	}

	public String data(){

		String strMes = JOptionPane.showInputDialog("Mês do cadastro: ");
		String strAno = JOptionPane.showInputDialog("Ano do cadastro: ");

		return "despesas_" + strMes + "_" + strAno + ".txt";
    }

	public List<Pessoa> getListPessoas() {
		return listaPessoas;
	}

	public void cadastroPessoa() {
		String nome = JOptionPane.showInputDialog("Nome: ");
		String email = JOptionPane.showInputDialog("Email: ");
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		float renda = Float.parseFloat(strRenda);
		listaPessoas.add(new Pessoa(nome, email, renda));
	}

	public void gravarPessoas() {
		BufferedWriter buffer = null;
		FileWriter out = null;

		try {
			out = new FileWriter(nomeArquivoPessoa);
			buffer = new BufferedWriter(out);

			for(Pessoa a : listaPessoas) {
				buffer.write(a.toString());
				buffer.write('\n');
			}

			buffer.close();
		} catch (IOException e) {

		}
	}

	public void lerPessoas() {
		FileReader in = null;
		BufferedReader buffer = null;

		try {
			in = new FileReader(nomeArquivoPessoa);
			buffer = new BufferedReader(in);

			String linha = null;
			do {
				linha = buffer.readLine();

				if (linha != null) {
					String[] registro = linha.split(" ; ");
					Pessoa a = new Pessoa(registro[0], registro[1], Float.parseFloat(registro[2]));
					listaPessoas.add(a);
				}
			} while (linha != null);

			JOptionPane.showMessageDialog(null, "Registros de Pessoa carregados do arquivo");
			buffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadastroDespesa() {
		String descricao = JOptionPane.showInputDialog("Descricao: ");
		String strCat = JOptionPane.showInputDialog("Categoria: ");
		Categoria categoria = new Categoria(strCat);
		String strValor = JOptionPane.showInputDialog("Valor: ");
		float valor = Float.parseFloat(strValor);
		//String resp = JOptionPane.showInputDialog("Subcategoria: ");

		listaDespesas.add(new Despesa(descricao, categoria, valor));
	}

	public void gravarDespesas() {
		BufferedWriter buffer = null;
		FileWriter out = null;

		try {
			out = new FileWriter(data());
			buffer = new BufferedWriter(out);

			for(Despesa a : listaDespesas) {
				buffer.write(a.toString());
				buffer.write('\n');
			}

			buffer.close();
		} catch (IOException e) {

		}
	}

	public void lerDespesas() {
		FileReader in = null;
		BufferedReader buffer = null;

		try {
			in = new FileReader(data());
			buffer = new BufferedReader(in);

			String linha = null;
			do {
				linha = buffer.readLine();

				if (linha != null) {
					String[] registro = linha.split(" ; ");
					Despesa a = new Despesa(registro[0], new Categoria(registro[1]), Float.parseFloat(registro[2]));
					listaDespesas.add(a);
				}
			} while (linha != null);

			JOptionPane.showMessageDialog(null, "Registros de Despesa carregados do arquivo");
			buffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excluirPessoa(String nome) {
		Pessoa resp = null;
		for(Pessoa p : listaPessoas){
			if (p.getNome().equalsIgnoreCase(nome)){
				resp = p;
				JOptionPane.showMessageDialog(null, "Cadastro de " + resp.getNome() + " removido");
				break;
			}
		}
		if(resp==null){
			JOptionPane.showMessageDialog(null, "Cadastro nao encontrado!");
		}
		listaPessoas.remove(resp);
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void excluirDespesa(String despesa) {
		Despesa desp = null;
		for (Despesa p : listaDespesas) {
			if(p.getDescricao().equalsIgnoreCase(despesa)) {
				desp = p;
				JOptionPane.showMessageDialog(null, "Cadastro de " + desp.getDescricao() + " removido");
				break;
			}
		}
			if(desp == null){
				JOptionPane.showMessageDialog(null, "Cadastro nao encontrado!");
		}
		listaDespesas.remove(desp);
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

