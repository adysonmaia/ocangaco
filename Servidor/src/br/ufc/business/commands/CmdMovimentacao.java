package br.ufc.business.commands;

import br.ufc.location.test.GameStateTest;
import br.ufc.location.test.LocationServerTest;
import br.ufc.servidor.Servidor;
import br.ufc.servidor.player.Player;
import myserver.kernel.CommandExecute;

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
		if(Servidor.gs.updateOrConnectPlayer(player)==1){
				resposta = "Player " + player.getNome() + " updated.";
		} else {
			resposta = "Erro";
			System.out.println("Erro em CmdMovimentacao");
		}
		return resposta;
	}

}