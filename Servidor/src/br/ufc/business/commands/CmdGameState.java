package br.ufc.business.commands;

import java.util.ArrayList;

import myserver.kernel.CommandExecute;
import br.ufc.servidor.player.Player;
import br.ufc.util.EntityParser;

/**
 * @author Rafael
 * A test command
 */

public class CmdGameState extends CommandExecute {

	public CmdGameState () {
	}	

	/**
	 * @author Rafael
	 * get List of players and his correct positions to send to clients
	 */

	public ArrayList<Player> getPlayerList(){
		Player player = new Player("player1", 1, 33.33333, 22.22222);
		Player player2 = new Player("player2", 2, 34.33333, 23.22222);
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		playerList.add(player);
		playerList.add(player2);
		
		return playerList;
		
		/**
		 * Usar interface - chamar Servidor.gs.listaJogadores()
		 * ex:
		 * 
		 * return Servidor.gs.listaJogadores();
		 * 
		 */
	}
	
	
	@Override
	public String execute(String[] param) {		
		return EntityParser.parsePlayerListToMessage(getPlayerList());
	}
}