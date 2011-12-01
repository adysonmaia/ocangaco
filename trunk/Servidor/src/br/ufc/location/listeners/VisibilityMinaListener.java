package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IVisibilityListener;
import br.ufc.servidor.artefatos.Mina;

/**
 * Esta classe implementa o que ocorre quando a mina entra no campo de visualização de algum
 * dispositivo
 * @author Rafael
 *
 */
public class VisibilityMinaListener implements IVisibilityListener{

	@Override
	public boolean isVisible(IMobileDevice device1, IMobileDevice device2,double distance) {
		 // Verifica se o dispositivo é do time adversário
		 if(device1.getGroup() != device2.getGroup()){
			 if ((distance > 0)&&(distance < Mina.VIEW_DISTANCE)){
				 return true;
			 }
		 }
		return false;
	}
}
