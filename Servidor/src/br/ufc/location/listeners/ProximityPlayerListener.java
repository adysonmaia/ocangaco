package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.servidor.artefatos.Barricada;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.servidor.player.Player;

/**
 * Esta classe implementa a a��o que o jogado executa quando se relaciona com
 * outro dispositivo movel
 * 
 * @author Danilo Reis
 * 
 */
public class ProximityPlayerListener extends ProximityListener {

	@Override
	public void action(IMobileDevice device1, IMobileDevice device2,
			double distance) {
		this.device1 = device1;
		this.device2 = device2;
		// Verifica se o dispositivo � do time advers�rio
		if (device1.getGroup() != device2.getGroup()) {
			if ((distance > Player.COLISION_DISTANCE)
					&& (distance < Player.VIEW_DISTANCE)) {
				actionVisibleArea(device2);
			} else {
				if (distance < Player.COLISION_DISTANCE) {
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
			msg = "Soldado t� avistando um jagun�o inimigo\n";
			break;
		case Player.DOCTOR:
			msg = "Soldado t� avistando um curandeiro inimigo\n";
			break;
		case Player.ENGINEER:
			msg = "Soldado t� avistando um pedreiro inimigo\n";
			break;
		case Player.SPY:
			msg = "Soldado t� avistando um dedo duro inimigo\n";
			break;
		case Mina.MINA:
			msg = "Soldado t� avistando uma mina inimiga\n";
			break;
		case Barricada.BARRICADA:
			msg = "Soldado t� avistando uma barricada inimiga\n";
			break;
		default:
			msg = "Soldado t� avistando um objeto n�o identificado inimigo\n";
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
			msg = "Soldado t� em riba de um jagun�o inimigo\n";
			break;
		case Player.DOCTOR:
			msg = "Soldado t� em riba de um curandeiro inimigo\n";
			break;
		case Player.ENGINEER:
			msg = "Soldado t� em riba de um pedreiro inimigo\n";
			break;
		case Player.SPY:
			msg = "Soldado t� em riba de um dedo duro inimigo\n";
			break;
		case Mina.MINA:
			explodirMina((Player) device1, (Mina) device);
			msg = "Soldado t� em riba de uma mina inimiga. Explodiu!\n";
			break;
		case Barricada.BARRICADA:
			((Player)device1).setEscondido(true);
			msg = "Soldado t� em riba de uma barricada. T� escondidim!\n";
			break;
		default:
			msg = "Soldado t� em riba de um objeto n�o identificado\n";
			break;
		}

		// Adiciona a mensagem ao gameStory
		logMessage(msg);
	}

	private void explodirMina(Player player, Mina mina) {
		// atualiza a vida do jogador e atualiza o status se ele morrer
		if (player.updateLife(-mina.getDamage())) {
			msg = "Soldado: " + player.getNome() + " perdeu "
					+ mina.getDamage()
					+ " pontos de vida\nSoldado continua vivo\n";
		} else {
			msg = "Soldado: " + player.getNome() + " perdeu "
					+ mina.getDamage()
					+ " pontos de vida\nSoldado est� morto.\n";
		}

		logMessage(msg);
	}
}
