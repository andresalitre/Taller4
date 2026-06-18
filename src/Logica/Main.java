package Logica;

import java.util.*;
import java.io.*;

public class Main {
	static ISistema sistema = Sistema.getInstance();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		leer();
		sistema.iniciar();
	}
	
	public static void leer() throws FileNotFoundException { //lector de archivos que envia la linea al ISistema que la envia al Sistema que la envia al factory que le devuelve el objeto creado al sistema que lo añade a la lista
		Scanner lector = new Scanner(new File("Sobres.txt"));
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			sistema.crearCarta(linea);
		} lector.close();
	}

}
