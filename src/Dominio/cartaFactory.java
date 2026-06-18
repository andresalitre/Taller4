package Dominio;

public class cartaFactory {
	
	public static Carta crearCarta(String linea) {
		String partes[] = linea.split(";");
		String tipo = partes[2];
		
		if (tipo.equals("Pokemon")) return new Pokemon(partes[0], Integer.valueOf(partes[1]), Integer.valueOf(partes[3]), Integer.valueOf(partes[4]));	
		if (tipo.equals("Item")) return new Item(partes[0], Integer.valueOf(partes[1]), Integer.valueOf(partes[3]));
		if (tipo.equals("Supporter")) return new Supporter(partes[0], Integer.valueOf(partes[1]), Integer.valueOf(partes[3]));
		if (tipo.equals("Energy")) return new Energia(partes[0], Integer.valueOf(partes[1]), partes[3]);
		return null;
	}
	
}
