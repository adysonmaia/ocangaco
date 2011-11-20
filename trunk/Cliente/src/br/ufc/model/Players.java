package br.ufc.model;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class Players {

	public static List<Player> amigos = new ArrayList<Player>();
	public static List<Player> inimigos = new ArrayList<Player>();
	

	static{
		// Teste de inicialização dos amigos
		Player player = new Player("Joao Paulo");
		
		player.setLatitude((int)-3730715.0);
		player.setLongitude((int)-3.8575982E7);
		amigos.add(player);
	}
}
