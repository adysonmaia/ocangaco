package br.ufc.sensor;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

/**
 * @author Andre, Benedito
 * Class that represents the GPS Sensor.
 * 
 * Inicializa o serviço de localização por GPS, responsavel 
 * por modificar as localizaçao no mapa
 */
public class GpsSensor {
	
		
	private LocationManager locationManager;
	private LocationProvider locationProvider;
	private Location lastKnownLocation;
	private MapSensor mapSensor;

	// Distancia minima percorrida para uma nova atualização
	private static final int MIN_DISTANCE_UPDATE = 5;
	
	// Tempo minimo de uma nova atualização
	private static final int MIN_TIME_UPDATE = 0;

	private boolean running;

	
	
	public MapSensor getMapSensor() {
		return mapSensor;
	}

	public void setMapSensor(MapSensor mapSensor) {
		this.mapSensor = mapSensor;
	}

	public GpsSensor(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
		lastKnownLocation = locationManager.getLastKnownLocation(locationProvider.getName());
		running = false;
	}

	/**
	 * Start GPS sensing
	 */
	public void start() {
		if(lastKnownLocation != null){getMapSensor().setLastKnownLocation(lastKnownLocation);}		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_UPDATE, MIN_DISTANCE_UPDATE, locationListener);		
		running = true;
	}

	/**
	 * Stop GPS sensing
	 */
	public void stop() {
		locationManager.removeUpdates(locationListener);
		running = false;
	}

	/**
	 * Use to know if this sensor is already running or not
	 * @return true if sensor is already running
	 */
	public boolean isRunning() {
		return running;
	}

	private LocationListener locationListener = new LocationListener() {
		
		/**
		 * Responsavel por atualizar o novo posicionamento do usuario na tela do mapa.
		 */
		@Override
		public void onLocationChanged(Location location) {
			getMapSensor().setLastKnownLocation(location);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}
	};

}