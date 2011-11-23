package br.ufc.util;

import android.graphics.drawable.Drawable;
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
		GeoPoint geoPoint;
		if(type == Player.CANGACEIRO)
			geoPoint = ClientGameState.playersCangaceiros.get(ClientGameState.playersCangaceiros.keySet().toArray()[i]).createLocationGeoPoint();
		else
			geoPoint = ClientGameState.playersJaguncos.get(ClientGameState.playersJaguncos.keySet().toArray()[i]).createLocationGeoPoint();

		return new OverlayItem(geoPoint, "", "");
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
}