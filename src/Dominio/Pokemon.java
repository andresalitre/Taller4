package Dominio;

public class Pokemon extends Carta {
	private int daño;
	private int cantidadEnergias;
	
	public Pokemon(String nombre, int rareza,int daño, int cantidadEnergias) {
		super(nombre, rareza);
		this.daño = daño;
		this.cantidadEnergias = cantidadEnergias;
		
	}
	
	public int getDaño() {
		return daño;
	}
	
	public int getCantidadEnergias() {
		return cantidadEnergias;
	}

	@Override
	public String escribirse() {
		// TODO Auto-generated method stub
		return nombre + ";" + rareza + ";" + "Pokemon" + ";" + daño + ";" + cantidadEnergias;
	}

}
