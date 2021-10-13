package entidades;

public class Pessoa {
	
	private String nome;
	private String email;
	private float renda;
	
	private Republica republica;
	
	public Pessoa (String nome, String email, float renda, Republica republica) {
		this.nome = nome;
		this.email = email;
		this.renda = renda;
		this.republica = republica;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public float getRenda() {
		return renda;
	}
	
	public void setRenda(float renda) {
		this.renda = renda;
	}
	
	public float divida() {
		if (!republica.getDivisaoProporcional()) {
			return republica.despesaTotal() / republica.getListaPessoas().size();
		} else {
			return republica.despesaTotal() * renda / republica.rendaTotal();
		}
	}
}
