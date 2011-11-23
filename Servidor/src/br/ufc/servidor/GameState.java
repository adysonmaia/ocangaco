package br.ufc.servidor;

import java.util.ArrayList;

import br.ufc.servidor.player.Player;

/**
 * Esta classe é a interface para a interação com o estado do jogo
 * Aqui estão os métodos que registram um jogador, recebe uma alteração de posição etc
 * @author danielm
 *
 */
public interface GameState {
	
	
	/**
	 * Inicia o banco de dados do servidor
	 * Deve ser chamada ao iniciar o processo servidor
	 */
	public void iniciaBanco();
	
	/**
	 * Adiciona um jogador ao jogo
	 * 
	 * @param player jogador a ser adicionado
	 * @return 1 caso o jogador tenha sido adicionado com sucesso ou -1 em caso de falha - já existe jogador com este nome
	 */
	public int connectPlayer(Player player);

	/**
	 * Desconecta um jogador do jogo
	 * 
	 * @param player Jogador a ser desconectado
	 * @return 1 caso jogador tenha sido removido e -1 em caso de falha - jogador não existe
	 */
	public int disconnectPlayer(Player player);
	
	/**
	 * Desconecta um jogador do jogo pelo nome
	 * 
	 * @param name nome do jogador a ser desconectado
	 * @return 1 caso jogador tenha sido removido e -1 em caso de falha - jogador não existe
	 */
	public int disconnectPlayer(String name);
	
	/**
	 * Adiciona ou atualiza coordenadas do jogador<br>
	 * Essa semântica foi criada pelo Rafael
	 * 
	 * @param player Jogador a ser incluído ou atualizado
	 * @return 1 caso atualização ou adição tenha ocorrido com sucesso e -1 em caso de falha
	 */
	public int updateOrConnectPlayer(Player player);
	
	/**
	 * Altera coordenadas de um jogador <br>
	 * @param player Jogador
	 * @param latitude nova Latitude
	 * @param longitude nova longitude
	 */
	public void alteraCoordenadas(Player player, double latitude, double longitude);
	
	/**
	 * Retorma uma lista com todos os jogadores do time passado como parâmetro
	 * @param time Parâmetro que determina o tipo do jogador
	 * @return returna uma List<Player> dos jogadores do time enviado como parâmetro ou null em caso de erro
	 */
	public ArrayList<Player> getPlayerListByType(int tipo);
	
	/**
	 * Retorma uma lista com todos os jogadores do jogo
	 * @return returna uma List<Player> de todos os jogadores do jogo ou null em caso de erro
	 */
	public ArrayList<Player> getPlayerList();
	
	

}
