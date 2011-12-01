package br.ufc.location.listeners;

import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.servidor.gamestory.GameStoryControl;
import br.ufc.servidor.gamestory.Message;

public abstract class ProximityListener implements IProximityListener {
	protected IMobileDevice device1;
	protected IMobileDevice device2;
	protected String msg;	
	protected GameStoryControl storyControl = GameStoryControl.getInstance();
	
	public void logMessage(String msg){
		Message message = new Message(msg, device1);
		storyControl.addMessage(message);
	}
}
