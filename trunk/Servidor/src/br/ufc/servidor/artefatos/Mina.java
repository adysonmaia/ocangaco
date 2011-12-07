package br.ufc.servidor.artefatos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.location.facade.IProximityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.listeners.ProximityMinaListener;
import br.ufc.location.listeners.VisibilityMinaListener;
import br.ufc.util.XMLParser;

public class Mina extends MobileDevice{
	private int damage;
	public static final int DEFAULT_TYPE = MINA;
	
	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;
	
	public static final int DAMAGE = 20;
	public static final int DEFAULT_DAMAGE = DAMAGE;
	

	public Mina(int group, double latitude, double longitude, int damage) {
		this.type = DEFAULT_TYPE;
		this.groupId = group;		
		this.latitude = latitude;
		this.longitude = longitude;
		this.damage = damage;
		
		proximity = new ProximityMinaListener();
		visibility = new VisibilityMinaListener();
		path = new DevicePath();
	}	

	public Mina() {
		this(DEFAULT_GROUP, 0, 0, DEFAULT_DAMAGE);
	}

	@Override
	public String toString() {
		return "Mina [damage=" + damage + ", groupId=" + groupId
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	@Override
	public void setProximityListener(IProximityListener listener) {
		this.proximity = (ProximityMinaListener) proximity;
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
		element.setAttribute("id", String.valueOf(this.getId()));
		element.setAttribute("grupo", String.valueOf(this.getGroup()));
		element.setAttribute("latitude", String.valueOf(this.getLatitude()));
		element.setAttribute("longitude", String.valueOf(this.getLongitude()));
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
		this.setId(Integer.parseInt(element.getAttribute("id")));
		this.setGroup(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
		this.setDamage(Integer.parseInt(element.getAttribute("damage")));
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}
	
	public Mina clone(){
		return new Mina();
	}

}
