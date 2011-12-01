package br.ufc.net;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufc.model.Mine;
import br.ufc.model.Player;
import br.ufc.util.CommandUtil;
import br.ufc.util.Properties;
import br.ufc.util.XMLParser;

public class ServerImpl implements IServer{

	public static final int PORT = 8080;
	String[] params;
	String comando;
	String response;
	
	/**
	 * Construtor com visibilidade de pacote para tornar obrigatória a instanciação desse Server
	 * por meio de ServerFactory
	 */
	ServerImpl() {
	}
	
	@Override
	public int connect(Player player) {
		params = new String [10];
		
		params[0] = player.getNome();
		params[1] = String.valueOf(player.getPapel());
		params[2] = String.valueOf(player.getTipo());
		params[3] = String.valueOf(0);
		params[4] = String.valueOf(0);
		
		comando = CommandUtil.makeCommand("register", params, 5);

		response = getServerResponse(comando);
	    System.out.println("player " + player.getNome() + "registered." + "Id = " + response);
		
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("id");
        Node id = nodes.item(0);
        int playerId = Integer.parseInt(id.getFirstChild().getNodeValue());
        
        if (id != null) {
			System.out.println("Id: " + playerId);        
		}
        
        player.setId(playerId);
        return 1;
	}

	@Override
	public void closeConnection(Player player) {
		String comando = "<disconnect>," +	player.getNome() + ",<disconnect>";	
		
		try {
			System.out.println(Conexao.executaComando(comando, Properties.SERVER_IP, PORT));		   
		} catch (Exception e) {
			System.out.println("Error disconnecting player from server. " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePlayerLocation(Player player) {
		params[0] = String.valueOf(player.getId());
		params[1] = String.valueOf(player.getLatitude());
		params[2] = String.valueOf(player.getLongitude());
		
		comando = CommandUtil.makeCommand("updateposition", params, 3);
		
		response = getServerResponse(comando);
        System.out.println("Response: " + response);
	}
	
	@Override
	public ArrayList<Player> getGameState(Player player) {
		params[0] = String.valueOf(player.getId());
		
		comando = CommandUtil.makeCommand("visibledevices", params, 3);
		
		response = getServerResponse(comando);
        System.out.println("List of devices: \n" + response);
        
        return getPlayers(response);
	}
	
	public ArrayList<Player> getPlayers(String response)
	{
		ArrayList<Player> players = new ArrayList<Player>();
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("player");
        for (int i = 0; i < nodes.getLength(); i++) {        	
        	try {
        		Player player = new Player();
        		player.fromXML((Element)nodes.item(i));
        		players.add(player);        		
				System.out.println(player);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
        
        return players;
	}
	
	
	public ArrayList<Mine> getMinas(String response)
	{
		ArrayList<Mine> minas = new ArrayList<Mine>();
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("mina");
        for (int i = 0; i < nodes.getLength(); i++) {        	
        	try {
        		Mine mina = new Mine();
        		mina.fromXML((Element)nodes.item(i));
        		minas.add(mina);        		
				System.out.println(mina);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
        
        return minas;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static String getServerResponse(String comando) {
		try {
			return Conexao.executaComando(comando, Properties.SERVER_IP, PORT);
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
