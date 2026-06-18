package Dominio;

public abstract class Carta {
	protected String nombre;
	protected int rareza;
	
	public Carta(String nombre, int rareza) {
		this.nombre = nombre;
		this.rareza = rareza;
	}

	@Override
	public String toString() {
		return "Carta [nombre=" + nombre + ", rareza=" + rareza + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public int getRareza() {
		return rareza;
	}
	
	public abstract String escribirse();
	
	
	
}
