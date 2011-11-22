package br.ufc.model;

import java.util.ArrayList;
import java.util.List;

public class ClientGameState {

	public static List<Player> amigos = new ArrayList<Player>();
	public static List<Player> inimigos = new ArrayList<Player>();
	
	// Belo nome pra uma vari·vel :) kkkkkkkkkkkkkk
	/**
	 * Vari·vel que representa o Player do usu·rio do celular.
	 */
	public static Player eu = null;

	static{
		// Teste de inicializa√ß√£o dos amigos
		Player player = new Player("Joao Paulo");
		
		player.setLatitude((int)-3730715.0);
		player.setLongitude((int)-3.8575982E7);
		amigos.add(player);
	}
}
