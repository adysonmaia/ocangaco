package game;

import facade.IMobileDevice;
import facade.IProximityListener;
import geoengine.DevicePath;
import geoengine.IGeoPosition;

/**
 * @author Andre Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player implements IMobileDevice{
	
	private String nome;
	private int tipo;
	private double latitude;
	private double longitude;

	public Player(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	/**
	 * 
	 * @param nome Nome do Jogador
	 */
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

	@Override //sobrecarga apenas para fins de visualização do estado do objeto
	public String toString() {
		return "Player [latitude=" + latitude + ", longitude=" + longitude
				+ ", nome=" + nome + ", tipo=" + tipo + "]";
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGeoPosition getGeoPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLastDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(Integer type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDistanceOn(float dist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroup(Integer group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDistanceOn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDevicePath(DevicePath path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DevicePath getDevicePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProximityListener(IProximityListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IProximityListener getProximityListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}
}