package br.ufc.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.ufc.net.ServerFactory;

public class ClientGameState {

	public static Map<String, Player> players = new ConcurrentHashMap<String, Player>();
	public static Map<Integer, Mine> mines = new ConcurrentHashMap<Integer, Mine>();
	public static Map<Integer, Barrier> barriers = new ConcurrentHashMap<Integer, Barrier>();

	/**
	 * Variável que representa o Player do usuário do celular.
	 */
	public static Player myPlayerOnClient = null;

	public static void updateState() {
		// Recupera lista de objetos no servidor
		List<MapObject> devicesOnServer = ServerFactory.getServer()
				.getGameState(myPlayerOnClient);
		
//		players.clear();
//		mines.clear();
//		barriers.clear();

		if (devicesOnServer != null && devicesOnServer.size() > 0) {
			for (MapObject device : devicesOnServer) {
				// Atualiza instancias de objeto player
				if (device instanceof Player) {
					Player playerUpdate = (Player) device;
					players.put(playerUpdate.getNome(), playerUpdate);
					System.out.println("Updated player: " + playerUpdate);

				}
				// Atualiza instancias de objeto mina
				else if (device instanceof Mine) {
					Mine mine = (Mine) device;
					mines.put(mine.getId(), mine);
//					System.out.println("Updated mine: " + mine);
				}
				// Atualiza instancias de objeto barrier
				else if (device instanceof Barrier) {
					Barrier barrier = (Barrier) device;
					barriers.put(barrier.getId(), barrier);
//					System.out.println("Updated barrier: " + barrier);
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
		return players.get(myPlayerOnClient.getNome());
	}

	/**
	 * Reiniciar o estado do ClientGameState
	 */
	public static void reset() {
		players = new ConcurrentHashMap<String, Player>();
		mines = new ConcurrentHashMap<Integer, Mine>();
		barriers = new HashMap<Integer, Barrier>();
		myPlayerOnClient = null;
	}
}