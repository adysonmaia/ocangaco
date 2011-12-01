package br.ufc.model;

public class Mine extends MapObject {
	
	// Id da mina
	private int id;

	// Damage this mine causes
	private int damage;

	// ver Player.CANGACEIRO e Player.JAGUNCO
	private int tipo;

	public Mine(int id, int tipo, int damage, double latitude, double longitude) {
		super(latitude, longitude);
		this.id = id;
		this.tipo = tipo;
		this.damage = damage;
	}

	public Mine() {
		this(0, 0 , 0, 0, 0);
	}

	public Mine(int tipo, int damage, double latitude, double longitude) {
		this(0, tipo, damage, latitude, longitude);
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
