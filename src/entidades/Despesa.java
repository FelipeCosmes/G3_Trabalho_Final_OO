package entidades;

public class Despesa {
	
	String descricao;
	Categoria categoria;
	float valor;
	
	
	public Despesa (String descricao, Categoria categoria, float valor) {
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
		//return descricao + " ; " + getCategoria().getNome() + " ; " + String.format("%.2f", valor);
		return descricao + " ; " + getCategoria().getNome() + " ; " + valor;
	}

}
