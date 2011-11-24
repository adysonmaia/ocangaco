package geoengine;

import facade.IMobileDevice;

public interface IVisibilityListener {
	public boolean isVisible(IMobileDevice device1,IMobileDevice device2,double distance);
}
