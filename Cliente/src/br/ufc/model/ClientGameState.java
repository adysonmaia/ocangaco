package br.ufc.model;

import java.util.HashMap;
import java.util.List;

import br.ufc.net.ServerFactory;

public class ClientGameState {

	public static HashMap<String, Player> playersCangaceiros = new HashMap<String, Player>();
	public static HashMap<String, Player> playersJaguncos = new HashMap<String, Player>();
	public static HashMap<Integer, Mine> mines =  new HashMap<Integer, Mine>();
	
	/**
	 * Vari�vel que representa o Player do usu�rio do celular.
	 */
	public static Player myPlayerOnClient = null;

	// Bloco criado apenas para fins de teste
	static {
		mines.put(1, new Mine(1, 1, 100, -3.746334, -38.578006));
	}
	
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

				// Caso o player ainda n�o esteja no GameState, adiciona-o
				if(playerAux == null) {
					playersAux.put(player.getNome(), player);
				} else {
					// Caso j� exita, atualiza a posi��o geogr�fica
					playerAux.setLatitude(player.getLatitude());
					playerAux.setLongitude(player.getLongitude());
				}
			}
		}
	}
	
	/**
	 * Recupera o jogador como o servidor o v�. Use esse m�todo ao invez de acessar myPlayerOnClient,
	 * ao menos que voc� saiba oq est� fazendo.
	 * @return
	 */
	public static Player getMyPlayerOnServer() {
		if(myPlayerOnClient.getTipo() == Player.CANGACEIRO)
			return playersCangaceiros.get(myPlayerOnClient.getNome());
		else
			return playersJaguncos.get(myPlayerOnClient.getNome());
	}
	
	/**
	 * Reiniciar o estado do ClientGameState
	 */
	public static void reset() {
		playersCangaceiros = new HashMap<String, Player>();
		playersJaguncos = new HashMap<String, Player>();
		mines =  new HashMap<Integer, Mine>();
		myPlayerOnClient = null;
	}
}