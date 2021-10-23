package entidades;

import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	//private CalculoDivida calculoDivida;
	private String nomeArquivoPessoa = "alunos.txt";
	private String strMes;
	private String strAno;
	

	public Republica() {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		/* calculoDivida.rendaTotal(listaPessoas);
		calculoDivida.despesaTotal(listaDespesas); */
	}

	//-----Cadastro de Data

	public String data(){
		
		setStrMes(JOptionPane.showInputDialog("Mês do cadastro: "));
		setStrAno(JOptionPane.showInputDialog("Ano do cadastro: "));
		
		return "despesas_" + strMes + "_" + strAno + ".txt";
    }

	public String getData(){
		return "despesas_" + strMes + "_" + strAno + ".txt";
	}

	//-----Leitura, Cadastro e Gravação de Pessoas
	
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

	public void cadastroPessoa() {
		String nome = JOptionPane.showInputDialog("Nome: ");
		String email = JOptionPane.showInputDialog("Email: ");
		String strRenda = JOptionPane.showInputDialog("Renda: ");
		float renda = Float.parseFloat(strRenda);
		listaPessoas.add(new Pessoa(nome, email, renda));
	}
	
	public List<Pessoa> getListPessoas() {
		return listaPessoas;
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

	public void excluirPessoa(String nome, String email, float renda) {
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

		// ----- Excluir de alunos.txt

		File fil = new File("alunos.txt");

		try {
			FileReader fr = new FileReader(fil);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<>();

			while (linha != null) {
				if (linha.equals(nome + " ; " + email + " ; " + renda) == false) {
					salvar.add(linha);
				}

				linha = br.readLine();
			}

			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(fil, true);
			fw2.close();

			FileWriter fw = new FileWriter(fil);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e) {
			//TODO: handle exception
		}
	}
	
	//-----Leitura, Cadastro, Gravação e Exclusão de Despesas
	
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
					Despesa a = new Despesa();
					a = new Despesa(registro[0], new Categoria(registro[1]), Float.parseFloat(registro[2]));
					listaDespesas.add(a);
				}
			} while (linha != null);
			
			JOptionPane.showMessageDialog(null, "Registros de Despesa carregados do arquivo");
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

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}
	
	public void gravarDespesas() {
		BufferedWriter buffer = null;
		FileWriter out = null;
		
		try {
			out = new FileWriter(getData());
			buffer = new BufferedWriter(out);
			
			for(Despesa a : listaDespesas) {
				buffer.write(a.toString());
				buffer.write('\n');
			}
			
			buffer.close();
		} catch (IOException e) {
			
		}
	}


	public void excluirDespesa(String descricao, String strCategoria, float valor) {
		Categoria categoria = new Categoria(strCategoria);
		Despesa desp = null;
		for (Despesa p : listaDespesas) {
			if(p.getDescricao().equalsIgnoreCase(descricao)) {
				desp = p;
				JOptionPane.showMessageDialog(null, "Cadastro de " + desp.getDescricao() + " removido");
				break;
			}
		}
		if(desp == null){
			JOptionPane.showMessageDialog(null, "Cadastro nao encontrado!");
		}
		listaDespesas.remove(desp);

		// ----- Excluir de despesas_<Mes>_<Ano>.txt

		File fil = new File("despesas_" + strMes + "_" + strAno + ".txt");

		try {
			FileReader fr = new FileReader(fil);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<>();

			while (linha != null) {
				if (linha.equals(descricao + " ; " + categoria.getNome() + " ; " + valor) == false) {
					salvar.add(linha);
				}

				linha = br.readLine();
			}

			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(fil, true);
			fw2.close();

			FileWriter fw = new FileWriter(fil);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e) {
			//TODO: handle exception
		}
	}

	//-------------------------------------------------------
	
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

	public String getStrAno() {
		return strAno;
	}
	
	public void setStrAno(String strAno) {
		this.strAno = strAno;
	}
	
	public String getStrMes() {
		return strMes;
	}
	
	public void setStrMes(String strMes) {
		this.strMes = strMes;
	}
}

