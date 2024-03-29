package br.ufc.sensor;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import br.ufc.activity.R;
import br.ufc.model.Barrier;
import br.ufc.model.ClientGameState;
import br.ufc.model.Mine;
import br.ufc.net.ServerFactory;
import br.ufc.util.BarrierItemizedOverlay;
import br.ufc.util.MineItemizedOverlay;
import br.ufc.util.PlayerItemizedOverlay;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GameController {
	private MapView mapView;
	private ProgressBar vida;
	private GpsSensor gpsSensor;	
	private Thread gameThread;
	private Resources resources; //Usado para capturar as imagens locais

	private PlayerItemizedOverlay playerItemizedOverlay;
	private MineItemizedOverlay mineItemizedOverlay;
	private BarrierItemizedOverlay barrierItemizedOverlay;
	
	private boolean running;
	
	private final int UPDATE_RATE = 2000;
	
	public GameController(MapView mapa, ProgressBar life, Context context) {
		vida = life;
		
		initiateObjects(mapa, context.getResources());
		
		gpsSensor = new GpsSensor(context);
		gpsSensor.setGameController(this);
		gpsSensor.start();
		
		setMapConfigurations(true, false);
	}

	private void initiateObjects(MapView mapView, Resources resources) {
		this.resources = resources;
		this.mapView = mapView;

		playerItemizedOverlay = new PlayerItemizedOverlay(getResources());
		mineItemizedOverlay = new MineItemizedOverlay(getResources().getDrawable(R.drawable.mine));
		barrierItemizedOverlay = new BarrierItemizedOverlay(getResources().getDrawable(R.drawable.barrier));
				
		getMapView().getOverlays().add(playerItemizedOverlay);
		getMapView().getOverlays().add(mineItemizedOverlay);
		getMapView().getOverlays().add(barrierItemizedOverlay);
	}

	private void setMapConfigurations(boolean sattelite, boolean streetView) {
		mapView.setSatellite(true);
		mapView.setStreetView(false);
		MapController mc = mapView.getController();
		mc.setZoom(18);
		mc.animateTo(ClientGameState.myPlayerOnClient.createLocationGeoPoint());
	}
	
	public void start() {
		running = true;
		
		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(running) {
					 // Atualiza a lista de jogadores, vai no servidor e 
					ClientGameState.updateState();					
					
					mineItemizedOverlay.invokePopulate();
					playerItemizedOverlay.invokePopulate();
					barrierItemizedOverlay.invokePopulate();
					
					vida.setProgress(ClientGameState.myPlayerOnClient.getVida());
					
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

	// Handler utilizado para atualizar o mapa sem gerar excec�cio de Threads
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
		
		//Atualiza posi��o do jogador no servidor. 
		ServerFactory.getServer().updatePlayerLocation(ClientGameState.myPlayerOnClient);
		mapView.getController().animateTo(ClientGameState.myPlayerOnClient.createLocationGeoPoint());
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
	
	public void addBarrier() {
		Barrier barrier = new Barrier(ClientGameState.myPlayerOnClient.getTipo(),
				ClientGameState.myPlayerOnClient.getLatitude(), ClientGameState.myPlayerOnClient.getLongitude());
		
		ServerFactory.getServer().createBarrier(barrier);
//		ClientGameState.barriers.put(id, new Barrier(id, ClientGameState.myPlayerOnClient.getTipo(),
//				ClientGameState.myPlayerOnClient.getLatitude(), ClientGameState.myPlayerOnClient.getLongitude()));
//		barrierItemizedOverlay.invokePopulate();
	}
	
	public void addMine() {		
		Mine mine = new Mine(ClientGameState.myPlayerOnClient.getTipo(), 100,
				ClientGameState.myPlayerOnClient.getLatitude(), ClientGameState.myPlayerOnClient.getLongitude());
		
		ServerFactory.getServer().createMine(mine);		 
//		ClientGameState.mines.put(id, mine);
//		mineItemizedOverlay.invokePopulate();
				
//		System.out.println("Colocando mina em:" + ClientGameState.myPlayerOnClient.getLatitude() + " " + ClientGameState.myPlayerOnClient.getLongitude());		
	}
		
}
