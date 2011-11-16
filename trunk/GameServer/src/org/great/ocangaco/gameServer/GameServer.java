package org.great.ocangaco.gameServer;
import java.util.LinkedList;

import org.great.ocangaco.Player;

public class GameServer {

	private LinkedList<Player> list;
	
	/**
	 * Construtor Padrão
	 */
	public GameServer(){
		list = new LinkedList<Player>();
	}
	
	/**
	 * Adicionar um jogador à lista de jogadores 
	 * @param player Jogador a ser inserido na lista
	 */
	public void addPlayer(Player player) {
		list.add(player);
	}
}
