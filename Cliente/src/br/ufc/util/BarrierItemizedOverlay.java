package br.ufc.util;

import android.graphics.drawable.Drawable;
import br.ufc.model.Barrier;
import br.ufc.model.ClientGameState;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class BarrierItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	public BarrierItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public void accessPopulate() {
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Barrier barrier = ClientGameState.barriers.get(ClientGameState.barriers.keySet().toArray()[i]);
		return new OverlayItem(barrier.createLocationGeoPoint(), "",  "Snippet");
	}

	@Override
	public int size() {
		return ClientGameState.barriers.size();
	}

	public void invokePopulate() {
		populate();
	}
	
}