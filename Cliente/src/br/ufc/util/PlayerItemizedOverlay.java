package br.ufc.util;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import br.ufc.activity.MainActivity;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PlayerItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private int type;
	
	public PlayerItemizedOverlay(Drawable defaultMarker, int type) {
		super(boundCenterBottom(defaultMarker));
		
		this.type = type;
	}
	
	public void accessPopulate() {
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Player player;
		
		if(type == Player.CANGACEIRO)
			player = ClientGameState.playersCangaceiros.get(ClientGameState.playersCangaceiros.keySet().toArray()[i]);
		else
			player = ClientGameState.playersJaguncos.get(ClientGameState.playersJaguncos.keySet().toArray()[i]);

		return new OverlayItem(player.createLocationGeoPoint(), player.getNome(),  player.getNome()+" Snippet");
	}

	@Override
	public int size() {
		if(type == Player.CANGACEIRO)
			return ClientGameState.playersCangaceiros.size();
		else
			return ClientGameState.playersJaguncos.size();
	}

	public void invokePopulate() {
		populate();
	}
	
	@Override
	protected boolean onTap(int i) {
		
		
		GeoPoint geoPoint;
		Player player = (Player)ClientGameState.playersCangaceiros.keySet().toArray()[i];
		
		if(type == Player.CANGACEIRO)
			player = (Player)ClientGameState.playersCangaceiros.keySet().toArray()[i];			
		else
			player = (Player)ClientGameState.playersJaguncos.keySet().toArray()[i];			

		geoPoint = ClientGameState.playersCangaceiros.get(player).createLocationGeoPoint();
		
	  
	  OverlayItem item = new OverlayItem(geoPoint, player.getNome(),  player.getNome()+" Snippet");
	  AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.activity);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
}