package br.ufc.location.test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.servidor.player.Player;
import br.ufc.util.Conexao;
import br.ufc.util.EntityParser;
import br.ufc.util.XMLParser;

public class LocationClientTest {
	static String comando;
	static String [] params;
	static Document doc;
	static NodeList nodes;
	static String response;
	
	public static void main(String[] args) throws IOException {
		//testMovimentacao();		
		//testGameState();
		
		//testRegister();
		testUpdatePosition();
		//testDevicesList();
		//testDisconnect();
	}

	private static void testDevicesList() {
		comando = makeCommand("deviceslist", params, 0);
		
		response = getServerResponse(comando);
        System.out.println("List of devices: \n" + response);
        
        
        doc = XMLParser.createXMLDocument(response);
        nodes = doc.getElementsByTagName("player");
        for (int i = 0; i < nodes.getLength(); i++) {
        	Player player = new Player("teste");
        	try {
				player.fromXML((Element)nodes.item(i));
				System.out.println(player);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}

	private static void testUpdatePosition() {
		params = new String [10];
		
		params[0] ="Danilo";
		params[1] ="1";
		params[2] ="1";
		params[3] ="-3.717381";
		params[4] ="38.539906";
		
		comando = makeCommand("register", params, 5);
		
		Document doc = XMLParser.createXMLDocument(getServerResponse(comando));
        NodeList nodes = doc.getElementsByTagName("id");
        Element node = (Element)nodes.item(0);
        
        if (node != null) {
			System.out.println("id 1: " + node.getTextContent());
        }
		
		params[0] ="Fabio";
		params[1] ="1";
		params[2] ="1";
        params[3] ="-3.717123";
        params[4] ="38.540081";
        
        comando = makeCommand("register", params, 5);
        
        String response = getServerResponse(comando);
        
        doc = XMLParser.createXMLDocument(response);
        nodes = doc.getElementsByTagName("id");
        node = (Element)nodes.item(0);
        
        if (node != null) {
			System.out.println("id 2: " + node.getTextContent());
        }
        
		params[0] = node.getTextContent();
		params[1] = String.valueOf(-3.717381 + 0.0001);
		params[2] = "38.539906";
		
		comando = makeCommand("updateposition", params, 5);
		
		response = getServerResponse(comando);
        System.out.println("List of devices: \n" + response);
        
        
        doc = XMLParser.createXMLDocument(response);
        nodes = doc.getElementsByTagName("player");
        for (int i = 0; i < nodes.getLength(); i++) {
        	Player player = new Player("teste");
        	try {
				player.fromXML((Element)nodes.item(i));
				System.out.println(player);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}		
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

		String response = getServerResponse(comando);
	    System.out.println("player 1 id = " + response);
		
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("id");
        Element id = (Element)nodes.item(0);
        
        if (id != null) {
			System.out.println("id 1: " + id.getTextContent());
        }
        
		params[0] ="Fabio";
		params[1] ="1";
		params[2] ="1";
        params[3] ="-3.717123";
        params[4] ="38.540081";
        
        comando = makeCommand("register", params, 5);
        
        response = getServerResponse(comando);
        System.out.println("player 2 id = " + response);
        
        doc = XMLParser.createXMLDocument(response);
        nodes = doc.getElementsByTagName("id");
        id = (Element)nodes.item(0);
        
        if (id != null) {
			System.out.println("id 2: " + id.getTextContent());
        }
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
