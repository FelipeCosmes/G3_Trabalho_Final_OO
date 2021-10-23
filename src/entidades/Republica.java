package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.CategoriaNaoInformadaException;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.RendimentoInvalidoException;
import exceptions.ValorNaoInformadoException;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	// private CalculoDivida calculoDivida;
	private String nomeArquivoPessoa = "alunos.txt";
	private String strMes;
	private String strAno;

	public Republica() {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		/*
		 * calculoDivida.rendaTotal(listaPessoas);
		 * calculoDivida.despesaTotal(listaDespesas);
		 */
	}

	// -----Cadastro de Data

	public String data() {

		setStrMes(JOptionPane.showInputDialog("Mês do cadastro: "));
		setStrAno(JOptionPane.showInputDialog("Ano do cadastro: "));

		return "despesas_" + strMes + "_" + strAno + ".txt";
	}

	public String getData() {
		return "despesas_" + strMes + "_" + strAno + ".txt";
	}

	// -----Leitura, Cadastro e GravaÃ§Ã£o de Pessoas

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
		try {
			if (nome.isBlank()) {

				throw new DadosPessoaisIncompletosException();
			}
			if (email.isBlank()) {
				throw new DadosPessoaisIncompletosException();
			}
			if (strRenda.isEmpty()) {
				throw new RendimentoInvalidoException();
			}
			if (renda <= 0) {
				throw new RendimentoInvalidoException();
			}
			// depois de verificar se todos os parametros estao validos será add o
			// listaPessoas.
			listaPessoas.add(new Pessoa(nome, email, renda));

		} catch (RendimentoInvalidoException d) {

			String msg = "rendimento invalido, tente novamente!!" + "\n" + d;
			JOptionPane.showMessageDialog(null, msg);
			d.printStackTrace();
		} catch (DadosPessoaisIncompletosException d) {
			String msg = "dados pessoais invalidos, tente novamente!!" + "\n" + d;
			JOptionPane.showMessageDialog(null, msg);
			d.printStackTrace();

		}

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

			for (Pessoa a : listaPessoas) {
				buffer.write(a.toString());
				buffer.write('\n');
			}

			buffer.close();
		} catch (IOException e) {

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

		// ----- Excluir de alunos.txt

		File fil = new File("alunos.txt");

		try {
			FileReader fr = new FileReader(fil);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<>();

			while (linha != null) {
				if (linha.equals(resp.getNome() + " ; " + resp.getEmail() + " ; " + resp.getRenda()) == false) {
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

	// -----Leitura, Cadastro, GravaÃ§Ã£o e ExclusÃ£o de Despesas

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
		// String resp = JOptionPane.showInputDialog("Subcategoria: ");

//		try{
//			listaDespesas.add(new Despesa(descricao, categoria, valor));
//			}
//		}catch(CategoriaNaoInformadaException f) {
//		
//	}
		try {
			if (descricao.isBlank()) {
				throw new DescricaoNaoInformadaException();
			}
			if (categoria.getNome().isBlank()) {
				throw new CategoriaNaoInformadaException();
			}
			if (valor <= 0) {
				throw new ValorNaoInformadoException();
			}

			listaDespesas.add(new Despesa(descricao, categoria, valor));

		} catch (CategoriaNaoInformadaException g) {
			String msg = "categoria nao informada, tente novamente!!" + "\n" + g;
			JOptionPane.showMessageDialog(null, msg);
			g.printStackTrace();
		} catch (DescricaoNaoInformadaException g) {
			String msg = "descrição nao informada, tente novamente!!" + "\n" + g;
			JOptionPane.showMessageDialog(null, msg);
			g.printStackTrace();
		} catch (ValorNaoInformadoException h) {
			String msg = "valor invalido ou nulo, insira um valor positivo!!" + "\n" + h;
			JOptionPane.showMessageDialog(null, msg);
			h.printStackTrace();
		}

		
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

			for (Despesa a : listaDespesas) {
				buffer.write(a.toString());
				buffer.write('\n');
			}

			buffer.close();
		} catch (IOException e) {

		}
	}

	public void excluirDespesa(String descricao) {
		Despesa desp = null;
		for (Despesa p : listaDespesas) {
			if (p.getDescricao().equalsIgnoreCase(descricao)) {
				desp = p;
				JOptionPane.showMessageDialog(null, "Cadastro de " + desp.getDescricao() + " removido");
				break;
			}
		}
		if (desp == null) {
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
				if (linha.equals(desp.getDescricao() + " ; " + desp.getCategoria().getNome() + " ; " + desp.getValor()) == false) {
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
			// TODO: handle exception
		}
	}

	// -------------------------------------------------------

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
