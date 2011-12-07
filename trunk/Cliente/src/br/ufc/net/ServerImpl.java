package br.ufc.net;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufc.model.Barrier;
import br.ufc.model.MapObject;
import br.ufc.model.Mine;
import br.ufc.model.Player;
import br.ufc.util.CommandUtil;
import br.ufc.util.Properties;
import br.ufc.util.XMLParser;

public class ServerImpl implements IServer{
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
//	    System.out.println("player " + player.getNome() + " registered." + 
//	    		" Response content = " + response);
		
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("id");
        Node id = nodes.item(0);
        int playerId = Integer.parseInt(id.getFirstChild().getNodeValue());
        
        if (id != null) {
//			System.out.println("Id: " + playerId);        
		}
        
        player.setId(playerId);
        return 1;
	}

	@Override
	public void closeConnection(Player player) {
		String comando = "<disconnect>," +	player.getId() + ",<disconnect>";	
		
		try {
			response = Conexao.executaComando(comando, Properties.SERVER_IP, Properties.SERVER_PORT);
//			System.out.println("Response: " + response));		   
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
//        System.out.println("Response content: " + response);
	}
	
	@Override
	public ArrayList<MapObject> getGameState(Player player) {
		params[0] = String.valueOf(player.getId());
		
		comando = CommandUtil.makeCommand("visibledevices", params, 3);
		
		response = getServerResponse(comando);
//        System.out.println("List of devices content: \n" + response);
        
        ArrayList<MapObject> devices = new ArrayList<MapObject>();
        
        devices.addAll(XMLParser.getDevices("player", response, new Player()));
        devices.addAll(XMLParser.getDevices("mina", response, new Mine()));
        devices.addAll(XMLParser.getDevices("barricada", response, new Barrier()));
        
        return devices;
	}
	
	
	
	@Override
	public int createMine(Mine mine) {
		params = new String [10];
		
		params[0] = String.valueOf(mine.getTipo());		
		params[1] = String.valueOf(mine.getLatitude());
		params[2] = String.valueOf(mine.getLongitude());
		params[3] = String.valueOf(mine.getDamage());
		
		comando = CommandUtil.makeCommand("criarmina", params, 4);

		response = getServerResponse(comando);
//	    System.out.println("mina id = " + response);
		
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("id");
        Node id = (Element)nodes.item(0);
        
        Integer mineId = 0;
        
        if (id != null) {
        	mineId = Integer.parseInt(id.getFirstChild().getNodeValue());
        }
        
		//System.out.println("id mina: " + mineId);
        
        mine.setId(mineId);
        
        return mineId;
	}
	
	@Override
	public int createBarrier(Barrier barrier) {
		params = new String [10];
		
		params[0] = String.valueOf(barrier.getTipo());		
		params[1] = String.valueOf(barrier.getLatitude());
		params[2] = String.valueOf(barrier.getLongitude());
		
		comando = CommandUtil.makeCommand("criarbarricada", params, 3);

		String response = getServerResponse(comando);
//	    System.out.println("barricada id = " + response);
		
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName("id");
        Node id = (Element)nodes.item(0);
        
        Integer barrierId = 0;        
        if (id != null) {
        	barrierId = Integer.parseInt(id.getFirstChild().getNodeValue());
        }
        
//		System.out.println("id barricada: " + barrierId);
        
        barrier.setId(barrierId);
        
        return barrierId;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static String getServerResponse(String comando) {
		try {
			return Conexao.executaComando(comando, Properties.SERVER_IP, Properties.SERVER_PORT);
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
