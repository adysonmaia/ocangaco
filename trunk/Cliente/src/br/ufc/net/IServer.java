package br.ufc.net;

import br.ufc.model.Player;

/**
 * @author Andre
 * Interface de comunica��o com o Servidor.
 */
public interface IServer {

	/**
	 * M�todo que um jogador utiliza para se connectar em um jogo.
	 * @param player com as informa��es do novo jogador.
	 */
	public void connect(Player player);
	
	
	/**
	 * M�todo usado para desvincular um jogador de um jogo.
	 * @param player
	 */
	public void closeConnection(Player player);
	
	/**
	 * M�todo que atualiza a posi��o de um jogador j� registrado no servidor.
	 * @param player contento a nova localiza��o do jogador
	 */
	public void updatePlayersLocation(Player player);
	
	
	/**
	 * M�todo utilizado para informar se o jogador j� est� connectado ao servidor
	 * @return
	 */
	public boolean isConnected();
}