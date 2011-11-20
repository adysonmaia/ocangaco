package br.ufc.model;

/**
 * @author Andr� Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player {
	
	private String nome;
	private int tipo;
	private int latitude;
	private int longitude;

	public Player(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	public Player(String nome) {
		super();
		this.nome = nome;
	}

	public Player(String nome, int tipo, int latitude, int longitude) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

}