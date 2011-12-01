package br.ufc.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
	
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}	
	
	
}
