package br.ufc.model;

public class Barrier extends MapObject {
	
	// Id da barricada
	private int id;

	// ver Player.CANGACEIRO e Player.JAGUNCO
	private int tipo;

	public Barrier(int id, int tipo, double latitude, double longitude) {
		super(latitude, longitude);
		this.id = id;
		this.tipo = tipo;
	}

	public Barrier(int tipo2, double latitude, double longitude) {
		this(0, tipo2, latitude, longitude);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
