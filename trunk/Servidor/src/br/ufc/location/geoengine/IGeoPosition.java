package br.ufc.location.geoengine;

import java.util.Date;

public interface IGeoPosition {
	
	public void setLatitude(float latitude);
	public void setLongitude(float longitude);
	public void setSpeed(float speed);
	public void setDirection(float angle);
	public float getLatitude();
	public float getLongitude();
	public float getSpeed();
	public float getDirection();
	public void setDate(Date date);
	public Date getDate();
}
