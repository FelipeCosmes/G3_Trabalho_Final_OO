package entidades;

import entidades.Categoria;

public class Despesa {
	
	String descri��o;
	Categoria categoria; //s� pra preencher o getCategoria
	float valor;
	
	public Despesa (String descri��o, float valor, Categoria categoria) {
		this.descri��o = descri��o;
		this.valor = valor;
		this.categoria = categoria;
	}
	
	public String getDescri��o() {

		return descri��o;
	}
	
	public float getValor() {
		
		return  valor ;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

}
