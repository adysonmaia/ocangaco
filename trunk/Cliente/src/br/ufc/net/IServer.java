package br.ufc.net;

import java.util.ArrayList;

import br.ufc.model.Barrier;
import br.ufc.model.Mine;
import br.ufc.model.Player;

/**
 * @author Andre
 * Interface de comunica��o com o Servidor.
 */
public interface IServer {

	/**
	 * M�todo que um jogador utiliza para se connectar em um jogo.
	 * @param player com as informa��es do novo jogador.
	 * @return inteiro com o id do player
	 */
	public int connect(Player player);
	
	
	/**
	 * M�todo usado para desvincular um jogador de um jogo.
	 * @param player
	 */
	public void closeConnection(Player player);
	
	/**
	 * M�todo que atualiza a posi��o de um jogador j� registrado no servidor.
	 * @param player contento a nova localiza��o do jogador
	 */
	public void updatePlayerLocation(Player player);
	
	/**
	 * M�todo que retorna uma lista com todos os objetos do mapa.
	 */
	public ArrayList<Player> getGameState(Player player);	
	
	/**
	 * M�todo utilizado para informar se o jogador j� est� connectado ao servidor
	 * @return
	 */
	public boolean isConnected();


	public int createMine(Mine mine);


	public int createBarrier(Barrier barrier);
}
