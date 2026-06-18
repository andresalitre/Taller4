package Dominio;

public class Supporter extends Carta {
	private int efectosPorTurno;

	public Supporter(String nombre, int rareza, int efectosPorTurno) {
		super(nombre, rareza);
		this.efectosPorTurno = efectosPorTurno;
	}

}
