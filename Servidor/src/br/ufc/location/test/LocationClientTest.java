package br.ufc.location.test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.servidor.player.Player;
import br.ufc.util.Conexao;
import br.ufc.util.EntityParser;

public class LocationClientTest {
	static String comando;
	static String [] params;	
	
	public static void main(String[] args) throws IOException {
		//testSoma();		
		//testMovimentacao();		
		//testGameState();
		//testDisconnect();
		testRegister();
		//testUpdatePosition();
		testDevicesList();
	}

	private static void testDevicesList() {
		comando = makeCommand("deviceslist", params, 0);
		System.out.println("List of devices: \n" + getServerResponse(comando));
	}

	private static void testUpdatePosition() {
		params = new String [10];
		
		params[0] ="Danilo";
		params[1] ="1";
		params[2] ="1";
		params[3] ="-3.717381";
		params[4] ="38.539906";
		
		comando = makeCommand("register", params, 5);
		int id = Integer.parseInt(getServerResponse(comando));
		
		params[0] ="Fabio";
		params[1] ="1";
		params[2] ="2";
        params[3] ="-3.717123";
        params[4] ="38.540081";
        
        comando = makeCommand("register", params, 5);
        id = Integer.parseInt(getServerResponse(comando));
        
		params[0] = String.valueOf(id);
		params[1] = String.valueOf(-3.717381 + 0.0001);
		params[2] = "38.539906";
		
		comando = makeCommand("updateposition", params, 5);
		System.out.println("List of devices: \n" + getServerResponse(comando));		
	}

	//Registro testado com sucesso com o servidor rodando!
	private static void testRegister() {
		params = new String [10];
		
		params[0] ="Danilo";
		params[1] ="1";
		params[2] ="1";
		params[3] ="-3.717381";
		params[4] ="38.539906";
		
		comando = makeCommand("register", params, 5);
		System.out.println("Player 1 id = " + getServerResponse(comando));
		
		params[0] ="Fabio";
		params[1] ="1";
		params[2] ="1";
        params[3] ="-3.717123";
        params[4] ="38.540081";
        
        comando = makeCommand("register", params, 5);
        System.out.println("player 2 id = " + getServerResponse(comando));
	}
	
	
	private static String makeCommand(String name, String[] params, int nParams)
	{
		comando = "<" + name + ">,";
		for (int i = 0; i < nParams; i++) {
			comando += params[i] + ",";
		}	
		if(nParams == 0){
			comando += ",";
		}
		comando += "<" + name + ">";
		
		return comando;
	}

	private static void testGameState() {
		comando = "<gamestate>, " +	"" + ",<gamestate>";		
		ArrayList<Player> playerList = EntityParser.parseMessageToPlayerList(getServerResponse(comando));		
		for (Player player : playerList) {
			System.out.println(player.toString());
		}
	}

	private static void testMovimentacao() {		
		comando = "<movimentacao>," + 
		"Joao" + "," + "1" + "," + "-3.746573" + "," + "-38.577791" + 
		  ",<movimentacao>";
		System.out.println("Movimentacao = " + getServerResponse(comando));
	}
	
	private static void testDisconnect() {		
		comando = "<disconnect>," +	"Joao" + ",<disconnect>";
		System.out.println("Disconnected: " + getServerResponse(comando));
	}

	private static void testSoma() {
		comando = "<soma>," + "a1234e" + ",<soma>";
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
