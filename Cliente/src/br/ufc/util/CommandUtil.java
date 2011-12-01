package br.ufc.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufc.model.MapObject;

public class CommandUtil {
	public static void setDevices(String string, String response, MapObject device) {
		Document doc = XMLParser.createXMLDocument(response);
        NodeList nodes = doc.getElementsByTagName(string);
        for (int i = 0; i < nodes.getLength(); i++) {        	
        	try {
        		MapObject aux = device.clone();
				aux.fromXML((Element)nodes.item(i));
				System.out.println(aux);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}
	
	public static String makeCommand(String name, String[] params, int nParams)
	{
		String comando = "<" + name + ">,";
		for (int i = 0; i < nParams; i++) {
			comando += params[i] + ",";
		}	
		if(nParams == 0){
			comando += ",";
		}
		comando += "<" + name + ">";
		
		return comando;
	}	
}
