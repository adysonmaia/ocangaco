package br.ufc.business.commands;

import java.util.ArrayList;

import br.ufc.util.EntityParser;
import br.ufc.servidor.gamestate.GameStateImp;
import br.ufc.servidor.player.Player;
import myserver.kernel.CommandExecute;

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
		//return GameStateTest.getInstance().getPlayerList();
		ArrayList<Player> list = GameStateImp.getInstance().getPlayerList();
		if(list == null)
			System.out.println("lista vazia");
		
		return list;
	
		
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