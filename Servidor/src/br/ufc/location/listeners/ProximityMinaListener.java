package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.artefatos.Barricada;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a a��o relacionada a uma mina quando um outro
 * dispositivo se aproxima
 * 
 * @author Rafael
 * 
 */
public class ProximityMinaListener extends ProximityListener {
	@Override
	public void action(IMobileDevice device1, IMobileDevice device2,
			double distance) {
		this.device1 = device1;
		this.device2 = device2;
		// Verifica se o dispositivo � do time advers�rio
		if (device1.getGroup() != device2.getGroup()) {
			if ((distance > Mina.COLISION_DISTANCE)
					&& (distance < Mina.VIEW_DISTANCE)) {
				actionVisibleArea(device2);
			} else {
				if (distance < Mina.COLISION_DISTANCE) {
					actionCollisionArea(device2);
				}
			}
		}
	}

	/**
	 * 
	 * @param device
	 */
	private void actionVisibleArea(IMobileDevice device) {
		// Dar o tratamento para cada tipo de objeto do time inimigo
		switch (device.getType()) {
		// Outro Jogador
		case Player.SOLDIER:
			msg = "Mina sendo avistada por um soldado inimigo\n";
			break;
		case Player.DOCTOR:
			msg = "Mina sendo avistada por um curandeiro inimigo\n";
			break;
		case Player.ENGINEER:
			msg = "Mina sendo avistada por um pedreiro inimigo\n";
			break;
		case Player.SPY:
			msg = "Mina sendo avistada por um dedo duro inimigo\n";
			break;
		case Mina.MINA:
			msg = "Mina bem pertim de outra mina inimiga\n";
			break;
		case Barricada.BARRICADA:
			msg = "Mina bem pertim de uma barricada inimiga\n";
			break;
		default:
			msg = "Mina bem pertim de um Objeto n�o identificado\n";
			break;
		}

		// Adiciona a mensagem ao gameStory
		logMessage(msg);
	}

	/**
	 * 
	 * @param device
	 */
	private void actionCollisionArea(IMobileDevice device) {
		// Dar o tratamento para cada tipo de objeto do time inimigo
		switch (device.getType()) {
		// Outro Jogador
		case Player.SOLDIER:
			//Apenas remove a mina dos dispositivos. A redu��o do life � feita no listener do player.
			control.removeDevice(device1);
			msg = "Mina atingiu um soldado inimigo\n";
			break;
		case Player.DOCTOR:
			//Apenas remove a mina dos dispositivos. A redu��o do life � feita no listener do player.
			control.removeDevice(device1);
			msg = "Mina atingiu um curandeiro inimigo\n";
			break;
		case Player.ENGINEER:
			//Apenas remove a mina dos dispositivos. A redu��o do life � feita no listener do player.
			control.removeDevice(device1);
			msg = "Mina atingiu um pedreiro inimigo\n";
			break;
		case Player.SPY:
			//Apenas remove a mina dos dispositivos. A redu��o do life � feita no listener do player.
			control.removeDevice(device1);
			msg = "Mina atingiu um espi�o inimigo\n";
			break;
		case Mina.MINA:			
			msg = "Mina colocada em riba da outra\n";
			break;
		case Barricada.BARRICADA:
			msg = "Mina colocada em riba de barricada\n";
			break;
		default:
			msg = "Mina colocada em riba de objeto n�o identificado\n";
			break;
		}

		// Adiciona a mensagem ao gameStory
		logMessage(msg);
	}	
}
