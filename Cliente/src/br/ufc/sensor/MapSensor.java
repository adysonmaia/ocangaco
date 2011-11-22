package br.ufc.sensor;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import br.ufc.activity.R;
import br.ufc.model.Player;
import br.ufc.model.ClientGameState;
import br.ufc.net.Conexao;
import br.ufc.net.ServerFactory;
import br.ufc.net.ServerImpl;
import br.ufc.util.Constants;
import br.ufc.util.LocationOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapSensor {
	MapView mapa;
	MapController mapController;	

	private Location lastKnownLocation;	
	GeoPoint lastUserGeoPoint;
	
	
	
	Resources resources; //Usado para capturar as imagens locais
	
	public MapSensor(MapView mapa, Resources resources) {
		initiateObjects(mapa, resources);
		setInitialLocation();
		setMapConfigurations(true, false);
		startGameLoop();
	}

	private void initiateObjects(MapView mapa, Resources resources) {
		this.resources = resources;
		this.mapa = mapa;
		this.mapController = mapa.getController();		
	}

	private void setInitialLocation() {
		// Caso do JP
		double lat = Constants.DEFAULT_LATITUDE; //-3.7307950;// No emulador -3.7307950  
		double log = Constants.DEFAULT_LONGITUDE; //-38.575991;// No emulador -38.575991
		
		Location location = new Location("");
		location.setLatitude(lat);
		location.setLongitude(log);
		
		this.lastUserGeoPoint = new GeoPoint((int)(lat * 1E6), (int)(log * 1E6));	
		setLastKnownLocation(location);	
		
		//Atualiza posição do jogador no servidor. 
		//ServerFactory.getServer().updatePlayerLocation(ClientGameState.eu);
	}	

	private void setMapConfigurations(boolean sattelite, boolean streetView) {
		mapa.setSatellite(true);
		mapa.setStreetView(false);		
	}
	
	private void startGameLoop() {
		// Game Loop do jogo. OBS: Esse é o local ideal para ele?.
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					/*
					 *  Atualizações em uma View não podem ser efetuadas em Threas secundárias.
					 *  A solução para o problema é a utilização de Handlers.
					 */
					handler.sendMessage(handler.obtainMessage());
					
					try {
						Thread.sleep(200);				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// Handler utilizado para atualizar o mapa sem gerar exceção de Threads
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
    		// TODO atualiza as imagem 
        	atualizaPlayers();
        	mapa.invalidate();
        }
    };
	
	public Resources getResources() {
		return resources;
	}

	public void setLastKnownLocation(Location lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
		
		/*
		 *  BLOCO USADO PARA FINS DE TESTE. Variável "Eu" deve ser instanciada
		 *  a partir de uma conexão com o server.
		 */
		{ // TODO : instanciar o player  apartir de uma conexão com o server
			if(ClientGameState.eu == null) 
				//ClientGameState.eu = new Player("Fulano");
				ClientGameState.eu = new Player("Sicrano");
		}

//		System.out.println("LOCA: " + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude());
		
		ClientGameState.eu.setLatitude(lastKnownLocation.getLatitude() + Constants.LOCATION_INCREMENT);
		ClientGameState.eu.setLongitude(lastKnownLocation.getLongitude());
		
		// Posiciona o usuario em sua posiÃ§Ã£o atual no mapa
		lastUserGeoPoint = ClientGameState.eu.createLocationGeoPoint();
		
		mapController.animateTo(lastUserGeoPoint);
		mapController.setZoom(mapa.getMaxZoomLevel() - 3);
	}
	
	private void atualizaPlayers() {			
		ArrayList<GeoPoint> locais = desenhaPlayers();
		ArrayList<Drawable> images = desenhaIcones();

		LocationOverlay myOverlay = new LocationOverlay(getResources().getDrawable(R.drawable.icon));
		
		myOverlay.setItems(locais, images);
		getMapa().getOverlays().clear();
        getMapa().getOverlays().add(myOverlay);
	}
	
	public MapController getMapController() {
		return mapController;
	}
	public MapView getMapa() {
		return mapa;
	}

	public void setMapa(MapView mapa) {
		this.mapa = mapa;
	}

	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}
	
	
	
	private ArrayList<Drawable> desenhaIcones() {
		ArrayList<Drawable> images = new ArrayList<Drawable>();

		// TODO Mudar o icone de acordo com o tipo de avatar do amigo
		for(int i = 0; i < ClientGameState.amigos.size(); i++) {
			images.add(getResources().getDrawable(R.drawable.icon_amigo));
		}

		// Adiciona icone do proprio player "eu"
		if(ClientGameState.eu != null)
			images.add(getResources().getDrawable(R.drawable.icon));
		
		return images;

	}
	
	private ArrayList<GeoPoint> desenhaPlayers() {
		ArrayList<GeoPoint> locais = new ArrayList<GeoPoint>();
		
		List<Player> amigos = ClientGameState.amigos;
		
		for(Player amigo : amigos){
			locais.add(amigo.createLocationGeoPoint());
		}
		
		// Adiciona o player "eu"
		if(ClientGameState.eu != null) {
			locais.add(ClientGameState.eu.createLocationGeoPoint());			
			System.out.println("GEOPOINT: " + ClientGameState.eu.createLocationGeoPoint());
		}
		
		return locais;
	}
}
