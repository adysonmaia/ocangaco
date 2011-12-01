package br.ufc.business.commands;

import java.util.ArrayList;
import java.util.Date;

import myserver.kernel.CommandExecute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.util.XMLParser;

public class CmdUpdatePosition extends CommandExecute {
	ArrayList<IMobileDevice> clientDevicesView;
	DevicesPositionControl control;

	public CmdUpdatePosition() {
	}

	@Override
	public String execute(String[] param) {
		String                      id;
		Integer               deviceId;
		double                latitude;
		double               longitude;
		GeoPosition                pos;
		Date                       now;		
		
		id        = param[0];
		latitude  = Double.parseDouble(param[1]);
		longitude = Double.parseDouble(param[2]);		
		
		deviceId = new Integer(id);
		
		control = DevicesPositionControl.getInstance();
		
		clientDevicesView = new ArrayList<IMobileDevice>();
		
		now = new Date();
		pos = new GeoPosition(now,latitude,longitude);
		
		// atualiza a posicao do objeto no controlador
		control.updateDevicePosition(deviceId, pos);	
		
		String response = toXml(1); 
		
		return response;
	}
	
	private String toXml(Integer responseId) {
		Document doc = XMLParser.createXMLDocument();
		if (doc != null) {
			Element response = doc.createElement("response");
			doc.appendChild(response);
	 
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(String.valueOf(responseId)));
			response.appendChild(id);
		}
		String response = XMLParser.getXMLString(doc); 
		return response;
	}
}
