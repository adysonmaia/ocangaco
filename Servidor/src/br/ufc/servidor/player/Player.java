package br.ufc.servidor.player;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.location.facade.IGeoPosition;
import br.ufc.location.facade.IMobileDevice;
import br.ufc.location.facade.IProximityListener;
import br.ufc.location.facade.IVisibilityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.geoengine.DevicesPositionControl;
import br.ufc.location.geoengine.GeoPosition;
import br.ufc.location.listeners.ProximityPlayerListener;
import br.ufc.location.listeners.VisibilityPlayerListener;
import br.ufc.util.XMLParser;

/**
 * @author Andre Fonteles, Rafael de Lima e Benedito Neto
 * 
 *         Class that defines a player
 * 
 */
public class Player implements IMobileDevice {

	private String nome;
	private int tipo;
	private double latitude;
	private double longitude;

	private Integer id;
	private GeoPosition position;
	private double distance;
	private double distanceOn;
	private Integer type;
	private Integer groupId;
	private DevicePath path;
	private ProximityPlayerListener proximity;
	private VisibilityPlayerListener visibility;

	public static final int SOLDIER = 1;
	public static final int DOCTOR = 2;
	public static final int ENGINEER = 3;
	public static final int SPY = 4;
	public static final int DEFAULT_TIPO = SOLDIER;

	public static final int BLUE_TEAM = 1;
	public static final int RED_TEAM = 2;
	public static final int DEFAULT_GROUP = BLUE_TEAM;

	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;

	public Player(String nome, int tipo) {

		super();

		this.nome = nome;
		this.tipo = tipo;

		proximity = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path = new DevicePath();
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Player(String nome) {
		this(nome, DEFAULT_TIPO);
	}
	
	public Player(String nome, int tipo, double latitude, double longitude) {
		this(nome, tipo, DEFAULT_GROUP, latitude, longitude);
	}

	public Player(String nome, int tipo, int group, double latitude,
			double longitude) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.groupId = group;
		this.latitude = latitude;
		this.longitude = longitude;
		proximity = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path = new DevicePath();
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
	// sobrecarga apenas para fins de visualização do estado do objeto
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

		if (position != null) {
			this.position = (GeoPosition) position;
			pos = new GeoPosition(position);
			this.path.addPosition(pos);
		}
	}

	@Override
	public IGeoPosition getGeoPosition() {
		return (IGeoPosition) position;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		distance = DevicesPositionControl.calculateDistance(this
				.getGeoPosition(), device.getGeoPosition());
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
	public String toXML() throws Exception {
		Document doc = XMLParser.createXMLDocument();
		if (doc != null) {
			toXML(null, doc);
			return XMLParser.getXMLString(doc);
		} else {
			throw new Exception("XML parser error");
		}
	}

	public void toXML(Element parent, Document doc) {
		Element player = doc.createElement("player");
		if (parent == null) {
			doc.appendChild(player);
		} else {
			parent.appendChild(player);
		}
		// set attributes to player element
		player.setAttribute("nome", String.valueOf(this.getNome()));
		player.setAttribute("tipo", String.valueOf(this.getTipo()));
		player.setAttribute("grupo", String.valueOf(this.getGroup()));
		player.setAttribute("latitude", String.valueOf(this.getLatitude()));
		player.setAttribute("longitude", String.valueOf(this.getLongitude()));
	}

	public void fromXML(String xmlString) throws Exception {
		Document doc = XMLParser.createXMLDocument(xmlString);

		if (doc != null) {
			NodeList nodes = doc.getElementsByTagName("player");
			Element p = (Element) nodes.item(0);

			if (p != null) {
				fromXML(p);
			}
		} else {
			throw new Exception("XML parser error");
		}
	}
	
	public void fromXML(Element element){
		this.setNome(element.getAttribute("nome"));
		this.setTipo(Integer.parseInt(element.getAttribute("tipo")));
		this.setGroup(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
	}

	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
	}
}