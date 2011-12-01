package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a ação que o jogado executa quando se relaciona com outro
 * dispositivo movel
 * @author Danilo Reis
 *
 */
public class ProximityPlayerListener extends ProximityListener{

	@Override
	public void action(IMobileDevice device1, IMobileDevice device2,double distance) {
		 
		 // Verifica se o dispositivo é do time adversário
		 if(device1.getGroup() != device2.getGroup()){
			 if ((distance > Player.COLISION_DISTANCE)&&(distance < Player.VIEW_DISTANCE)){
				 actionVisibleArea(device2);
			 }
			 else{
				 if (distance <Player.COLISION_DISTANCE){
					 actionCollisionArea(device2);
				 }
			 }
		 }
	}
	/**
	 * 
	 * @param device
	 */
	private void actionVisibleArea(IMobileDevice device){
		 // Dar o tratamento para cada tipo de objeto do time inimigo
		 switch(device.getType()){
		    // Outro Jogador
		 case Player.SOLDIER:
			 System.out.printf("To avistando um jagunço inimigo\n");
			 break;
		 case Player.DOCTOR:
			 System.out.printf("To avistando  um curandeiro inimigo\n");
			 break;
		 case Player.ENGINEER:
			 System.out.printf("To avistando  um pedreiro inimigo\n");
			 break;
		 case Player.SPY:
			 System.out.printf("To avistando  um dedo duro inimigo\n");
			 break;
		 }
	}
	/**
	 * 
	 * @param device
	 */
	private void actionCollisionArea(IMobileDevice device){
		 // Dar o tratamento para cada tipo de objeto do time inimigo
		 switch(device.getType()){
		 // Outro Jogador
		 case Player.SOLDIER:
			 System.out.printf("To em riba de um jagunço inimigo\n");
			 break;
		 case Player.DOCTOR:
			 System.out.printf("To em riba de um curandeiro inimigo\n");
			 break;
		 case Player.ENGINEER:
			 System.out.printf("To em riba de um pedreiro inimigo\n");
			 break;
		 case Player.SPY:
			 System.out.printf("To em riba de um dedo duro inimigo\n");
			 break;
		 }
	}
}
