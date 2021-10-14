package entidades;

public class Despesa {
	
	String descricao;
	Categoria categoria;
	float valor;
	
	public Despesa (String descricao, float valor, Categoria categoria) {
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

}
