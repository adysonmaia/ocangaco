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
	 * @return id do jogador adicionado
	 */
	public int addPlayer(Player player);
	
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
	public ArrayList<Player> listaJogadoresTipo(int tipo);
	
	/**
	 * Retorma uma lista com todos os jogadores do jogo
	 * @return returna uma List<Player> de todos os jogadores do jogo ou null em caso de erro
	 */
	public ArrayList<Player> listaJogadores();
	
	

}
