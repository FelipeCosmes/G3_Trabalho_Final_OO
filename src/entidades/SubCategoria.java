package entidades;

public class SubCategoria extends Categoria {
	
	public SubCategoria(String nome) {
		super(nome);
		//TODO Auto-generated constructor stub
	}

	@Override
	public String getNome(){
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
