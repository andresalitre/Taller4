package Dominio;

public class Item extends Carta {
	private int bonificacion;
	
	public Item(String nombre, int rareza,int bonificacion) {
		super(nombre, rareza);
		this.bonificacion = bonificacion;
	}
	
	
	public int getBonificacion() {
		return bonificacion;
	}


	@Override
	public String escribirse() {
		// TODO Auto-generated method stub
		return nombre + ";" + rareza + ";" + "Item" + ";" + bonificacion;
	}

}
