package br.ufc.util;

import android.graphics.drawable.Drawable;
import br.ufc.model.ClientGameState;
import br.ufc.model.Mine;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MineItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	public MineItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public void accessPopulate() {
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Mine mine = ClientGameState.mines.get(ClientGameState.mines.keySet().toArray()[i]);
		return new OverlayItem(mine.createLocationGeoPoint(), "",  "Snippet");
	}

	@Override
	public int size() {
		return ClientGameState.mines.size();
	}

	public void invokePopulate() {
		populate();
	}
	
}