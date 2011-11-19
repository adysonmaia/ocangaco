package br.ufc.sensor;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @author Andre, Benedito
 * Class that represents the GPS Sensor.
 */
public class GpsSensor {

	private LocationManager locationManager;

	private static final int MIN_DISTANCE_UPDATE = 5;
	private static final int MIN_TIME_UPDATE = 0;

	private boolean running;

	public GpsSensor(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		running = false;
	}

	/**
	 * Start GPS sensing
	 */
	public void start() {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MIN_TIME_UPDATE, MIN_DISTANCE_UPDATE, locationListener);
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
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO : Atualizar localização para o responsável por enviar mudanças de estado do player ao servidor
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