package br.ufc.sensor;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import br.ufc.activity.R;
import br.ufc.model.Player;
import br.ufc.model.Players;
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
	}
	
	public Resources getResources() {
		return resources;
	}

	public void setLastKnownLocation(Location lastKnownLocation) {
		
		this.lastKnownLocation = lastKnownLocation;
		// Posiciona o usuario em sua posição atual no mapa
		double lat = lastKnownLocation.getLatitude();
		double log = lastKnownLocation.getLongitude();
		lastUserGeoPoint = new GeoPoint( (int)(lat), (int)(log));
		
		mapController.animateTo(lastUserGeoPoint);
		mapController.setZoom(15);
		
		// TODO atualiza as imagem 
		atualizaAmigos();		
	}
	
	private void atualizaAmigos() {
		ArrayList<GeoPoint> locais = desenhaAmigos();
		ArrayList<Drawable> images = desenhaIcones();

		LocationOverlay myOverlay = new LocationOverlay(getResources().getDrawable(R.drawable.icon));
		
		myOverlay.setItems(locais, images);
        getMapa().getOverlays().add(myOverlay);
        
        
        //getMapController().setZoom(15);

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
		List<Player> amigos = Players.amigos;

		// TODO Mudar o icone de acordo com o tipo de avatar do amigo
		for (Player amigo : amigos) {
			images.add(getResources().getDrawable(R.drawable.icon));
		}

		return images;

	}
	
	private ArrayList<GeoPoint> desenhaAmigos() {
		
		List<Player> amigos = Players.amigos;
		ArrayList<GeoPoint> locais = new ArrayList<GeoPoint>();
		for(Player amigo : amigos){
			locais.add(new GeoPoint(amigo.getLatitude(), amigo.getLongitude()));
		}
		
		return locais;
	}
}
