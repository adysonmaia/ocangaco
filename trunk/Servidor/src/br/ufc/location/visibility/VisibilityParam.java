package br.ufc.location.visibility;

public class VisibilityParam {
	private int visibility;
	private int distance;
	private int status;
	
	
	public VisibilityParam(int visibility, int distance, int status) {
		super();
		this.visibility = visibility;
		this.distance = distance;
		this.status = status;
	}

	
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
