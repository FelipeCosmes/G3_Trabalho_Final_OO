package servicos;

import entidades.Republica;

public class RegraProporcional extends CalculoDivida {
    
	public RegraProporcional(Republica republica) {
		super(republica);
	}
	
	public float divida(float renda){
        return this.despesaTotal() * renda / this.rendaTotal();
    }
	
}
