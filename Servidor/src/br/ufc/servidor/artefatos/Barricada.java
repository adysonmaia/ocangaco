package br.ufc.servidor.artefatos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.location.facade.IProximityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.listeners.ProximityBarricadaListener;
import br.ufc.location.listeners.VisibilityBarricadaListener;
import br.ufc.util.XMLParser;

public class Barricada extends MobileDevice{
	public static final int DEFAULT_TYPE = BARRICADA;
	
	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;
	
	public Barricada(int group, double latitude, double longitude) {
		this.type = DEFAULT_TYPE;
		this.groupId = group;
		this.latitude = latitude;
		this.longitude = longitude;
		
		proximity = new ProximityBarricadaListener();
		visibility = new VisibilityBarricadaListener();
		path = new DevicePath();
	}
	
	public Barricada(){
		this(DEFAULT_GROUP,0,0);
	}
	
	@Override
	public String toString() {
		return "Barricada [groupId=" + groupId
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public void setProximityListener(IProximityListener listener) {
		this.proximity = (ProximityBarricadaListener) listener;
		
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
		Element element = doc.createElement("barricada");
		if (parent == null) {
			doc.appendChild(element);
		} else {
			parent.appendChild(element);
		}
		// set attributes to barricada element	
		element.setAttribute("id", String.valueOf(this.getId()));
		element.setAttribute("grupo", String.valueOf(this.getGroup()));
		element.setAttribute("latitude", String.valueOf(this.getLatitude()));
		element.setAttribute("longitude", String.valueOf(this.getLongitude()));
		
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
		this.setId(Integer.parseInt(element.getAttribute("id")));
		this.setGroup(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
	}
	
	public Barricada clone(){
		return new Barricada();
	}
}
