package br.ufc.activity;


import android.os.Bundle;
import br.ufc.sensor.GpsSensor;
import br.ufc.sensor.MapSensor;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity {
	
	GpsSensor gpsSensor;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Inicializa os sensores e repassa os listeners que controlam as mudanças
		gpsSensor = new GpsSensor(this);
		gpsSensor.setMapSensor(new MapSensor((MapView)findViewById(R.id.mapa), getResources()));
		gpsSensor.start();	
		
	}
	
	
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}