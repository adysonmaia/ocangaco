package br.ufc.business.commands;


import br.ufc.servidor.gamestate.GameStateImp;
import myserver.kernel.CommandExecute;
import br.ufc.servidor.Servidor;

public class CmdDisconnect extends CommandExecute {

	public CmdDisconnect () {
	}	

	@Override
	public String execute(String[] param) {
		String name = param[0];
		//String resposta = GameStateTest.getInstance().disconnectPlayer(name);
		String resposta;
		if(GameStateImp.getInstance().disconnectPlayer(name)==1){
			resposta = "Player: " + name + " disconnected.";
		} else 
			resposta = "Erro";
		return resposta;
	}

}