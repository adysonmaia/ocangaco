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
import br.ufc.model.Player;
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

	private PlayerItemizedOverlay cangaceirosItemizedOverlay;
	private PlayerItemizedOverlay jaguncosItemizedOverlay;
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

		cangaceirosItemizedOverlay = new PlayerItemizedOverlay(getResources().getDrawable(R.drawable.icon_cangaceiro), Player.CANGACEIRO);
		jaguncosItemizedOverlay = new PlayerItemizedOverlay(getResources().getDrawable(R.drawable.icon_jagunco), Player.JAGUNCO);
		mineItemizedOverlay = new MineItemizedOverlay(getResources().getDrawable(R.drawable.mine));
		barrierItemizedOverlay = new BarrierItemizedOverlay(getResources().getDrawable(R.drawable.barrier));
				
		getMapView().getOverlays().add(cangaceirosItemizedOverlay);
		getMapView().getOverlays().add(jaguncosItemizedOverlay);
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
					cangaceirosItemizedOverlay.invokePopulate();
					jaguncosItemizedOverlay.invokePopulate();
					barrierItemizedOverlay.invokePopulate();
					
					vida.setProgress(100);
					
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
		
		int id = ServerFactory.getServer().createBarrier(barrier);
		ClientGameState.barriers.put(id, new Barrier(id, ClientGameState.myPlayerOnClient.getTipo(),
				ClientGameState.myPlayerOnClient.getLatitude(), ClientGameState.myPlayerOnClient.getLongitude()));
		barrierItemizedOverlay.invokePopulate();
	}
	
	public void addMine() {		
		Mine mine = new Mine(ClientGameState.myPlayerOnClient.getTipo(), 100,
				ClientGameState.myPlayerOnClient.getLatitude(), ClientGameState.myPlayerOnClient.getLongitude());
		
		int id = ServerFactory.getServer().createMine(mine);		 
		ClientGameState.mines.put(id, mine);
		mineItemizedOverlay.invokePopulate();
				
//		System.out.println("Colocando mina em:" + ClientGameState.myPlayerOnClient.getLatitude() + " " + ClientGameState.myPlayerOnClient.getLongitude());		
	}
		
}
