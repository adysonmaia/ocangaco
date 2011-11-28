package br.ufc.business.commands;

import java.util.ArrayList;

import myserver.kernel.CommandExecute;
import br.ufc.servidor.Servidor;
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
//		return GameStateTest.getInstance().getPlayerList();
		return Servidor.gs.getPlayerList();
		
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