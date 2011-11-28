package br.ufc.business.commands;

import java.util.ArrayList;
import java.util.Iterator;

import myserver.kernel.CommandExecute;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.geoengine.DevicesPositionControl;

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
		StringBuffer                      resp;

		resp = new StringBuffer();
		iterator = control.getDevices().iterator();
		// percorre a lista de dispositivos inimigos que estao visiveis
		resp.append("<devices>");
		resp.append('\n');
		while(iterator.hasNext()){
			device  = iterator.next();
			resp.append(device.toXML());
			resp.append('\n');
		}
		resp.append("</devices>");
		resp.append('\n');
		return resp.toString();
	}
}
