package br.ufc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.ufc.net.ServerFactory;

public class ClientGameState {

	public static Map<String, Player> playersCangaceiros = new ConcurrentHashMap<String, Player>();
	public static Map<String, Player> playersJaguncos = new ConcurrentHashMap<String, Player>();
	public static Map<Integer, Mine> mines = new ConcurrentHashMap<Integer, Mine>();
	public static HashMap<Integer, Barrier> barriers = new HashMap<Integer, Barrier>();

	/**
	 * Variável que representa o Player do usuário do celular.
	 */
	public static Player myPlayerOnClient = null;

	public static void updateState() {
		// Recupera lista de objetos no servidor
		List<MapObject> devicesOnServer = ServerFactory.getServer()
				.getGameState(myPlayerOnClient);

		Map<String, Player> playersAux;
		Player playerAux;

		if (devicesOnServer != null && devicesOnServer.size() > 0) {
			for (MapObject device : devicesOnServer) {
				// Atualiza instancias de objeto player
				if (device instanceof Player) {
					Player player = (Player) device;
					if (player.getTipo() == Player.CANGACEIRO)
						playersAux = playersCangaceiros;
					else
						playersAux = playersJaguncos;

					playerAux = playersAux.get(player.getNome());

					// Caso o player ainda não esteja no GameState, adiciona-o
					if (playerAux == null) {
						playersAux.put(player.getNome(), player);
					} else {
						// Caso já exita, atualiza a posição geográfica
						playerAux.setLatitude(player.getLatitude());
						playerAux.setLongitude(player.getLongitude());
					}
					System.out.println("Updated player: " + player);

				}
				// Atualiza instancias de objeto mina
				else if (device instanceof Mine) {
					// TODO setar no hashmap
					Mine mine = (Mine) device;
					System.out.println("Updated mine: " + mine);
				}
				// Atualiza instancias de objeto barrier
				else if (device instanceof Barrier) {
					// TODO setar no hashmap
					Barrier barrier = (Barrier) device;
					System.out.println("Updated barrier: " + barrier);
				}
			}
		}
	}

	/**
	 * Recupera o jogador como o servidor o vê. Use esse método ao invez de
	 * acessar myPlayerOnClient, ao menos que você saiba oq está fazendo.
	 * 
	 * @return
	 */
	public static Player getMyPlayerOnServer() {
		if (myPlayerOnClient.getTipo() == Player.CANGACEIRO)
			return playersCangaceiros.get(myPlayerOnClient.getNome());
		else
			return playersJaguncos.get(myPlayerOnClient.getNome());
	}

	/**
	 * Reiniciar o estado do ClientGameState
	 */
	public static void reset() {
		playersCangaceiros = new ConcurrentHashMap<String, Player>();
		playersJaguncos = new ConcurrentHashMap<String, Player>();
		mines = new ConcurrentHashMap<Integer, Mine>();
		barriers = new HashMap<Integer, Barrier>();
		myPlayerOnClient = null;
	}
}