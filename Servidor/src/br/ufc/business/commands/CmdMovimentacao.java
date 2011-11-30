package br.ufc.business.commands;

import myserver.kernel.CommandExecute;
import br.ufc.servidor.gamestate.GameStateImp;
import br.ufc.servidor.player.Player;

public class CmdMovimentacao extends CommandExecute {

	public CmdMovimentacao () {
	}	

	@Override
	public String execute(String[] param) {
		String name = param[0];
		int tipo = Integer.parseInt(param[1]);
		double latitude = Double.parseDouble(param[2]);
		double longitude = Double.parseDouble(param[3]);		
		
		Player player = new Player(name, tipo, latitude, longitude);		
		//GameStateTest.getInstance().updateOrConnectPlayer(player);
		String resposta;
		if(GameStateImp.getInstance().update(player)==1){
				resposta = "Player " + player.getNome() + " updated.";
		} else {
			resposta = "Erro";
			System.out.println("Erro em CmdMovimentacao");
		}
		return resposta;
	}

}