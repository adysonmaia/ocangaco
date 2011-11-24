package visibilitylistener;

import facade.IMobileDevice;
import geoengine.IVisibilityListener;

public class VisibilityPlayListener implements IVisibilityListener{

	@Override
	public boolean isVisible(IMobileDevice device1, IMobileDevice device2,double distance) {
		return false;
	}

}
