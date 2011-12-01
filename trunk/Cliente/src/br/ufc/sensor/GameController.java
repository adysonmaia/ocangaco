package br.ufc.sensor;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import br.ufc.activity.R;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.net.ServerFactory;
import br.ufc.util.MineItemizedOverlay;
import br.ufc.util.PlayerItemizedOverlay;

import com.google.android.maps.MapView;

public class GameController {
	private MapView mapView;
	private GpsSensor gpsSensor;	
	private Thread gameThread;
	private Resources resources; //Usado para capturar as imagens locais

	private PlayerItemizedOverlay cangaceirosItemizedOverlay;
	private PlayerItemizedOverlay jaguncosItemizedOverlay;
	private MineItemizedOverlay mineItemizedOverlay;
	
	private boolean running;
	
	private final int UPDATE_RATE = 2000;
	
	public GameController(MapView mapa, Context context) {
		initiateObjects(mapa, context.getResources());
		
		gpsSensor = new GpsSensor(context);
		gpsSensor.setGameController(this);
		gpsSensor.start();
		
		setMapConfigurations(true, false);
	}

	private void initiateObjects(MapView mapView, Resources resources) {
		this.resources = resources;
		this.mapView = mapView;

		cangaceirosItemizedOverlay = new PlayerItemizedOverlay(getResources().getDrawable(R.drawable.icon_cangaceiro), Player.CANGACEIRO);
		jaguncosItemizedOverlay = new PlayerItemizedOverlay(getResources().getDrawable(R.drawable.icon_jagunco), Player.JAGUNCO);
		mineItemizedOverlay = new MineItemizedOverlay(getResources().getDrawable(R.drawable.mine));
				
		getMapView().getOverlays().add(cangaceirosItemizedOverlay);
		getMapView().getOverlays().add(jaguncosItemizedOverlay);
		getMapView().getOverlays().add(mineItemizedOverlay);
	}

	private void setMapConfigurations(boolean sattelite, boolean streetView) {
		mapView.setSatellite(true);
		mapView.setStreetView(false);		
	}
	
	public void start() {
		running = true;
		
		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(running) {
					 // Atualiza a lista de jogadores, vai no servidor e 
					ClientGameState.updateState();					
					
					cangaceirosItemizedOverlay.invokePopulate();
					jaguncosItemizedOverlay.invokePopulate();
					mineItemizedOverlay.invokePopulate();
					
					// Atualiza o mapa
					handler.sendMessage(handler.obtainMessage());
					
					try {
						Thread.sleep(UPDATE_RATE);				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		gameThread.start();
	}

	// Handler utilizado para atualizar o mapa sem gerar excecício de Threads
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	mapView.invalidate();
            
        	if(ClientGameState.getMyPlayerOnServer() != null)
        		mapView.getController().setCenter(ClientGameState.getMyPlayerOnServer().createLocationGeoPoint());
        }
    };
	
	public Resources getResources() {
		return resources;
	}

	public void setLastKnownLocation(Location lastKnownLocation) {
		ClientGameState.myPlayerOnClient.setLatitude(lastKnownLocation.getLatitude());				
		ClientGameState.myPlayerOnClient.setLongitude(lastKnownLocation.getLongitude());
		
		//Atualiza posição do jogador no servidor. 
		ServerFactory.getServer().updatePlayerLocation(ClientGameState.myPlayerOnClient);
	}
	
	public MapView getMapView() {
		return mapView;
	}
	
	public void stop() {
		try {
			gpsSensor.stop();
			running = false;
			gameThread.join();

			ServerFactory.getServer().closeConnection(ClientGameState.myPlayerOnClient);
			ClientGameState.reset();
			mapView.invalidate();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
