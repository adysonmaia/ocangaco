package br.ufc.business.commands;

import java.util.ArrayList;
import java.util.Iterator;

import myserver.kernel.CommandExecute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.util.XMLParser;

public class CmdGetDevicesList extends CommandExecute {
	ArrayList<IMobileDevice> clientDevicesView;
	DevicesPositionControl control;

	public CmdGetDevicesList() {
	}

	@Override
	public String execute(String[] param) {		
		String                    resp;				
		control = DevicesPositionControl.getInstance();
	
		// Retorna a lista dos dispositivos
		resp = getDevicesList() ;
		
		return resp;
	}

	private String getDevicesList() {
		Iterator<IMobileDevice>       iterator;
		IMobileDevice                   device;
		
		iterator = control.getDevices().iterator();		
		
		Document doc = XMLParser.createXMLDocument();		
		if (doc != null) {
			Element response = doc.createElement("response");
			doc.appendChild(response);
	 
			Element devices = doc.createElement("devices");
			
			// percorre a lista de dispositivos inimigos que estao visiveis			
			while(iterator.hasNext()){
				device  = iterator.next();
				try {
					device.toXML(devices, doc);					
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
			
			response.appendChild(devices);			
			
			String resp = XMLParser.getXMLString(doc); 
			return resp;	
		}
		
		return null;
	}
}
