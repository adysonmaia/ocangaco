package br.ufc.servidor;

import br.ufc.servidor.gamestate.GameStateImp;
import br.ufc.servidor.player.Player;
import myserver.kernel.ServerService;

public class Servidor {
	public static GameState gs;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * Cria um gerenciador do estado do jogo
		 */
		gs = new GameStateImp();
		
		/**
		 * Inicia o banco de dados do jogo
		 */
		gs.iniciaBanco();
		
		/**
		 * Inicia o serviço de Comunicação
		 */
		ServerService server = new ServerService(8080, 1024, false);
		server.start();
		System.out.println("Inicializando servidor do jogo");
		
		
		// Dados de exemplo
		Player p = new Player("Zé",1);
		gs.addPlayer(p);
		p = new Player("Antoin",2);
		gs.addPlayer(p);
		System.out.println("Primeiro jogador: "+gs.listaJogadores().get(0).getNome());
		System.out.println("Primeiro jogador do time 2: "+gs.listaJogadoresTipo(2).get(0).getNome());
				

	}

}