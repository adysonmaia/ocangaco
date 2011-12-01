package br.ufc.business.commands;

import java.util.Date;

import myserver.kernel.CommandExecute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.servidor.artefatos.Mina;
import br.ufc.util.XMLParser;

public class CmdCriarMina extends CommandExecute {

	public CmdCriarMina() {
	}

	@Override
	public String execute(String[] param) {
		double latitude;
		double longitude;
		int group;
		int damage;
		Mina mina;
		GeoPosition pos;
		Date now;
		DevicesPositionControl control;
		Integer freeId;
		
		// time a qual a mina pertence
		group = Integer.parseInt(param[0]);
		// latitude da mina
		latitude = Double.parseDouble(param[1]);
		// longitude da mina
		longitude = Double.parseDouble(param[2]);
		// dano que a mina tira
		damage = Integer.parseInt(param[3]);

		control = DevicesPositionControl.getInstance();

		mina = new Mina(group, latitude, longitude, damage);

		freeId = DevicesPositionControl.getNextFreeDeviceId();

		mina.setId(freeId);

		now = new Date();
		pos = new GeoPosition(now, latitude, longitude);
		// seta a posiçao corrente;
		mina.setGeoPosition(pos);

		// adiciona o dispositivo para ser controlado
		control.addDevice(mina);

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
