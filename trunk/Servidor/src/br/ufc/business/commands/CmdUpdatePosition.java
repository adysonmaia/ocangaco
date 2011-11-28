package br.ufc.business.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import myserver.kernel.CommandExecute;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;

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
		String                    resp;
		IMobileDevice           device;
		
		id        = param[0];
		latitude  = Double.parseDouble(param[1]);
		longitude = Double.parseDouble(param[2]);		
		
		deviceId = new Integer(id);
		
		control = DevicesPositionControl.getInstance();

		// Busca o dispositivo
		device            = control.searchMobileDeviceById(deviceId);
		clientDevicesView = new ArrayList<IMobileDevice>();
		
		now = new Date();
		pos = new GeoPosition(now,latitude,longitude);
		
		// atualiza a posicao do objeto no controlador
		control.updateDevicePosition(deviceId, pos);

		// adiciona na lista os dispositivos do mesmo time
		addToListFriendDevices(device);
		// adiciona na lista os dispositivos do time oposto
		addToListVisibleInimyDevices(device);
		
		// Retorna o identificador do gerenciador de geoposicionamento
		resp =  getVisibleDevicesList();
		
		
		return resp;
	}

	private String getVisibleDevicesList() {
		Iterator<IMobileDevice>       iterator;
		IMobileDevice                   device;
		StringBuffer                      resp;

		resp = new StringBuffer();
		iterator = clientDevicesView.iterator();
		// percorre a lista de dispositivos inimigos que estao visiveis
		resp.append("<visibledevices>");
		resp.append('\n');
		while(iterator.hasNext()){
			device  = iterator.next();
			resp.append(device.toXML());
			resp.append('\n');
		}
		resp.append("</visibledevices>");
		resp.append('\n');
		return resp.toString();
	}

	private void addToListVisibleInimyDevices(IMobileDevice device) {
		Iterator<IMobileDevice>       iterator;
		IMobileDevice                      dev;
		List<IMobileDevice>     listTotal,list;
		int                            inimyId;

		// Descobre o id do time inimigo
		inimyId =( device.getGroup()==1)?2:1;

		listTotal = control.getDevicesByGroup(inimyId);
		list      = control.getVisibleDeviceList(device, listTotal);
		iterator = list.iterator();
		// percorre a lista de dispositivos inimigos que estao visiveis
		while(iterator.hasNext()){
			dev      = iterator.next();
			clientDevicesView.add(dev);
		}
	}

	private void addToListFriendDevices(IMobileDevice device) {
		Iterator<IMobileDevice>       iterator;
		IMobileDevice                      dev;
		List<IMobileDevice>               list;
		int                           friendId;

		// cria uma lista com todos os dispositivos
		friendId  = device.getGroup();
		list      = control.getDevicesByGroup(friendId);
		iterator = list.iterator();
		// percorre a lista de dispositivos registrados
		while(iterator.hasNext()){
			dev      = iterator.next();
			// caso o dispositivo nao seja ele proprio
			if( !(dev.getId().equals(device.getId())) ){
				clientDevicesView.add(dev);
			}
		}
	}
}
