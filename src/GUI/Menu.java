package GUI;

import java.util.*;
import Dominio.*;
import Logica.*;

public class Menu {
	
	private List<Carta> cartas;
	
	public Menu(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public void iniciar() {
		for (Carta c : cartas) {
			System.out.println(c);
		}
	}
	
}
