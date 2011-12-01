package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a ação relacionada a uma mina quando um outro 
 * dispositivo se aproxima
 * @author Rafael
 *
 */
public class ProximityMinaListener implements IProximityListener{

	@Override
	public void action(IMobileDevice mina, IMobileDevice device2,double distance) {
		 
		 // Verifica se o dispositivo é do time adversário
		 if(mina.getGroup() != device2.getGroup()){
			 if ((distance > Mina.COLISION_DISTANCE)&&(distance < Mina.VIEW_DISTANCE)){
				 actionVisibleArea(device2);
			 }
			 else{
				 if (distance <Mina.COLISION_DISTANCE){
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
		 case Player.DOCTOR:			 
		 case Player.ENGINEER:			 
		 case Player.SPY:
			 System.out.printf("To avistando uma mina inimiga\n");
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
			 System.out.printf("soldado inimigo atingido por uma mina\n");
			 break;
		 case Player.DOCTOR:
			 System.out.printf("curandeiro inimigo atingido por uma mina\n");
			 break;
		 case Player.ENGINEER:
			 System.out.printf("pedreiro inimigo atingido por uma mina\n");
			 break;
		 case Player.SPY:
			 System.out.printf("dedo duro inimigo atingido por uma mina\n");
			 break;
		 }
	}
}
