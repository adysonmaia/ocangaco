package br.ufc.net;

import android.location.Location;
import br.ufc.model.Player;

public class ServerImpl implements IServer{

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
	public void updatePlayersLocation(Location location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeConnection(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
