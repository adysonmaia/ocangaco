package br.ufc.servidor.artefatos;

import org.w3c.dom.Element;

import br.ufc.location.facade.IGeoPosition;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.location.facade.IVisibilityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.location.listeners.ProximityListener;
import br.ufc.location.listeners.VisibilityListener;

public abstract class MobileDevice implements IMobileDevice{		
	protected double latitude;
	protected double longitude;
	
	protected Integer id;
	protected GeoPosition position;
	protected double distance;
	protected double distanceOn;
	protected Integer type;
	protected Integer groupId;
	protected DevicePath path;
	protected ProximityListener proximity;
	protected VisibilityListener visibility;
	
	public static final int SOLDIER = 1;
	public static final int DOCTOR = 2;
	public static final int ENGINEER = 3;
	public static final int SPY = 4;
	
	public static final int MINA = 5;
	public static final int BARRICADA = 6;
	
	public static final int BLUE_TEAM = 1;
	public static final int RED_TEAM = 2;
	public static final int DEFAULT_GROUP = BLUE_TEAM;

	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		GeoPosition pos;

		if (position != null) {
			this.position = (GeoPosition) position;
			pos = new GeoPosition(position);			
			this.path.addPosition(pos);
			this.latitude = position.getLatitude();
			this.longitude = position.getLongitude();
		}
	}

	@Override
	public IGeoPosition getGeoPosition() {
		return (IGeoPosition) position;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		distance = DevicesPositionControl.calculateDistance(this
				.getGeoPosition(), device.getGeoPosition());
		return distance;
	}

	@Override
	public double getLastDistance() {
		return distance;
	}

	@Override
	public Integer getType() {
		return type;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;

	}

	@Override
	public void setDistanceOn(double dist) {
		this.distanceOn = dist;
	}

	@Override
	public void setGroup(Integer group) {
		this.groupId = group;
	}

	@Override
	public Integer getGroup() {
		return groupId;
	}

	@Override
	public double getDistanceOn() {
		return distanceOn;
	}

	@Override
	public void setDevicePath(DevicePath path) {
		this.path = path;
	}

	@Override
	public DevicePath getDevicePath() {
		return path;
	}

	@Override
	public void setProximityListener(IProximityListener proximity) {
		this.proximity = (ProximityListener) proximity;

	}

	@Override
	public IProximityListener getProximityListener() {
		return proximity;
	}
	
	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
	}

	@Override
	public abstract String toXML() throws Exception;
	
	@Override
	public void fromXML(Element element) {
		
	}
	
	public abstract IMobileDevice clone();
}
