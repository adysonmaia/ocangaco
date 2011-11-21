package br.ufc.servidor.gamestate;

import java.util.ArrayList;

import br.ufc.servidor.GameState;
import br.ufc.servidor.db.DB;
import br.ufc.servidor.player.Player;

public class GameStateImp implements GameState {

	@Override
	public int addPlayer(Player player) {
		return DB.addPlayer(player);
	}

	@Override
	public void alteraCoordenadas(Player player, double latitude,
			double longitude) {
		
	}

	@Override
	public void iniciaBanco() {
		try {
			DB.startDB();
			System.out.println("Banco iniciado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Player> listaJogadoresTipo(int tipo) {
		ArrayList<Player> list = null;
		try {
			list = DB.getPlayersFromTeam(tipo);
			/**
			for(int size = 0; size < l.size(); size++){
				System.out.println("Player: " + l.get(size).getNome());
			}
			*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Player> listaJogadores() {
		ArrayList<Player> list = null;
		try {
			list = DB.getPlayers();
			/*
			for(int size = 0; size < list.size(); size++){
				System.out.println("Player: " + list.get(size).getNome());
			}
			*/
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

}
