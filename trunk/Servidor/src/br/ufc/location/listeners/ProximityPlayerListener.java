package proximitylistener;

import facade.IMobileDevice;
import facade.IProximityListener;

/**
 * Esta classe implementa a ação que o jogado executa quando se relaciona com outro
 * dispositivo movel
 * @author Danilo Reis
 *
 */
public class ProximityPlayerListener implements IProximityListener{

	@Override
	public void action(IMobileDevice device1, IMobileDevice device2,double distance) {
		 
		 // Verifica se o dispositivo é do time adversário
		 if(device1.getGroup() != device2.getGroup()){
			 // Dar o tratamento para cada tipo de objeto do time inimigo
			 switch(device2.getType()){
			    // Outro Jogador
			 
			 }
			 
		 }
	}


}
