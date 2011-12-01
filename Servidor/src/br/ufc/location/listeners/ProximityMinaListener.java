package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.artefatos.Barricada;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a a��o relacionada a uma mina quando um outro 
 * dispositivo se aproxima
 * @author Rafael
 *
 */
public class ProximityMinaListener extends ProximityListener{

	@Override
	public void action(IMobileDevice mina, IMobileDevice device2,double distance) {
		 
		 // Verifica se o dispositivo � do time advers�rio
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
		 case Mina.MINA:
			 System.out.printf("mina colocada bem pertim da outra\n");
			 break;
		 case Barricada.BARRICADA:
			 System.out.printf("barricada colocada bem pertim da outra\n");
			 break;
		 default: 
			 System.out.printf("visible: device sem tipo\n");
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
		 case Mina.MINA:
			 System.out.printf("mina colocada em riba da outra\n");
			 break;
		 case Barricada.BARRICADA:
			 System.out.printf("barricada colocada em riba da mina\n");
			 break;
		 }
	}
}
