package br.ufc.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.ufc.model.MapObject;
import br.ufc.model.Mine;
import br.ufc.model.Player;

/**
 * 
 * @author rafael
 * 
 *         Classe utilitaria para manipulação das mensagens XML trocadas entre
 *         cliente e servidor
 */
public class XMLParser {

	static DocumentBuilderFactory docFactory;
	static DocumentBuilder docBuilder;

	/**
	 * @return Document xml simples para criação de resposta em XML
	 */
	public static Document createXMLDocument() {
		docFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docFactory.newDocumentBuilder();
			return docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param xmlString
	 * @return Document xml que poderá ser manipulado para criação da resposta
	 *         em xml
	 */
	public static Document createXMLDocument(String xmlString) {
		try {
			docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			return docBuilder.parse(is);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	public static List<MapObject> getDevices(String string, String response,
			MapObject device) {
		List<MapObject> devices = new ArrayList<MapObject>();
		Document doc = XMLParser.createXMLDocument(response);
		NodeList nodes = doc.getElementsByTagName(string);		
		
		if (nodes != null && nodes.getLength() > 0) {
			for (int i = 0; i < nodes.getLength(); i++) {
				try {
					MapObject aux = device.clone();
					aux.fromXML((Element) nodes.item(i));
					System.out.println("Returned " + string + ": " + aux);
					devices.add(aux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("No " + string + "s returned.");
		}

		return devices;
	}

	public ArrayList<Player> getPlayers(String response) {
		ArrayList<Player> players = new ArrayList<Player>();
		Document doc = XMLParser.createXMLDocument(response);
		NodeList nodes = doc.getElementsByTagName("player");

		if (nodes != null && nodes.getLength() > 0) {
			for (int i = 0; i < nodes.getLength(); i++) {
				try {
					Player player = new Player();
					player.fromXML((Element) nodes.item(i));
					players.add(player);
					System.out.println(player);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("No players returned.");
		}

		return players;
	}

	public ArrayList<Mine> getMinas(String response) {
		ArrayList<Mine> minas = new ArrayList<Mine>();
		Document doc = XMLParser.createXMLDocument(response);
		NodeList nodes = doc.getElementsByTagName("mina");
		
		if(nodes != null && nodes.getLength() > 0){
		for (int i = 0; i < nodes.getLength(); i++) {
			try {
				Mine mina = new Mine();
				mina.fromXML((Element) nodes.item(i));
				minas.add(mina);
				System.out.println(mina);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		else
		{
			System.out.println("No mines returned.");
		}

		return minas;
	}
}
