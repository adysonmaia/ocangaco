package br.ufc.servidor.gamestory;

import java.util.Date;

import br.ufc.location.facade.IMobileDevice;

public class Message {
	private int id;
	private Date time;
	private String value;
	private IMobileDevice device;
	
	public Message(String value) {		
		this(value,null);
	}
	
	public Message(String value, IMobileDevice device) {
		super();
		this.value = value;
		this.device = device;
		this.setTime(new Date());
		this.id = 0;		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getTime() {
		return time;
	}
	public void setDevice(IMobileDevice device) {
		this.device = device;
	}
	public IMobileDevice getDevice() {
		return device;
	}

	@Override
	public String toString() {
		return "Message [time=" + time + ", value=" + value + "]";
	}
}
