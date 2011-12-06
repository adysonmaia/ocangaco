package br.ufc.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.sensor.GameController;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;


public class ClienteActivity  extends MapActivity implements OnClickListener {
	
	private MapView mapView;
	private ProgressBar life;
	private Button btBarricada;
	private GameController gameController;
	private Button btMina;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		btMina = (Button)findViewById(R.id.btMina);
		btMina.setOnClickListener(this);
		if (ClientGameState.myPlayerOnClient.getPapel() != Player.ESPIAO)
			btMina.setVisibility(View.GONE);
		
		btBarricada = (Button) findViewById(R.id.btBarricada);
		btBarricada.setOnClickListener(this);
		if (ClientGameState.myPlayerOnClient.getPapel() != Player.ENGENHEIRO)
			btBarricada.setVisibility(View.GONE);
		
		mapView = (MapView)findViewById(R.id.mapa);
		mapView.setBuiltInZoomControls(true);
		mapView.getController().setZoom(18);
		mapView.getOverlays().clear();
		
		life = (ProgressBar) findViewById(R.id.pbLife);
		
		gameController = new GameController(mapView, life, this);
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

	@Override
	public void onClick(View v) {
		if (v == btMina) {
			gameController.addMine();
		} else if (v == btBarricada) {
			gameController.addBarrier();	
		}
	}
}