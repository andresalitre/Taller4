package Logica;

import java.io.IOException;
import java.util.ArrayList;

import Dominio.Carta;

public interface ISistema {

	void crearCarta(String linea);
	
	void iniciar() throws IOException;

	ArrayList<Carta> getCartas();

	void modificarCarta(int indice, String linea);

	void eliminarCarta(int fila);
}
