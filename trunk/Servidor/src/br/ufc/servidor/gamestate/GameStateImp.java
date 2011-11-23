package br.ufc.servidor.gamestate;

import java.util.ArrayList;

import br.ufc.servidor.GameState;
import br.ufc.servidor.db.DB;
import br.ufc.servidor.player.Player;

public class GameStateImp implements GameState {
	
	//variaveis para contar o numero de jogadores de cada time de forma a distribuir
	//uniformemente os jogadores com preferencia para cangaceiros
	private int cangaceiros = 0;
	private int jaguncos = 0;

	@Override
	public int connectPlayer(Player player) {
		int ret = 0;
		System.out.println("Recebi mensagem de connectPlayer");
		if(jaguncos >= cangaceiros)
		{
			player.setTipo(1);
			cangaceiros++;
			
			// Se não conseguir incluir, deve desfazer a alteração no contador
			ret = DB.addPlayer(player);
			if(ret == -1){
				cangaceiros--;
			}
		}
		else
		{
			player.setTipo(2);
			jaguncos++;
			
			// Se não conseguir incluir, deve desfazer a alteração no contador
			ret = DB.addPlayer(player);
			if(ret == -1){
				jaguncos--;
			}
		}
		
		
		return ret;
			
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
	public ArrayList<Player> getPlayerListByType(int tipo) {
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
	public ArrayList<Player> getPlayerList() {
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

	@Override
	public int disconnectPlayer(Player player) {
		return DB.delPlayer(player.getNome());
		
	}

	@Override
	public int disconnectPlayer(String name) {
		return DB.delPlayer(name);
	}

	@Override
	public int updateOrConnectPlayer(Player player) {
		int ret = 0;
		System.out.println("Recebi mensagem de updateOrConnectPlayer");
		// Se não é uma atualização, é uma inserção
		if(DB.updatePlayer(player)!=1){
			if(jaguncos >= cangaceiros)
			{
				player.setTipo(1);
				cangaceiros++;
				
				// Se não conseguir incluir, deve desfazer a alteração no contador
				ret = DB.addPlayer(player);
				if(ret == -1){
					cangaceiros--;
				}
			}
			else
			{
				player.setTipo(2);
				jaguncos++;
				
				// Se não conseguir incluir, deve desfazer a alteração no contador
				ret = DB.addPlayer(player);
				if(ret == -1){
					jaguncos--;
				}
			}
			
		}
		
		return ret;
	}

	@Override
	public ArrayList<Player> getPlayerListByType(Player player) {
		ArrayList<Player> list = null;
		try {
			list = DB.getPlayersFromTeam(player.getTipo());
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
	
	@Override
	public ArrayList<Player> getPlayerListByType(String player) {
		ArrayList<Player> list = null;
		try {
			Player p = DB.getDataFromPlayer(player);
			list = DB.getPlayersFromTeam(p.getTipo());
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
