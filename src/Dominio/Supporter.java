package Dominio;

public class Supporter extends Carta {
	private int efectosPorTurno;

	public Supporter(String nombre, int rareza, int efectosPorTurno) {
		super(nombre, rareza);
		this.efectosPorTurno = efectosPorTurno;
	}
	
	public int getEfecto() {
		return efectosPorTurno;
	}

	@Override
	public String escribirse() {
		// TODO Auto-generated method stub
		return nombre + ";" + rareza + ";" + "Supporter" + ";" + efectosPorTurno;
	}

}
