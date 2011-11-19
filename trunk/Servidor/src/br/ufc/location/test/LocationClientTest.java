package br.ufc.location.test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import br.ufc.servidor.player.Player;
import br.ufc.util.Conexao;
import br.ufc.util.EntityParser;

public class LocationClientTest {
	public static void main(String[] args) throws IOException {
		//testSoma();		
		//testMovimentacao();
		testGameState();
	}

	private static void testGameState() {
		String comando3 = "<gamestate>, " +	"" + ",<gamestate>";		
		ArrayList<Player> playerList = EntityParser.parseMessageToPlayerList(getServerResponse(comando3));		
		for (Player player : playerList) {
			System.out.println(player.toString());
		}
	}

	private static void testMovimentacao() {
		String comando2 = "<movimentacao>, " + 
		"playerName" + ", " + "playerLatitude" + ", "+ "playerLongitude" + 
		  ",<movimentacao>";
		System.out.println("Movimentacao = " + getServerResponse(comando2));
	}

	private static void testSoma() {
		String comando = "<soma>," + "a1234e" + ",<soma>";
		System.out.println("Soma de strings = " + getServerResponse(comando));		
	}

	private static String getServerResponse(String comando) {
		int porta = 8080;
		String ip = "localhost";
		try {
			return Conexao.executaComando(comando, ip, porta);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error connecting server";
	}
}
