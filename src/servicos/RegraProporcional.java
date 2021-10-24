package servicos;

import entidades.Pessoa;
import entidades.Republica;

public class RegraProporcional extends CalculoDivida {
    
	public RegraProporcional(Republica republica) {
		super(republica);
	}
	
	@Override
	public float divida(Pessoa p){
		float renda = p.getRenda();
        return this.despesaTotal() * renda / this.rendaTotal();
    }
	
}
