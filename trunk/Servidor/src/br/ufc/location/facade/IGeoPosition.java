package br.ufc.location.facade;

import java.util.Date;

public interface IGeoPosition {
	
	public void setLatitude(double latitude);
	public void setLongitude(double longitude);
	public void setSpeed(double speed);
	public void setDirection(double angle);
	public double getLatitude();
	public double getLongitude();
	public double getSpeed();
	public double getDirection();
	public void setDate(Date date);
	public Date getDate();
}
