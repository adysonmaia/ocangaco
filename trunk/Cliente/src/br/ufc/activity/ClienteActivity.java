package br.ufc.activity;



import android.os.Bundle;
import android.widget.Toast;
import br.ufc.sensor.GameController;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity {
	
	private MapView mapView;
	private GameController gameController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		mapView = (MapView)findViewById(R.id.mapa);
		mapView.setBuiltInZoomControls(true);
		mapView.getOverlays().clear();
		
		gameController = new GameController(mapView, this);
		gameController.start();
	}
	
	@Override
	public void onBackPressed() {
		gameController.stop();
		
		super.onBackPressed();
		
		Toast.makeText(this, "Log out efetuado!", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}