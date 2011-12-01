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
import br.ufc.location.listeners.ProximityBarricadaListener;
import br.ufc.location.listeners.VisibilityBarricadaListener;
import br.ufc.util.XMLParser;

public class Barricada implements IMobileDevice{
	private double latitude, longitude;

	private Integer id;
	private GeoPosition position;
	private double distance;
	private double distanceOn;
	private Integer type;
	private Integer groupId;
	private DevicePath path;
	private ProximityBarricadaListener proximity;
	private VisibilityBarricadaListener visibility;
	
	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;
	
	public Barricada(int group, double latitude, double longitude) {
		this.groupId = group;
		this.latitude = latitude;
		this.longitude = longitude;
		
		proximity = new ProximityBarricadaListener();
		visibility = new VisibilityBarricadaListener();
		path = new DevicePath();
	}
	
	public Barricada(){
		this(1,0,0);
	}
	
	@Override
	public String toString() {
		return "Barricada [groupId=" + groupId
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the position
	 */
	public GeoPosition getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(GeoPosition position) {
		this.position = position;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the distanceOn
	 */
	public double getDistanceOn() {
		return distanceOn;
	}

	/**
	 * @param distanceOn the distanceOn to set
	 */
	public void setDistanceOn(double distanceOn) {
		this.distanceOn = distanceOn;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the path
	 */
	public DevicePath getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(DevicePath path) {
		this.path = path;
	}

	/**
	 * @return the proximity
	 */
	public ProximityBarricadaListener getProximity() {
		return proximity;
	}

	/**
	 * @param proximity the proximity to set
	 */
	public void setProximity(ProximityBarricadaListener proximity) {
		this.proximity = proximity;
	}

	/**
	 * @return the visibility
	 */
	public VisibilityBarricadaListener getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(VisibilityBarricadaListener visibility) {
		this.visibility = visibility;
	}

	@Override
	public void setGeoPosition(IGeoPosition position) {
		this.position = (GeoPosition) position;
		
	}

	@Override
	public IGeoPosition getGeoPosition() {
		return position;
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
	public void setGroup(Integer group) {
		this.groupId = group;
		
	}

	@Override
	public Integer getGroup() {
		return groupId;
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
	public void setProximityListener(IProximityListener listener) {
		this.proximity = (ProximityBarricadaListener) listener;
		
	}

	@Override
	public IProximityListener getProximityListener() {
		return proximity;
	}

	@Override
	public IVisibilityListener getVisibilityListener() {
		return visibility;
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

	@Override
	public void toXML(Element parent, Document doc) {
		Element element = doc.createElement("mina");
		if (parent == null) {
			doc.appendChild(element);
		} else {
			parent.appendChild(element);
		}
		// set attributes to barricada element		
		element.setAttribute("grupo", String.valueOf(this.getGroup()));
		element.setAttribute("latitude", String.valueOf(this.getLatitude()));
		element.setAttribute("longitude", String.valueOf(this.getLogintude()));
		
	}
	
	public void fromXML(String xmlString) throws Exception {
		Document doc = XMLParser.createXMLDocument(xmlString);

		if (doc != null) {
			NodeList nodes = doc.getElementsByTagName("barricada");
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
	}
}
