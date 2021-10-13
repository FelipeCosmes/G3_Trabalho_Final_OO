package entidades;

public class SubCategoria extends Categoria {
	
	private Categoria superCategoria;
	
	public SubCategoria (String nome, Categoria superCategoria) {
		super(nome);
		this.superCategoria = superCategoria;
	}
	
	public Categoria getCategoria() {
		return superCategoria;
	}

}
