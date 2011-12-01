package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.artefatos.Barricada;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a aï¿½ï¿½o relacionada a uma mina quando um outro 
 * dispositivo se aproxima
 * @author Rafael
 *
 */
public class ProximityBarricadaListener extends ProximityListener{

	@Override
	public void action(IMobileDevice device1, IMobileDevice device2,double distance) {
		 this.device1 = device1;
		 this.device2 = device2;
		 // Verifica se o dispositivo ï¿½ do time adversï¿½rio
		 if(device1.getGroup() != device2.getGroup()){
			 if ((distance > Barricada.COLISION_DISTANCE)&&(distance < Barricada.VIEW_DISTANCE)){
				 actionVisibleArea(device2);
			 }
			 else{
				 if (distance <Barricada.COLISION_DISTANCE){
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
			 msg = "Barricada sendo avistada por um soldado inimigo\n";
			 break;
		 case Player.DOCTOR:
			 msg = "Barricada sendo avistada por um curandeiro inimigo\n";
			 break;
		 case Player.ENGINEER:
			 msg = "Barricada sendo avistada por um pedreiro inimigo\n";
			 break;
		 case Player.SPY:
			 msg = "Barricada sendo avistada por um dedo duro inimigo\n";
			 break;
		 case Mina.MINA:
			 msg = "Barricada bem pertim de outra mina inimiga\n";
			 break;
		 case Barricada.BARRICADA:
			 msg = "Barricada bem pertim de uma barricada inimiga\n";
			 break;
		 default: 
			 msg = "Barricada bem pertim de um Objeto não identificado\n";
			 break;		 
		 }
		 
		//Adiciona a mensagem ao gameStory
		 logMessage(msg);
	}
	/**
	 * 
	 * @param device
	 */
	private void actionCollisionArea(IMobileDevice device){
		 // Dar o tratamento para cada tipo de device do time inimigo
		 switch(device.getType()){
		 // Outro device
		 case Player.SOLDIER:
			 msg = "Soldado inimigo em riba de uma barricada\n";
			 break;
		 case Player.DOCTOR:
			 msg = "Curandeiro inimigo em riba de uma barricada\n";
			 break;
		 case Player.ENGINEER:
			 msg = "Pedreiro inimigo em riba de uma barricada\n";
			 break;
		 case Player.SPY:
			 msg = "Dedo duro inimigo em riba de uma barricada\n";
			 break;
		 case Mina.MINA:
			 msg = "Mina colocada em riba de uma barricada\n";
			 break;
		 case Barricada.BARRICADA:
			 msg = "Barricada colocada em riba de outra barricada\n";
			 break;
		 default: 
			 msg = "Objeto não identificado está em riba da barricada\n";
			 break;		  
		 }
		 
		 //Adiciona a mensagem ao gameStory
		 logMessage(msg);
	}
}
