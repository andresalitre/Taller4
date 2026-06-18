package Dominio;

public abstract class Carta {
	private String nombre;
	private int rareza;
	
	public Carta(String nombre, int rareza) {
		this.nombre = nombre;
		this.rareza = rareza;
	}

	@Override
	public String toString() {
		return "Carta [nombre=" + nombre + ", rareza=" + rareza + "]";
	}
	
	
	
}
