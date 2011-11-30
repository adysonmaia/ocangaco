package br.ufc.servidor.gamestate;

import java.util.ArrayList;
import br.ufc.servidor.GameState;
import br.ufc.servidor.player.Player;

public class GameStateImp implements GameState {
	
	private ArrayList<Player> players = null;
	
	private static GameStateImp instance = null;
	
	public GameStateImp(){
		players = new ArrayList<Player>();
	}

	public static GameStateImp getInstance() {
		if (instance == null)
			instance = new GameStateImp();

		return instance;
	}

	@Override
	public int connectPlayer(Player player) {
		int ret = 0;
		if (players != null && players.size() > 0) {
			for (Player p : players) {
				if (player.getNome().equals(p.getNome())) {
					ret = -1;				
				}
			}
		}
		
		if(ret != -1 ){
			players.add(player);
			ret = 1;
		}
			
		return ret;
			
	}

	@Override
	public int update(Player player) {
		if (players != null && players.size() > 0) {
			for (Player p : players) {
				if (player.getNome().equals(p.getNome())) {
					p.setLatitude(player.getLatitude());
					p.setLongitude(player.getLongitude());					
					return 1;
				}
			}
		}
		players.add(player);
		return 1;
	}

	@Override
	public ArrayList<Player> getPlayerListByType(int tipo) {
		ArrayList<Player> playersByTipo = new ArrayList<Player>();

		if (players != null && players.size() > 0) {
			for (Player player : players) {
				if (player.getTipo() == tipo) {
					playersByTipo.add(player);
				}
			}
		}

		return playersByTipo;
	}

	@Override
	public ArrayList<Player> getPlayerList() {
		ArrayList<Player> list = new ArrayList<Player>();
		for(Player p: players)
			list.add(p);
		return list;
	}

	@Override
	public int disconnectPlayer(Player player) {
		if(players != null && players.size() > 0)
		{
			for (Player p : players) {
				if(player.getNome().equals(p.getNome()))
				{
					players.remove(player);
					return 1;
				}				
			}			
		}
		
		return -1;
		
	}

	@Override
	public int disconnectPlayer(String name) {
		if(players != null && players.size() > 0)
		{
			for (Player p : players) {
				if(p.getNome().equals(name))
				{
					players.remove(p);
					return 1;
				}				
			}			
		}
		
		return -1;
	}


	@Override
	public ArrayList<Player> getPlayerListByType(Player player) {
		ArrayList<Player> playersByTipo = new ArrayList<Player>();

		if (players != null && players.size() > 0) {
			for (Player p : players) {
				if (player.getTipo() == player.getTipo()) {
					playersByTipo.add(player);
				}
			}
		}

		return playersByTipo;
	}
	
	@Override
	public ArrayList<Player> getPlayerListByType(String player) {
		ArrayList<Player> playersByTipo = new ArrayList<Player>();

		Player p1 = null;
		if (players != null && players.size() > 0) {
			for (Player p: players){
				if(p.getNome().equals(player))
					p1=p;
			}
		}
		
		if (players != null && players.size() > 0) {
			for (Player p : players) {
				if (p.getTipo() == p1.getTipo()) {
					playersByTipo.add(p);
				}
			}
		}

		return playersByTipo;
	}

}
