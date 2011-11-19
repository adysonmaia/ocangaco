package br.ufc.net;

import br.ufc.model.Player;

public class ServerImpl implements IServer{

	// TODO : Atualizar o SERVER_IP com o IP adequado para teste
	public static final String SERVER_IP = "localhost";
	public static final int PORT = 8080;
	
	/**
	 * Construtor com visibilidade de pacote para tornar obrigatória a instanciação desse Server
	 * por meio de ServerFactory
	 */
	ServerImpl() {
	}
	
	@Override
	public void connect(Player player) {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeConnection(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updatePlayersLocation(Player player) {
		String comando;
		
		comando = "<movimentacao>, " + 
				player.getNome() + ", " + player.getLatitude() + ", "+ player.getLongitude() + 
				  ",<movimentacao>";
		
		try {
			Conexao.executaComando(comando, SERVER_IP, PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
