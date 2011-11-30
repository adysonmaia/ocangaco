package br.ufc.servidor.artefatos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.location.facade.IGeoPosition;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.location.facade.IVisibilityListener;
import br.ufc.location.geoengine.DevicePath;

public class Mina implements IMobileDevice{
	private double latitude, longitude;
	private int group;
	
	private Integer id;

	public Mina(int group, double latitude, double longitude) {
		this.group = group;
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
	public double getLogintude() {
		return longitude;
	}

	/**
	 * @param logintude the logintude to set
	 */
	public void setLogintude(double logintude) {
		this.longitude = logintude;
	}

	@Override
	public DevicePath getDevicePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDistanceOn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IGeoPosition getGeoPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLastDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IProximityListener getProximityListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVisibilityListener getVisibilityListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDevicePath(DevicePath path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDistanceOn(double dist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroup(Integer group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProximityListener(IProximityListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(Integer type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toXML() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toXML(Element devices, Document doc) {
		// TODO Auto-generated method stub
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
