package br.ufc.sensor;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import br.ufc.activity.R;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.net.ServerFactory;
import br.ufc.util.Constants;
import br.ufc.util.LocationOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GameController {
	private MapView mapView;
	private MapController mapController;	

	private GpsSensor gpsSensor;	
	
	private Location lastKnownLocation;	
	private GeoPoint lastUserGeoPoint;

	private Thread gameThread;
	
	private Resources resources; //Usado para capturar as imagens locais
	
	public GameController(MapView mapa, Context context) {
		initiateObjects(mapa, context.getResources());
		
		/*
		 *  BLOCO USADO PARA FINS DE TESTE. Variável "Eu" deve ser instanciada
		 *  a partir de uma conexão com o server.
		 */
		{ // TODO : instanciar o player  apartir de uma conexão com o server
			if(ClientGameState.eu == null) 
				ClientGameState.eu = new Player("Fulano");
//				ClientGameState.eu = new Player("Sicrano");
		}

		gpsSensor = new GpsSensor(context);
		gpsSensor.setGameController(this);
		gpsSensor.start();
		
		setMapConfigurations(true, false);
	}

	private void initiateObjects(MapView mapView, Resources resources) {
		this.resources = resources;
		this.mapView = mapView;
		this.mapController = mapView.getController();		
	}

	private void setMapConfigurations(boolean sattelite, boolean streetView) {
		mapView.setSatellite(true);
		mapView.setStreetView(false);		
	}
	
	public void start() {
		
		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					 // Atualiza estado do jogo
					ClientGameState.updateState();
					
					// Atualiza o mapa
					handler.sendMessage(handler.obtainMessage());
					
					try {
						Thread.sleep(3000);				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		gameThread.start();
	}

	// Handler utilizado para atualizar o mapa sem gerar exceção de Threads
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	updateMap();
        	mapView.invalidate();
        }
    };
	
	public Resources getResources() {
		return resources;
	}

	public void setLastKnownLocation(Location lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
		
		ClientGameState.eu.setLatitude(lastKnownLocation.getLatitude());				
		ClientGameState.eu.setLongitude(lastKnownLocation.getLongitude());
		
		//ClientGameState.eu.setLatitude(lastKnownLocation.getLatitude() + Constants.LOCATION_INCREMENT);
		
		//Atualiza posição do jogador no servidor. 
		ServerFactory.getServer().updatePlayerLocation(ClientGameState.eu);
		
		// Posiciona o usuario em sua posiÃ§Ã£o atual no mapa
		lastUserGeoPoint = ClientGameState.eu.createLocationGeoPoint();
	}
	
	private void updateMap() {		
		ArrayList<GeoPoint> geoPoints = createPlayersGeoPoints();
		ArrayList<Drawable> images = createImages();

		LocationOverlay myOverlay = new LocationOverlay(getResources().getDrawable(R.drawable.icon_cangaceiro_eu));
		
		myOverlay.setItems(geoPoints, images);
		getMapa().getOverlays().clear();
        getMapa().getOverlays().add(myOverlay);
	}
	
	public MapController getMapController() {
		return mapController;
	}
	public MapView getMapa() {
		return mapView;
	}

	public void setMapa(MapView mapa) {
		this.mapView = mapa;
	}

	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}
	
	private ArrayList<Drawable> createImages() {
		ArrayList<Drawable> images = new ArrayList<Drawable>();

		for (Player p : ClientGameState.players.values()) {
			
			// Adiciona o icone do player
			if(ClientGameState.eu != null && p.getNome().equals(ClientGameState.eu.getNome())) {
				if(ClientGameState.eu.getTipo() == Player.CANGACEIRO)
					images.add(getResources().getDrawable(R.drawable.icon_cangaceiro_eu));
				else
					images.add(getResources().getDrawable(R.drawable.icon_jagunco_eu));
			}
			
			// Adiciona o icone dos players restantes
			if(p.getTipo() == Player.CANGACEIRO)
				images.add(getResources().getDrawable(R.drawable.icon_cangaceiro_time));
			else
				images.add(getResources().getDrawable(R.drawable.icon_jagunco_time));
		}
		
		return images;
	}
	
	private ArrayList<GeoPoint> createPlayersGeoPoints() {
		ArrayList<GeoPoint> geoPoints = new ArrayList<GeoPoint>();
		
		// Adiciona os players em seus devidos locais. OBS: player do jogador está incluso
		for (Player player : ClientGameState.players.values()) {
			geoPoints.add(player.createLocationGeoPoint());
		}
		
		return geoPoints;
	}
}
