package Logica;

import java.util.*;
import Dominio.*;
import java.io.*;

public class Main {
	static ISistema sistema = Sistema.getInstance();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		leer();
		sistema.iniciar();
	}

	public static void leer() throws FileNotFoundException { // lector de archivos que envia la linea al ISistema que la envia al Sistema que la envia al factory que le devuelve el objeto creado al sistema que lo añade a la lista
		Scanner lector = new Scanner(new File("Taller4/Sobres.txt"));
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			sistema.crearCarta(linea);
		}
		lector.close();
	}

	public static void guardarArchivo(List<Carta> cartas) throws IOException {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Sobres.txt"))) {
	    	for (Carta c : cartas) {
	    		bw.write(c.escribirse());
	    		bw.newLine();
	    	}
	    }
	}
}
