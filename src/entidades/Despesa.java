package entidades;

import entidades.Categoria;

public class Despesa {
	
	String descrição;
	Categoria categoria; //só pra preencher o getCategoria
	float valor;
	
	public Despesa (String descrição, float valor, Categoria categoria) {
		this.descrição = descrição;
		this.valor = valor;
		this.categoria = categoria;
	}
	
	public String getDescrição() {

		return descrição;
	}
	
	public float getValor() {
		
		return  valor ;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

}
