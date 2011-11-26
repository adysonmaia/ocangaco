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
		int                    t,group;
		Player                  player;
		GeoPosition                pos;
		Date                       now;
		DevicesPositionControl control;
		Integer                 freeId;
		String                    resp;
		
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
		
		// adiciona o dispositivo para ser controlado
		control.addDevice(player);
		
		// Retorna o identificador do gerenciador de geoposicionamento
		resp = String.valueOf(freeId);
		
		return resp;
	}

}
