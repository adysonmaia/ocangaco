package br.ufc.activity;


import java.util.HashMap;

import android.os.Bundle;
import android.widget.Toast;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.net.ServerFactory;
import br.ufc.sensor.GameController;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity {
	
	private MapView mapView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		mapView = (MapView)findViewById(R.id.mapa);
		mapView.setBuiltInZoomControls(true);
		
		GameController gameController = new GameController(mapView, this);
		gameController.start();
	}
	
	@Override
	public void onBackPressed() {
		ServerFactory.getServer().closeConnection(ClientGameState.eu);
		ClientGameState.players = new HashMap<String, Player>();
		
		super.onBackPressed();
		
		Toast.makeText(this, "Log out efetuado!", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}