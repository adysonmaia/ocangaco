package visibilitylistener;

import facade.IMobileDevice;
import facade.IVisibilityListener;

public class VisibilityPlayerListener implements IVisibilityListener{

	@Override
	public boolean isVisible(IMobileDevice device1, IMobileDevice device2,double distance) {
		return false;
	}

}
