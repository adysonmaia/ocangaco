package br.ufc.servidor.player;

/**
 * @author André Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player {
	
	private String nome;
	private int tipo;
	private double latitude;
	private double longitude;

	public Player(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	public Player(String nome) {
		super();
		this.nome = nome;
	}

	public Player(String nome, int tipo, double latitude, double longitude) {
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Player [latitude=" + latitude + ", longitude=" + longitude
				+ ", nome=" + nome + ", tipo=" + tipo + "]";
	}
}