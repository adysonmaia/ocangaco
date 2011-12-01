package br.ufc.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.util.XMLParser;

public class Mine extends MapObject {
	
	// Id da mina
	private int id;

	// Damage this mine causes
	private int damage;

	// ver Player.CANGACEIRO e Player.JAGUNCO
	private int tipo;

	public Mine(int id, int tipo, int damage, double latitude, double longitude) {
		super(latitude, longitude);
		this.id = id;
		this.tipo = tipo;
		this.damage = damage;
	}

	public Mine() {
		this(0, 0 , 0, 0, 0);
	}

	public Mine(int tipo, int damage, double latitude, double longitude) {
		this(0, tipo, damage, latitude, longitude);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
		element.setAttribute("grupo", String.valueOf(this.getTipo()));
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
		this.setTipo(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
		this.setDamage(Integer.parseInt(element.getAttribute("damage")));
	}
	
	@Override
	public String toString() {
		return "Mine [damage=" + damage + ", id=" + id + ", tipo=" + tipo
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + "]";
	}

	@Override
	public MapObject clone() {
		return new Mine();
	}	
}
