package entidades;

import exceptions.CategoriaNaoInformadaException;
import exceptions.DescricaoNaoInformadaException;
import exceptions.ValorNaoInformadoException;

public class Despesa {
	
	String descricao;
	Categoria categoria;
	float valor;
	
	
	public Despesa (String descricao, Categoria categoria, float valor) {
		if (descricao.isBlank() || !descricao.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}") || descricao.equals("")) {
            throw new DescricaoNaoInformadaException();
        }
        if (categoria.getNome().isBlank() || !categoria.getNome().matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}") || categoria.getNome().equals("")) {
            throw new CategoriaNaoInformadaException();
        }
        if (valor <= 0) {
            throw new ValorNaoInformadoException();
        }

		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
	}

	public Despesa(){
		
	}

	public String getDescricao() {

		return descricao;
	}
	
	public float getValor() {
		
		return  valor ;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return descricao + " ; " + getCategoria().getNome() + " ; " + valor;
	}

}
