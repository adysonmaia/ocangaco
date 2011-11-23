package br.ufc.activity;


import android.os.Bundle;
import br.ufc.sensor.GameController;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity {
	
	private MapView mapView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mapView = (MapView)findViewById(R.id.mapa);
		mapView.setBuiltInZoomControls(true);
		
		GameController gameController = new GameController(mapView, this);
		gameController.start();
	}
	
	
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}