package br.ufc.business.commands;

import br.ufc.location.test.GameStateTest;
import br.ufc.location.test.LocationServerTest;
import br.ufc.servidor.Servidor;
import br.ufc.servidor.player.Player;
import myserver.kernel.CommandExecute;

public class CmdDisconnect extends CommandExecute {

	public CmdDisconnect () {
	}	

	@Override
	public String execute(String[] param) {
		String name = param[0];
		//String resposta = GameStateTest.getInstance().disconnectPlayer(name);
		String resposta;
		if(Servidor.gs.disconnectPlayer(name)==1){
			resposta = "Player: " + name + " disconnected.";
		} else 
			resposta = "Erro";
		return resposta;
	}

}