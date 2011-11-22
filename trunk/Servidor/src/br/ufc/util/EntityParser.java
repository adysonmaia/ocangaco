package br.ufc.util;

import java.util.ArrayList;

import br.ufc.servidor.player.Player;

/**
 * @author André Fonteles, Rafael de Lima e Benedito Neto
 * 
 *         Utility class to parse entities in a well formatted string and
 *         strings to entities.
 * 
 */
public class EntityParser {

	public static final String FIELD_SEPARATOR = "&";
	public static final String OBJECT_SEPARATOR = "@";

	public static Player parsePlayerToMessage(String message) {
		System.out.println("EntityParser: message to parse: " + message);
		String fields[] = message.split(FIELD_SEPARATOR);
		Player player = new Player(fields[0], Integer.parseInt(fields[1]),
				Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));

		return player;
	}

	public static String parseMessageToPlayer(Player player) {
		String message = player.getNome() + FIELD_SEPARATOR + player.getTipo()
				+ FIELD_SEPARATOR + player.getLatitude() + FIELD_SEPARATOR
				+ player.getLongitude();
		System.out.println("EntityParser: parsed message: " + message);
		return message;
	}

	public static ArrayList<Player> parseMessageToPlayerList(String message) {
		System.out.println("EntityParser: message to parse: " + message);
		String players[] = message.split(OBJECT_SEPARATOR);

		ArrayList<Player> playerList = new ArrayList<Player>();
		if (players != null && players.length > 0) {
			for (String string : players) {
				playerList.add(parsePlayerToMessage(string));
			}
		}

		return playerList;
	}

	public static String parsePlayerListToMessage(ArrayList<Player> playerList) {
		String message = "";
		if (playerList != null && playerList.size() > 0) {
			for (Player player : playerList) {
				message += parseMessageToPlayer(player) + OBJECT_SEPARATOR;
			}
			message = message.substring(0, message.length() - 1);
		}

		System.out.println("EntityParser: parsed message: " + message);
		return message;
	}

	public static void main(String args[]) {
		Player player = new Player("player1", 1);
		Player player2 = new Player("player2", 2);

		String p1 = parseMessageToPlayer(player);
		String p2 = parseMessageToPlayer(player2);

		System.out.println(p1 + " " + p2);

		player = parsePlayerToMessage(p2);
		player2 = parsePlayerToMessage(p1);

		System.out.println(player.getNome() + " " + player.getTipo() + "\n"
				+ player2.getNome() + " " + player2.getTipo());

		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player);
		playerList.add(player2);

		String list = parsePlayerListToMessage(playerList);

		System.out.println(list);

		playerList = null;
		playerList = parseMessageToPlayerList(list);

		for (Player p : playerList) {
			System.out.println(p.getNome() + " " + p.getTipo());
		}
	}
}