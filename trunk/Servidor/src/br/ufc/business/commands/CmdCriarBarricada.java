package br.ufc.business.commands;

import java.util.Date;

import myserver.kernel.CommandExecute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.servidor.artefatos.Barricada;
import br.ufc.servidor.gamestory.GameStoryControl;
import br.ufc.servidor.gamestory.Message;
import br.ufc.util.XMLParser;

public class CmdCriarBarricada extends CommandExecute {

	public CmdCriarBarricada() {
	}

	@Override
	public String execute(String[] param) {
		double latitude;
		double longitude;
		int group;
		
		Barricada barricada;
		GeoPosition pos;
		Date now;
		DevicesPositionControl control;
		Integer freeId;
		
		// time a qual a barricada pertence (isso não necessariamente será útil para o jogo)
		group = Integer.parseInt(param[0]);
		// latitude da barricada
		latitude = Double.parseDouble(param[1]);
		// longitude da barricada
		longitude = Double.parseDouble(param[2]);

		control = DevicesPositionControl.getInstance();

		barricada = new Barricada(group, latitude, longitude);

		freeId = DevicesPositionControl.getNextFreeDeviceId();

		barricada.setId(freeId);

		now = new Date();
		pos = new GeoPosition(now, latitude, longitude);
		// seta a posiçao corrente;
		barricada.setGeoPosition(pos);

		// adiciona o dispositivo para ser controlado
		control.addDevice(barricada);
		
		GameStoryControl storyControl = GameStoryControl.getInstance();
		Message message = new Message("Uma barricada foi criada", barricada);
		storyControl.addMessage(message);

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
