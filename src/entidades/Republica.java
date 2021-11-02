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
import exceptions.DescricaoNaoInformadaException;
import exceptions.ValorNaoInformadoException;
import servicos.CalculoDivida;

import javax.swing.*;

public class Republica {

	private List<Pessoa> listaPessoas;
	private List<Despesa> listaDespesas;
	
	public CalculoDivida calculoDivida;
	
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

	// ----- get data do relatorio
	public String getData() {
		return "despesas_" + strMes + "_" + strAno + ".txt";
	}

	// -----Leitura, Cadastro e Gravacao de Pessoas

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
		listaPessoas.remove(resp);
	}

	// -----Leitura, Cadastro, Gravacao e Exclusao de Despesas

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

		if (!(strAno == null || strMes == null)){
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
	}

	public void excluirDespesa(Despesa desp) {
		listaDespesas.remove(desp);
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
