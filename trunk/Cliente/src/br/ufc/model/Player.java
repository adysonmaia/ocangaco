package br.ufc.model;

import com.google.android.maps.GeoPoint;

/**
 * @author Andr� Fonteles, Rafael de Lima e Benedito Neto
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

	public GeoPoint createLocationGeoPoint() {
		return new GeoPoint((int)(latitude * 1E6), (int) (longitude * 1E6));
	}
}