package br.ufc.servidor.artefatos;

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
import br.ufc.location.listeners.ProximityMinaListener;
import br.ufc.location.listeners.VisibilityMinaListener;
import br.ufc.util.XMLParser;

public class Mina implements IMobileDevice{
	private double latitude, longitude;
	private int damage;
	
	private Integer id;
	private GeoPosition position;
	private double distance;
	private double distanceOn;
	private Integer type;
	private Integer groupId;
	private DevicePath path;
	private ProximityMinaListener proximity;
	private VisibilityMinaListener visibility;
	
	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;

	public Mina(int group, double latitude, double longitude, int damage) {
		this.groupId = group;
		this.latitude = latitude;
		this.longitude = longitude;
		this.damage = damage;
		
		proximity = new ProximityMinaListener();
		visibility = new VisibilityMinaListener();
		path = new DevicePath();
	}
	

	public Mina() {
		this(1, 0, 0, 0);
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Mina [damage=" + damage + ", groupId=" + groupId
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}


	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the logintude
	 */	
	public double getLogintude() {
		return longitude;
	}

	/**
	 * @param logintude the logintude to set
	 */
	public void setLogintude(double logintude) {
		this.longitude = logintude;
	}

	@Override
	public DevicePath getDevicePath() {
		return path;
	}

	@Override
	public double getDistanceFrom(IMobileDevice device) {
		distance = DevicesPositionControl.calculateDistance(this
				.getGeoPosition(), device.getGeoPosition());
		return distance;
	}

	@Override
	public double getDistanceOn() {
		return distanceOn;
	}

	@Override
	public IGeoPosition getGeoPosition() {
		return position;
	}

	@Override
	public Integer getGroup() {
		return groupId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public double getLastDistance() {
		return distance;
	}

	@Override
	public IProximityListener getProximityListener() {
		return proximity;
	}

	@Override
	public Integer getType() {
		return type;
	}

	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
	}

	@Override
	public void setDevicePath(DevicePath path) {
		this.path = path;
	}

	@Override
	public void setDistanceOn(double dist) {
		this.distanceOn = dist;
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		this.position = (GeoPosition)position;		
	}

	@Override
	public void setGroup(Integer group) {
		this.groupId = group;
	}

	@Override
	public void setProximityListener(IProximityListener listener) {
		this.proximity = (ProximityMinaListener) proximity;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;
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
		Element element = doc.createElement("mina");
		if (parent == null) {
			doc.appendChild(element);
		} else {
			parent.appendChild(element);
		}
		// set attributes to mina element		
		element.setAttribute("grupo", String.valueOf(this.getGroup()));
		element.setAttribute("latitude", String.valueOf(this.getLatitude()));
		element.setAttribute("longitude", String.valueOf(this.getLogintude()));
		element.setAttribute("damage", String.valueOf(this.getDamage()));
	}

	public void fromXML(String xmlString) throws Exception {
		Document doc = XMLParser.createXMLDocument(xmlString);

		if (doc != null) {
			NodeList nodes = doc.getElementsByTagName("mina");
			Element p = (Element) nodes.item(0);

			if (p != null) {
				fromXML(p);
			}
		} else {
			throw new Exception("XML parser error");
		}
	}
	
	public void fromXML(Element element){
		this.setGroup(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLogintude(Double.parseDouble(element
				.getAttribute("longitude")));
		this.setDamage(Integer.parseInt(element.getAttribute("damage")));
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


	public int getDamage() {
		return damage;
	}

}
