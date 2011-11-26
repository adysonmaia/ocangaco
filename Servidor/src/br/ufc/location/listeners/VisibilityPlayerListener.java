package visibilitylistener;

import facade.IMobileDevice;
import facade.IVisibilityListener;
import game.Player;

public class VisibilityPlayerListener implements IVisibilityListener{

	@Override
	public boolean isVisible(IMobileDevice device1, IMobileDevice device2,double distance) {
		 // Verifica se o dispositivo é do time adversário
		 if(device1.getGroup() != device2.getGroup()){
			 if ((distance > 0)&&(distance < Player.VIEW_DISTANCE)){
				 return true;
			 }
		 }
		return false;
	}

}
