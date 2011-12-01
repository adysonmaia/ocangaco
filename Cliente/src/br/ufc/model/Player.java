package br.ufc.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.util.XMLParser;



/**
 * @author Andr� Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player extends MapObject {
	
	public static final int CANGACEIRO = 1;
	public static final int JAGUNCO = 2;
	
	public static final int MUNICIADOR = 1;
	public static final int MEDICO     = 2;
	public static final int ENGENHEIRO = 3;
	public static final int ESPIAO     = 4;
	
	private String nome;
	private int tipo;
	private int papel;
	private Integer id;

	public Player(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Player(String nome, int tipo, int papel) {
		super();
		this.nome  = nome;
		this.tipo  = tipo;
		this.papel = papel;
	}

	public Player(String nome) {
		super();
		this.nome = nome;
	}

	public Player(String nome, int tipo, double latitude, double longitude) {
		super(latitude, longitude);
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Player(String nome, int tipo, int papel, double latitude, double longitude) {
		super(latitude, longitude);
		this.nome = nome;
		this.tipo = tipo;
		this.papel = papel;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getPapel() {
		return papel;
	}

	public void setPapel(int papel) {
		this.papel = papel;
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
		player.setAttribute("tipo", String.valueOf(this.getPapel()));
		player.setAttribute("grupo", String.valueOf(this.getTipo()));
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
		this.setId(Integer.parseInt(element.getAttribute("id")));
		this.setNome(element.getAttribute("nome"));
		this.setPapel(Integer.parseInt(element.getAttribute("tipo")));
		this.setTipo(Integer.parseInt(element.getAttribute("grupo")));
		this.setLatitude(Double.parseDouble(element
						.getAttribute("latitude")));
		this.setLongitude(Double.parseDouble(element
				.getAttribute("longitude")));
	}

	public void setId(Integer id) {
		this.id = id;		
	}
	
	public Integer getId()
	{
		return id;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", nome=" + nome + ", papel=" + papel
				+ ", tipo=" + tipo + ", getLatitude()=" + getLatitude()
				+ ", getLongitude()=" + getLongitude() + "]";
	}

	@Override
	public MapObject clone() {
		return new Player();
	}
	
	
}