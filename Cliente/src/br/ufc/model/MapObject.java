package br.ufc.model;

import org.w3c.dom.Element;

import com.google.android.maps.GeoPoint;


/**
 * Classe pai de todos os objetos que podem ser adicionados ao mapa (Player, Mine, etc..)
 * @author Andre
 *
 */
public abstract class MapObject {
	
	private double latitude;
	private double longitude;

	public MapObject() {
	}
	
	public MapObject(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the logintude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param logintude the logintude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Generate a new GeoPoint containing objects current latitude and longitude
	 * @return
	 */
	public GeoPoint createLocationGeoPoint() {
		return new GeoPoint((int)(latitude * 1E6), (int) (longitude * 1E6));
	}
	
	public void fromXML(Element element){
		
	}
	
	public MapObject clone(){
		return null;
	}

}
