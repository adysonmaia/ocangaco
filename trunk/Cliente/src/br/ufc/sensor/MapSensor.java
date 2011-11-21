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
		this.resources = resources;
		this.mapa = mapa;
		this.mapController = mapa.getController();
		// Caso do JP
		double lat = -3.7307950;// No emulador -3.7307950 
		double log = -38.575991;// No emulador -38.575991
		Location location = new Location("");
		location.setLatitude(lat);
		location.setLongitude(log);
		
		this.lastUserGeoPoint = new GeoPoint((int)(lat), (int)(log));	
		setLastKnownLocation(location);
		
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
				ClientGameState.eu = new Player("Fulano");
		}

//		System.out.println("LOCA: " + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude());
		
		ClientGameState.eu.setLatitude((int)(lastKnownLocation.getLatitude()));
		ClientGameState.eu.setLongitude((int)(lastKnownLocation.getLongitude()));
		
		// Posiciona o usuario em sua posiÃ§Ã£o atual no mapa
		lastUserGeoPoint = ClientGameState.eu.createLocationGeoPoint();
		
		mapController.animateTo(lastUserGeoPoint);
		mapController.setZoom(15);
		
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
			images.add(getResources().getDrawable(R.drawable.icon));
		}

		// Adiciona icone do proprio player "eu"
		if(ClientGameState.eu != null)
			images.add(getResources().getDrawable(R.drawable.icon));
		
		return images;

	}
	
	private ArrayList<GeoPoint> desenhaPlayers() {
		
		List<Player> amigos = ClientGameState.amigos;
		ArrayList<GeoPoint> locais = new ArrayList<GeoPoint>();
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
