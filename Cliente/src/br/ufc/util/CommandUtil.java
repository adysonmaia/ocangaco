package br.ufc.util;


public class CommandUtil {	
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
