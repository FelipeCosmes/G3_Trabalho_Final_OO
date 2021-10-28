package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.UI;
import exceptions.CategoriaNaoInformadaException;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.ValorNaoInformadoException;
import servicos.CalculoDivida;

import javax.swing.*;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	
	public CalculoDivida calculoDivida;
	public SubCategoria subcategoria = new SubCategoria();
	
	private String nomeArquivoPessoa = "alunos.txt";
	private String strMes;
	private String strAno;

	public Republica(String strMes, String strAno) {
		listaPessoas = new ArrayList<>();
		listaDespesas = new ArrayList<>();
		
		calculoDivida = null;
		
		this.strMes = strMes;
		this.strAno = strAno;
	}

	// ----- get data do relat�rio
	public String getData() {
		return "despesas_" + strMes + "_" + strAno + ".txt";
	}

	// -----Leitura, Cadastro e Gravac�o de Pessoas

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

			UI.mensagem("Registros de Pessoa carregados do arquivo","Arquivo");
			buffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadastroPessoa(Pessoa p) {

				listaPessoas.add(p);



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

	public void excluirPessoa(Pessoa resp) {
//		Pessoa resp = null;
//		for(Pessoa p : listaPessoas){
//			if (p.getNome().equalsIgnoreCase(nome)){
//				resp = p;
//				JOptionPane.showMessageDialog(null, "Cadastro de " + resp.getNome() + " removido");
//				break;
//			}
//		}
//		if(resp==null){
//			JOptionPane.showMessageDialog(null, "Cadastro nao encontrado!");
//		}
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
		File f = new File(getData());
		if(f.isFile()) {
			try {
				in = new FileReader(getData());
				buffer = new BufferedReader(in);

				String linha = null;
				do {
					linha = buffer.readLine();

					if (linha != null) {
						String[] registro = linha.split(" ; ");
						Despesa a;
						a = new Despesa(registro[0], new Categoria(registro[1]), Float.parseFloat(registro[2]));
						listaDespesas.add(a);
					}
				} while (linha != null);

				UI.mensagem("Registros de Despesa carregados do arquivo","Arquivo");
				buffer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

	public void cadastroDespesa(Despesa d) {
		try{
			if(d.getCategoria().getNome().isBlank()){
				throw new CategoriaNaoInformadaException();
			}
			if(d.getDescricao().isBlank()){
				throw new DescricaoNaoInformadaException();
			}
			if (d.getValor() < 0) {
				throw new ValorNaoInformadoException();
			}

			int sub = JOptionPane.showConfirmDialog(null, "Deseja cadastrar subcategoria?");
			if(sub==0){
				subcategoria.setNome(JOptionPane.showInputDialog("Qual a subcategoria?"));
			}

			listaDespesas.add(d);
		}
		catch(ValorNaoInformadoException e){
			JOptionPane.showMessageDialog(null, "Valor invalido!\n"+ e);
		}
		catch(DescricaoNaoInformadaException e){
			JOptionPane.showMessageDialog(null, "Descricao nao informada!!\n"+ e);
		}
		catch (CategoriaNaoInformadaException e){
			JOptionPane.showMessageDialog(null, "Categoria nao informada\n"+ e);

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

	public void excluirDespesa(Despesa desp) {
//		Despesa desp = null;
//		for (Despesa p : listaDespesas) {
//			if (p.getDescricao().equalsIgnoreCase(descricao)) {
//				desp = p;
//				JOptionPane.showMessageDialog(null, "Cadastro de " + desp.getDescricao() + " removido");
//				break;
//			}
//		}
//		if (desp == null) {
//			JOptionPane.showMessageDialog(null, "Cadastro nao encontrado!");
//		}
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