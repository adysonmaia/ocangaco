package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.player.Player;

public class VisibilityPlayerListener extends VisibilityListener{

	@Override
	public boolean isVisible(IMobileDevice device1, IMobileDevice device2,double distance) {
		 // Verifica se o dispositivo é do time adversário
		 if(device1.getGroup() != device2.getGroup()){
			 if ((distance > 0)&&(distance < Player.VIEW_DISTANCE) && ((Player)device2).isEscondido()){
				 return true;
			 }
		 }
		return false;
	}

}
