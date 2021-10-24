package servicos;

import entidades.Republica;

public class RegraIgualitaria extends CalculoDivida {
	
	public RegraIgualitaria(Republica republica) {
		super(republica);
	}

    public float divida(){
        return 0;
    }

}
