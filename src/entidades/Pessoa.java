package entidades;

public class Pessoa {
	
	private String nome;
	private String email;
	private float renda;
	
	public Pessoa (String nome, String email, float renda) {
		this.nome = nome;
		this.email = email;
		this.renda = renda;
	}
	
	public Pessoa() {
		
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

	@Override
	public String toString() {
		return nome + " ; " + email + " ; " + renda;
	}
	
}
