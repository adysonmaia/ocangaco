package br.ufc.servidor.player;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.location.facade.IProximityListener;
import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.listeners.ProximityPlayerListener;
import br.ufc.location.listeners.VisibilityPlayerListener;
import br.ufc.servidor.artefatos.MobileDevice;
import br.ufc.util.XMLParser;

/**
 * @author Andre Fonteles, Rafael de Lima, Benedito Neto
 * 
 *         Class that defines a player
 * 
 */
public class Player extends MobileDevice {
	private String nome;
	private int life;

	public static final int DEFAULT_TIPO = SOLDIER;
	
	public static final int VIEW_DISTANCE = 100;
	public static final int COLISION_DISTANCE = 10;
	
	public static final int VIDA = 100;

	public Player(String nome, int tipo) {
		this(nome, DEFAULT_TIPO, 0 , 0);
	}

	public Player(String nome) {
		this(nome, DEFAULT_TIPO);
	}
	
	public Player(String nome, int tipo, double latitude, double longitude) {
		this(nome, tipo, DEFAULT_GROUP, latitude, longitude);
	}

	public Player(String nome, int tipo, int group, double latitude,
			double longitude) {
		this(nome, tipo, group, latitude, longitude, VIDA);
	}
	
	public Player(String nome, int tipo, int group, double latitude,
			double longitude, int life) {
		super();
		this.nome = nome;
		this.type = tipo;
		this.groupId = group;
		this.latitude = latitude;
		this.longitude = longitude;
		this.life = life;
		proximity = new ProximityPlayerListener();
		visibility = new VisibilityPlayerListener();
		path = new DevicePath();
	}

	public Player() {
		this("null");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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
	public String toString() {
		return "Player [life=" + life + ", nome=" + nome + ", groupId="
				+ groupId + ", latitude=" + latitude + ", longitude="
				+ longitude + ", type=" + type + "]";
	}

	public void toXML(Element parent, Document doc) {
		Element player = doc.createElement("player");
		if (parent == null) {
			doc.appendChild(player);
		} else {
			parent.appendChild(player);
		}
		// set attributes to player element
		player.setAttribute("id", String.valueOf(this.getId()));
		player.setAttribute("nome", String.valueOf(this.getNome()));
		player.setAttribute("tipo", String.valueOf(this.getType()));
		player.setAttribute("grupo", String.valueOf(this.getGroup()));
		player.setAttribute("latitude", String.valueOf(this.getLatitude()));
		player.setAttribute("longitude", String.valueOf(this.getLongitude()));
		player.setAttribute("vida", String.valueOf(this.getLife()));
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
		this.setId(Integer.parseInt(element.getAttribute("id")));
		this.setNome(element.getAttribute("nome"));
		this.setType(Integer.parseInt(element.getAttribute("tipo")));
		this.setGroup(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
		this.setLife(Integer.parseInt(element.getAttribute("vida")));
	}
	
	@Override
	public void setProximityListener(IProximityListener proximity) {
		this.proximity = (ProximityPlayerListener) proximity;
	}
	
	public Player clone(){
		return new Player();
	}
}