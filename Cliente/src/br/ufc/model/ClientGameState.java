package br.ufc.model;

import java.util.HashMap;
import java.util.List;

import br.ufc.net.ServerFactory;

public class ClientGameState {

	public static HashMap<String, Player> playersCangaceiros = new HashMap<String, Player>();
	public static HashMap<String, Player> playersJaguncos = new HashMap<String, Player>();
	
	/**
	 * Variável que representa o Player do usuário do celular.
	 */
	public static Player eu = null;

	public static void updateState() {
		// Recupera lista de jogadores no servidor
		List<Player> playersOnServer = ServerFactory.getServer().getGameState();
		
		HashMap<String, Player> playersAux;
		Player playerAux;
		
		if (playersOnServer != null && playersOnServer.size() > 0) {
			for (Player player : playersOnServer) {
				
				if(player.getTipo() == Player.CANGACEIRO)
					playersAux = playersCangaceiros;
				else
					playersAux = playersJaguncos;
				
				playerAux = playersAux.get(player.getNome());

				// Caso o player ainda não esteja no GameState, adiciona-o
				if(playerAux == null) {
					playersAux.put(player.getNome(), player);
				} else {
					// Caso já exita, atualiza a posição geográfica
					playerAux.setLatitude(player.getLatitude());
					playerAux.setLongitude(player.getLongitude());
				}
			}
		}
	}
	
	/**
	 * Reiniciar o estado do ClientGameState
	 */
	public static void reset() {
		playersCangaceiros = new HashMap<String, Player>();
		playersJaguncos = new HashMap<String, Player>();
		eu = null;
	}
}