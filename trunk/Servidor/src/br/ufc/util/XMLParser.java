package br.ufc.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.ufc.servidor.player.Player;

/**
 * 
 * @author rafael
 * 
 * Classe utilitaria para manipulação das mensagens XML trocadas entre cliente e servidor	
 */
public class XMLParser {

	static DocumentBuilderFactory docFactory; 
	static DocumentBuilder docBuilder;	
	
	/**
	 * @return Document xml simples para criação de resposta em XML
	 */
	public static Document createXMLDocument() 
	{
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
	 * @return Document xml que poderá ser manipulado para criação da resposta em xml
	 */
	public static Document createXMLDocument(String xmlString) {
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
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
	
	/**
	 * @param doc
	 * @return String contendo o xml gerado a partir do document passado como parametro <br>
	 * Ex return: {@code <player grupo="2" latitude="2.2" longitude="3.2" nome="a" tipo="1"/> }
	 */
	public static String getXMLString(Document doc) {
		try {
			// set up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans;

			trans = transfac.newTransformer();

			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			
			return sw.toString();
		}

		catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
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
	
	/**
	 * Metodo de testes para os metodos toXML e fromXML da classe player
	 * @param args
	 * @throws Exception	  
	 */
	public static void main(String[] args) throws Exception {
		Player p = new Player("a", 1, 2, 2.2, 3.2);
		String xml = null;
		try {
			xml = p.toXML();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(xml);
		p = new Player("b", 2, 3, 2.1, 2.4);
		System.out.println("before: " + p.toString());
		p.fromXML(xml);
		System.out.println("after: " + p.toString());
	}
}
