package Logica;

import Dominio.*;
import GUI.*;

import java.io.IOException;
import java.util.*;

public class Sistema implements ISistema {
	private static Sistema instance;
	private static List<Carta> cartas = new ArrayList<>();
	
	private Sistema( ) {}
	
	public static ISistema getInstance() {
		if (instance == null) instance = new Sistema();
		return instance;
		
	}

	@Override
	public void crearCarta(String linea) {
		cartas.add(cartaFactory.crearCarta(linea));
	}

	@Override
	public void iniciar() throws IOException {
		Menu menu = new Menu((ArrayList<Carta>) cartas);
		menu.iniciar();
	}

	@Override
	public ArrayList<Carta> getCartas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarCarta(int indice, String linea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCarta(int fila) {
		// TODO Auto-generated method stub
		
	}
	


}
