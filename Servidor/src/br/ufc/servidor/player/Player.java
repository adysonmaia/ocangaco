package game;

import proximitylistener.ProximityPlayerListener;
import visibilitylistener.VisibilityPlayerListener;
import facade.IGeoPosition;
import facade.IMobileDevice;
import facade.IProximityListener;
import facade.IVisibilityListener;
import geoengine.DevicePath;
import geoengine.DevicesPositionControl;
import geoengine.GeoPosition;

/**
 * @author Andre Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player implements IMobileDevice{
	
	private String          nome;
	private int             tipo;
	private double      latitude;
	private double     longitude;
	
	private Integer                          id;
	public void setId(Integer id) {
		this.id = id;
	}

	private GeoPosition                position;
	private double                     distance;
	private double                   distanceOn;
	private Integer                        type;
	private Integer                     groupId;
	private DevicePath                     path;
	private ProximityPlayerListener   proximity;
	private VisibilityPlayerListener visibility;


	
	public Player(String nome, int tipo) {
		
		super();

		this.nome = nome;
		this.tipo = tipo;
		
		proximity  = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path       = new DevicePath();
		distanceOn = 10;
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
		return id;
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		GeoPosition pos;
		
		if(position != null){
			this.position = (GeoPosition) position; 
			pos = new GeoPosition(position);
			this.path.addPosition(pos);
		}
	}

	@Override
	public IGeoPosition getGeoPosition() {
		return (IGeoPosition)position;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		distance = DevicesPositionControl.calculateDistance(this.getGeoPosition(),device.getGeoPosition());
		return distance;
	}

	@Override
	public double getLastDistance() {
		return distance;
	}

	@Override
	public Integer getType() {
		return type;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;
		
	}

	@Override
	public void setDistanceOn(double dist) {
		this.distanceOn = dist;
	}

	@Override
	public void setGroup(Integer group) {
		this.groupId = group;
	}

	@Override
	public Integer getGroup() {
		return groupId;
	}

	@Override
	public double getDistanceOn() {
		return distanceOn;
	}

	@Override
	public void setDevicePath(DevicePath path) {
		this.path = path;
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

	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
	}

}