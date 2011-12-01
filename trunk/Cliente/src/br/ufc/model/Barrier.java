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
