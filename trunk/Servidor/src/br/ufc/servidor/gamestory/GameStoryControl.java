package br.ufc.servidor.gamestory;

import java.util.HashMap;

public class GameStoryControl {
	private static int freeMessageId;
	private HashMap<Integer, Message> messages;	
	static GameStoryControl manager ;
	
	static {
		freeMessageId = 0;
	}

	static public synchronized int getNextFreeMessageId() {
		return freeMessageId++;
	}
	static public synchronized GameStoryControl getInstance(){
		if (manager == null)
		    manager = new GameStoryControl();
		return manager;
	}
	
	public GameStoryControl() {
		super();
		messages = new HashMap<Integer, Message>();
	}
	
	public void addMessage(Message message) {
		message.setId(getNextFreeMessageId());
		messages.put(message.getId(), message);
		System.out.println("GameStory: " + message.getValue());
	}
}
