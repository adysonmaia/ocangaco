package br.ufc.model;

import java.util.ArrayList;
import java.util.List;

import br.ufc.net.ServerFactory;
import br.ufc.util.Constants;

public class ClientGameState {

	public static List<Player> cangaceiros = new ArrayList<Player>();
	public static List<Player> jaguncos = new ArrayList<Player>();

	// Belo nome pra uma vari·vel :) kkkkkkkkkkkkkk
	/**
	 * Vari·vel que representa o Player do usu·rio do celular.
	 */
	public static Player eu = null;

	static {
		// testInicializacaoAmigos();
		// testeAtualizacaoServidor();
	}

	public static void testInicializacaoAmigos() {
		// Teste de inicializa√ß√£o dos amigos
		cangaceiros.add(new Player("Player 1", 1, Constants.DEFAULT_LATITUDE
				+ Constants.LOCATION_INCREMENT, Constants.DEFAULT_LONGITUDE));
		cangaceiros.add(new Player("Player 2", 1, Constants.DEFAULT_LATITUDE,
				Constants.DEFAULT_LONGITUDE + Constants.LOCATION_INCREMENT));
	}

	public static void testeAtualizacaoServidor() {
		// Recupera lista de jogadores/objetos no servidor
		List<Player> players = ServerFactory.getServer().getGameState();

		if (players != null && players.size() > 0) {
			cangaceiros = new ArrayList<Player>();
			jaguncos = new ArrayList<Player>();
			for (Player player : players) {
				if (player.getNome().equals(eu.getNome())) {
					eu = player;
				} else if (player.getTipo() == 1) {
					cangaceiros.add(player);
				} else {
					jaguncos.add(player);
				}
			}
		}
	}
}
