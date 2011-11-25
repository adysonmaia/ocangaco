package game;

import geoengine.DevicesPositionControl;
import geoengine.GeoPosition;

import java.util.Date;

public class CmdRegister extends CommandExecute {

	public CmdRegister() {
	}

	@Override
	public String execute(String[] param) {
		String                    nome;
		String                    tipo;
		double                latitude;
		double               longitude;
		int                          t;
		Player                  player;
		GeoPosition                pos;
		Date                       now;
		DevicesPositionControl control;
		Integer                 freeId;
		String                    resp;
		
		nome      = param[0];
		t         = Integer.parseInt(param[1]);
		latitude  = Double.parseDouble(param[2]);
		longitude = Double.parseDouble(param[3]);		
		
		control = DevicesPositionControl.getInstance();

		player    = new Player(nome, t, latitude, longitude);
		freeId = DevicesPositionControl.getNextFreeDeviceId();
		
		player.setId(freeId);
		
		now = new Date();
		pos = new GeoPosition(now,latitude,longitude);
		// seta a posiçao corrente;
		player.setGeoPosition(pos);
		
		// adiciona o dispositivo para ser controlado
		control.addDevice(player);
		
		// Retorna o identificador do gerenciador de geoposicionamento
		resp = String.valueOf(freeId);
		
		return resp;
	}

}
