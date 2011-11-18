package br.ufc.util;

import java.util.ArrayList;

import br.ufc.model.Player;

/**
 * @author André Fonteles, Rafael de Lima e Benedito Neto
 *
 * utility class to parse entities in a well formatted string and strings to entities.
 * 
 */
public class EntityParser {
	
	public static final String FIELD_SEPARATOR = "&";
	public static final String OBJECT_SEPARATOR = "@";
	
	public static Player parsePlayerToMessage(String message){
		String fields[] = message.split(FIELD_SEPARATOR);		
		Player player = new Player(fields[0], Integer.parseInt(fields[1]));
		
	    return player;	
	}
	
	public static String parseMessageToPlayer(Player player){
		return player.getNome() + FIELD_SEPARATOR + player.getTipo();
	}
	
	public static ArrayList<Player> parsePlayerListToMessage(String message){
		String players[] = message.split(OBJECT_SEPARATOR);
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		for (String string : players) {
			playerList.add(parsePlayerToMessage(string));
		}
		
		return playerList;
	}
	
	public static String parsePlayerListToMessage(ArrayList<Player> playerList){
		String message = "";
		for (Player player : playerList) {
			message += parseMessageToPlayer(player) + OBJECT_SEPARATOR;
		}		
		message = message.substring(0,message.length()-1);
		
		return message;
	}
	
	public static void main(String args[]){
		Player player = new Player("player1", 1);
		Player player2 = new Player("player2", 2);
		
		String p1 = parseMessageToPlayer(player);
		String p2 = parseMessageToPlayer(player2);
		
		System.out.println(p1 + " " + p2);
		
		player = parsePlayerToMessage(p2);
		player2 = parsePlayerToMessage(p1);
		
		System.out.println(player.getNome() + " " + player.getTipo() + 
				"\n" + player2.getNome() + " " + player2.getTipo());
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player);
		playerList.add(player2);
		
		String list = parsePlayerListToMessage(playerList);
		
		System.out.println(list);
		
		playerList = null;
		playerList = parsePlayerListToMessage(list);
		
		for (Player p : playerList) {
			System.out.println(p.getNome() + " " + p.getTipo());
		}		
	}
}