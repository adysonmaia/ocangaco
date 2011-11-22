package br.ufc.model;

import java.util.ArrayList;
import java.util.List;

import br.ufc.net.ServerFactory;
import br.ufc.util.Constants;

public class ClientGameState {

	public static List<Player> amigos = new ArrayList<Player>();
	public static List<Player> inimigos = new ArrayList<Player>();

	// Belo nome pra uma vari·vel :) kkkkkkkkkkkkkk
	/**
	 * Vari·vel que representa o Player do usu·rio do celular.
	 */
	public static Player eu = null;

	static {
		 testInicializacaoAmigos();
		//testeInicializacaoServidor();
	}

	public static void testInicializacaoAmigos() {
		// Teste de inicializa√ß√£o dos amigos
		amigos.add(new Player("Player 1", 1, Constants.DEFAULT_LATITUDE
				+ Constants.LOCATION_INCREMENT, Constants.DEFAULT_LONGITUDE));
		amigos.add(new Player("Player 2", 1, Constants.DEFAULT_LATITUDE,
				Constants.DEFAULT_LONGITUDE + Constants.LOCATION_INCREMENT));
	}

	private static void testeInicializacaoServidor() {
		// Recupera lista de jogadores/objetos no servidor
		List<Player> players = ServerFactory.getServer().getGameState();

		if (players != null && players.size() > 0) {
			for (Player player : players) {
				if (player.getNome().equals(eu.getNome())) {
					eu = player;
				} else {
					for (Player a : amigos) {
						if (player.getNome().equals(a.getNome())) {
							a.setLatitude(player.getLatitude());
							a.setLongitude(player.getLongitude());
						}
					}
				}
			}
		}
	}

}
