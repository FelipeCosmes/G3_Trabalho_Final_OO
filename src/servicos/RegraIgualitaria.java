package servicos;

import entidades.Pessoa;
import entidades.Republica;

public class RegraIgualitaria extends CalculoDivida {
	
	public RegraIgualitaria(Republica republica) {
		super(republica);
	}

	@Override
    public float divida(Pessoa p){
        return 0;
    }

}
