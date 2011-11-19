package br.ufc.business.commands;

import myserver.kernel.CommandExecute;

public class CmdMovimentacao extends CommandExecute {

	public CmdMovimentacao () {
	}	

	@Override
	public String execute(String[] param) {
		String playerName = param[0];
		String playerLatitude = param[1];
		String playerLongitude = param[2];		
		
		String resposta = "Name: " + playerName + " Lat: " + playerLatitude + " Lon: " + playerLongitude;
		return resposta;
	}

}