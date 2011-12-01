package br.ufc.business.commands;

import java.util.Date;

import myserver.kernel.CommandExecute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.servidor.gamestory.GameStoryControl;
import br.ufc.servidor.gamestory.Message;
import br.ufc.servidor.player.Player;
import br.ufc.util.XMLParser;

public class CmdRegister extends CommandExecute {

	public CmdRegister() {
	}

	@Override
	public String execute(String[] param) {
		String                    nome;
		double                latitude;
		double               longitude;
		int                    t,group;
		Player                  player;
		GeoPosition                pos;
		Date                       now;
		DevicesPositionControl control;
		Integer                 freeId;		
		
		// Nome do jogador
		nome      = param[0];
		// tipo do jogador
		t         = Integer.parseInt(param[1]);
		// time do jogador
		group     = Integer.parseInt(param[2]);
		// latitude corrente do jogador
		latitude  = Double.parseDouble(param[3]);
		// longitude corrente do jogador
		longitude = Double.parseDouble(param[4]);		
		
		control = DevicesPositionControl.getInstance();
		
		player    = new Player(nome, t,group, latitude, longitude);
		
		freeId = DevicesPositionControl.getNextFreeDeviceId();
		
		player.setId(freeId);
		
		now = new Date();
		pos = new GeoPosition(now,latitude,longitude);
		// seta a posiçao corrente;
		player.setGeoPosition(pos);
		
		GameStoryControl storyControl = GameStoryControl.getInstance();
		Message message = new Message("Soldado " + player.getNome() + " entrou no jogo", player);
		storyControl.addMessage(message);
		
		// adiciona o dispositivo para ser controlado
		control.addDevice(player);
				
		String response = toXml(freeId); 
		
		return response;
	}

	private String toXml(Integer freeId) {
		Document doc = XMLParser.createXMLDocument();
		if (doc != null) {
			Element response = doc.createElement("response");
			doc.appendChild(response);
	 
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(freeId)));
			response.appendChild(id);
		}
		String response = XMLParser.getXMLString(doc); 
		return response;
	}
}
