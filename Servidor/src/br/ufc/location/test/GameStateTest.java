package br.ufc.location.test;

import java.util.ArrayList;

import br.ufc.servidor.player.Player;

public class GameStateTest {

	private static GameStateTest instance = null;

	public static GameStateTest getInstance() {
		if (instance == null)
			instance = new GameStateTest();

		return instance;
	}

	private ArrayList<Player> players = null;

	public GameStateTest() {
		super();
		players = new ArrayList<Player>();
	}

	public void connectPlayer(Player player) {
		players.add(player);
	}

	public void disconnectPlayer(Player player) {
		players.remove(player);
	}

	public void updateOrConnectPlayer(Player player) {
		boolean updated = false;
		if (players != null && players.size() > 0) {
			for (Player p : players) {
				if (player.getNome().equals(p.getNome())) {
					p = player;
					updated = true;
				}
			}
		}
		if (!updated) {
			players.add(player);
		}
	}

	public ArrayList<Player> getPlayerList() {
		return players;
	}

	public ArrayList<Player> getPlayerListByType(int tipo) {
		ArrayList<Player> playersByTipo = new ArrayList<Player>();

		if (players != null && players.size() > 0) {
			for (Player player : players) {
				if (player.getTipo() == tipo) {
					playersByTipo.add(player);
				}
			}
		}

		return playersByTipo;
	}
}
