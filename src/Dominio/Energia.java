package Dominio;

public class Energia extends Carta {
	private String elemento;

	public Energia(String nombre, int rareza,String elemento) {
		super(nombre, rareza);
		this.elemento = elemento;
	}

}
