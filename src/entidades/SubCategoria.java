package entidades;

import exceptions.CategoriaNaoInformadaException;

public class SubCategoria extends Categoria {

	private Categoria superCategoria;

    public SubCategoria (String nome, Categoria superCategoria) {
        super(nome);
		if (nome.equals(null) || !nome.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}") || nome.isBlank()) {
            throw new CategoriaNaoInformadaException();
        }
        this.superCategoria = superCategoria;

    }
	public Categoria getCategoria() {
        return superCategoria;
    }

}
