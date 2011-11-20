package br.ufc.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class LocationOverlay extends ItemizedOverlay<OverlayItem> {
	 
	private List<GeoPoint> mItems;
	private List<Drawable> mMarkers;
 
	public LocationOverlay(Drawable marker) {
		super(boundCenterBottom(marker));
	}
 
	public void setItems(ArrayList<GeoPoint>items, ArrayList<Drawable> drawables){
		mItems = items;
		mMarkers = drawables;
		populate();
	}
 
	@Override
	protected OverlayItem createItem(int i) {
		OverlayItem item = new OverlayItem(mItems.get(i),null,null);
		item.setMarker(boundCenterBottom(mMarkers.get(i)));
		return item;
 
	}
 
	@Override
	public int size() {
		return mItems.size();
	}
 
	@Override
	public boolean onTap(int i) {
		return true;
	}
 
}
