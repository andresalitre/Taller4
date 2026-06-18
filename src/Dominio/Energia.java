package Dominio;

public class Energia extends Carta {
	private String elemento;

	public Energia(String nombre, int rareza,String elemento) {
		super(nombre, rareza);
		this.elemento = elemento;
	}
	
	public String getElemento() {
		return elemento;
	}

	@Override
	public String escribirse() {
		// TODO Auto-generated method stub
		return nombre + ";" + rareza + ";" + "Energy" + ";" + elemento;
	}
}
