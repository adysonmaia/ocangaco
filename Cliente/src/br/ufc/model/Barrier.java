package br.ufc.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.util.XMLParser;

public class Barrier extends MapObject {
	
	// Id da barricada
	private int id;

	// ver Player.CANGACEIRO e Player.JAGUNCO
	private int tipo;

	public Barrier(int id, int tipo, double latitude, double longitude) {
		super(latitude, longitude);
		this.id = id;
		this.tipo = tipo;
	}

	public Barrier(int tipo2, double latitude, double longitude) {
		this(0, tipo2, latitude, longitude);
	}

	public Barrier() {
		this(1,0,0);
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

	@Override
	public String toString() {
		return "Barrier [id=" + id + ", tipo=" + tipo + ", getLatitude()="
				+ getLatitude() + ", getLongitude()=" + getLongitude() + "]";
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
		element.setAttribute("grupo", String.valueOf(this.getTipo()));
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
		this.setTipo(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
	}

	@Override
	public MapObject clone() {
		return new Barrier();
	}
}
