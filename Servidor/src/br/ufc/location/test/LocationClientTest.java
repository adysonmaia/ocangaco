package br.ufc.location.test;

import java.io.IOException;
import java.net.UnknownHostException;

import br.ufc.util.Conexao;

public class LocationClientTest {
	public static void main(String[] args) throws IOException {
		String comando = "<soma>," + "a1234e" + ",<soma>";
		System.out.println("Soma de strings = " + getServerResponse(comando));

		String comando2 = "<movimentacao>, " + 
		"playerName" + ", " + "playerLatitude" + ", "+ "playerLongitude" + 
		  ",<movimentacao>";
		System.out.println("Movimentacao = " + getServerResponse(comando2));
		
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
