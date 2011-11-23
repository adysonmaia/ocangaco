package br.ufc.model;

import java.util.HashMap;
import java.util.List;

import br.ufc.net.ServerFactory;
import br.ufc.util.Constants;

public class ClientGameState {

	public static HashMap<String, Player> players = new HashMap<String, Player>();
	
	// Belo nome pra uma vari�vel :) kkkkkkkkkkkkkk
	/**
	 * Vari�vel que representa o Player do usu�rio do celular.
	 */
	public static Player eu = null;

	static {
		// testInicializacaoAmigos();
		// testeAtualizacaoServidor();
	}

	public static void adicionarAmigosDeTeste() {
		// Teste de inicializa��o dos amigos
		players.put("Player 1", new Player("Player 1", 1, Constants.DEFAULT_LATITUDE
				+ Constants.LOCATION_INCREMENT, Constants.DEFAULT_LONGITUDE));
		players.put("Player 2", new Player("Player 2", 1, Constants.DEFAULT_LATITUDE,
				Constants.DEFAULT_LONGITUDE + Constants.LOCATION_INCREMENT));
	}

	public static void updateState() {
		// Recupera lista de jogadores no servidor
		List<Player> playersOnServer = ServerFactory.getServer().getGameState();
		
		//Reinicia o game state pois algum jogador pode ter desconectado
		players = new HashMap<String, Player>();
		
		if (playersOnServer != null && playersOnServer.size() > 0) {
			for (Player player : playersOnServer) {
				players.put(player.getNome(), player);	
			}
		}
	}
}