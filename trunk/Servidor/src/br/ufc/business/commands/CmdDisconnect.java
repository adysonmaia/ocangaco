package br.ufc.business.commands;


import myserver.kernel.CommandExecute;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.servidor.gamestory.GameStoryControl;
import br.ufc.servidor.gamestory.Message;

public class CmdDisconnect extends CommandExecute {

	public CmdDisconnect () {
	}	

	@Override
	public String execute(String[] param) {
		Integer id = Integer.parseInt(param[0]);
		
		String resposta;
		DevicesPositionControl control = DevicesPositionControl.getInstance();
		
		// Busca o dispositivo
		IMobileDevice device = control.searchMobileDeviceById(id);
		resposta = String.valueOf(control.removeDevice(device));
		
		GameStoryControl storyControl = GameStoryControl.getInstance();
		Message message = new Message("Soldado saiu do jogo", device);
		storyControl.addMessage(message);
		
		return resposta;
	}

}