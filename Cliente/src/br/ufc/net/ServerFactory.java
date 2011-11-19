package br.ufc.net;



/**
 * @author Andre
 *	
 * Class used to get instances of interface IServer.
 *
 */
public class ServerFactory {

	private static IServer server = null;
	
	public static IServer getServer() {
		if(server == null)
			server = new ServerImpl();
		
		return server;
	}
	
}
