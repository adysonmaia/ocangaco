package br.ufc.servidor.player;

import br.ufc.location.facade.IGeoPosition;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.location.facade.IVisibilityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.location.listeners.ProximityPlayerListener;
import br.ufc.location.listeners.VisibilityPlayerListener;

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
	private GeoPosition                position;
	private double                     distance;
	private double                   distanceOn;
	private Integer                        type;
	private Integer                     groupId;
	private DevicePath                     path;
	private ProximityPlayerListener   proximity;
	private VisibilityPlayerListener visibility;


	public static final int SOLDIER  = 1;
	public static final int DOCTOR   = 2;
	public static final int ENGINEER = 3;
	public static final int SPY      = 4;	
	public static final int DEFAULT_GROUP = SOLDIER;
	
	public static final int RED_TEAM  = 1;
	public static final int BLUE_TEAM = 2;

	public static final int VIEW_DISTANCE     = 100;
	public static final int COLISION_DISTANCE = 10;
	
	
	public Player(String nome, int tipo) {
		
		super();

		this.nome = nome;
		this.tipo = tipo;
		
		proximity  = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path       = new DevicePath();
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @param nome Nome do Jogador
	 */
	public Player(String nome) {
		
		super();
		this.nome = nome;
		proximity  = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path       = new DevicePath();
		
	}

	public Player(String nome, int tipo, int group, double latitude, double longitude) {
		super();
		this.nome      = nome;
		this.tipo      = tipo;
		this.groupId   = group;
		this.latitude  = latitude;
		this.longitude = longitude;
		proximity  = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path       = new DevicePath();
	}

	public Player(String nome, int tipo, double latitude, double longitude) {
		super();
		
		this.nome = nome;
		this.tipo = tipo;
		this.latitude = latitude;
		this.longitude = longitude;
		this.groupId   = DEFAULT_GROUP;
		proximity  = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path       = new DevicePath();
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
		return path;
	}

	@Override
	public void setProximityListener(IProximityListener proximity) {
		this.proximity = (ProximityPlayerListener) proximity;
		
	}

	@Override
	public IProximityListener getProximityListener() {
		return proximity;
	}

	@Override
	public String toXML() {
		StringBuffer  resp;
		
		resp = new StringBuffer();
		resp.append("<player>");
		resp.append("  <id>");
		resp.append(String.valueOf((this.getId()).intValue()));
		resp.append("  </id>");
		resp.append("  <type>");
		resp.append(String.valueOf(this.getTipo()));
		resp.append("  </type>");
		resp.append("  <latitude>");
		resp.append(String.valueOf(this.getLatitude()));
		resp.append("  </latitude>");
		resp.append("  <longitude>");
		resp.append(String.valueOf(this.getLongitude()));
		resp.append("  </longitude>");
		resp.append("</player>/n");
		return resp.toString();
	}

	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
	}

}