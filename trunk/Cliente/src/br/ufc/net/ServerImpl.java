package br.ufc.net;

import java.util.ArrayList;

import br.ufc.model.Player;
import br.ufc.util.EntityParser;

public class ServerImpl implements IServer{

	// TODO : Atualizar o SERVER_IP com o IP adequado para teste
	public static final String SERVER_IP = "10.0.2.2";
	public static final int PORT = 8080;
	
	/**
	 * Construtor com visibilidade de pacote para tornar obrigatória a instanciação desse Server
	 * por meio de ServerFactory
	 */
	ServerImpl() {
	}
	
	@Override
	public int connect(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void closeConnection(Player player) {
		// TODO Auto-generated method stub
		 
	}
	
	@Override
	public void updatePlayerLocation(Player player) {
		String comando;
		
		comando = "<movimentacao>," + 
				player.getNome() + "," + player.getTipo() + "," + player.getLatitude() + ","+ player.getLongitude() + 
				  ",<movimentacao>";		
		try {
			System.out.println(Conexao.executaComando(comando, SERVER_IP, PORT));
		} catch (Exception e) {
			System.out.println("Error updating player position on server. " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Player> getGameState() {
		String comando = "<gamestate>, " +	"" + ",<gamestate>";
		
		try {
			ArrayList<Player> playerList = 
				EntityParser.parseMessageToPlayerList(Conexao.executaComando(comando, SERVER_IP, PORT));					
			return playerList;			 
		} catch (Exception e) {
			System.out.println("Error getting game state on server. " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
