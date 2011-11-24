package geoengine;

import java.util.Date;

public class GeoPosition implements IGeoPosition {
	Date         date;
	float       speed;
	float   direction;
	float    latitude;
	float   longitude;

	public GeoPosition(Date date, float speed, float direction, float latitude,float longitude) {
		super();
		this.date      = date;
		this.speed     = speed;
		this.direction = direction;
		this.latitude  = latitude;
		this.longitude = longitude;
	}
	public GeoPosition(IGeoPosition position) {
		super();
		this.date      = position.getDate();
		this.speed     = position.getSpeed();
		this.direction = position.getDirection();
		this.latitude  = position.getLatitude();
		this.longitude = position.getLongitude();
	}
	
	
	@Override
	public void setLatitude(float latitude) {
		this.latitude = latitude;

	}

	@Override
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void setDirection(float angle) {
		this.direction = angle;
	}

	@Override
	public float getLatitude() {
		return latitude;
	}

	@Override
	public float getLongitude() {
		return longitude;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public float getDirection() {
		return direction;
	}

	@Override
	public void setDate(Date date) {
		this.date=date;
	}

	@Override
	public Date getDate() {
		return date;
	}

}
