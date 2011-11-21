package br.ufc.activity;


import android.os.Bundle;
import br.ufc.sensor.GpsSensor;
import br.ufc.sensor.MapSensor;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity {
	
	private GpsSensor gpsSensor;	
	private MapView mapa;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mapa = (MapView)findViewById(R.id.mapa);
		mapa.setBuiltInZoomControls(true);
		
		// Inicializa os sensores e repassa os listeners que controlam as mudanças
		gpsSensor = new GpsSensor(this);
		gpsSensor.setMapSensor(new MapSensor(mapa, getResources()));
		gpsSensor.start();	
		
	}
	
	
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}