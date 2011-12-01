package br.ufc.net;

import java.util.ArrayList;

import br.ufc.model.Barrier;
import br.ufc.model.Mine;
import br.ufc.model.Player;

/**
 * @author Andre
 * Interface de comunicação com o Servidor.
 */
public interface IServer {

	/**
	 * Método que um jogador utiliza para se connectar em um jogo.
	 * @param player com as informações do novo jogador.
	 * @return inteiro com o id do player
	 */
	public int connect(Player player);
	
	
	/**
	 * Método usado para desvincular um jogador de um jogo.
	 * @param player
	 */
	public void closeConnection(Player player);
	
	/**
	 * Método que atualiza a posição de um jogador já registrado no servidor.
	 * @param player contento a nova localização do jogador
	 */
	public void updatePlayerLocation(Player player);
	
	/**
	 * Método que retorna uma lista com todos os objetos do mapa.
	 */
	public ArrayList<Player> getGameState(Player player);	
	
	/**
	 * Método utilizado para informar se o jogador já está connectado ao servidor
	 * @return
	 */
	public boolean isConnected();


	public int createMine(Mine mine);


	public int createBarrier(Barrier barrier);
}
