package br.ufc.business.commands;

import myserver.kernel.CommandExecute;
import myserver.kernel.CommandParser;

/**
 * @author iaufc37.lima Classe utilitária relacionada ao gerenciamento dos
 *         commandos 
 */
public class CommandFactory {
	static CommandExecute cmd;
	
	/**
	 * Método para registrar os comandos no parser de forma desacoplada da
	 * camada de comandos no pacote myserver.kernel . Todos os comandos
	 * utilizados no jogo devem ser registrados aqui.
	 */
	public static void Create(){
	 	cmd = new CmdMovimentacao();
	 	CommandParser.addCommand("<movimentacao>",3,cmd);		
	 	
	 	cmd = new CmdGameState();
	 	CommandParser.addCommand("<gamestate>",0,cmd);	
	 	
	 	cmd = new CmdDisconnect();
	 	CommandParser.addCommand("<disconnect>",1,cmd);	
	 	
	 	cmd = new CmdRegister();
	 	CommandParser.addCommand("<register>",0,cmd);
	 	
	 	cmd = new CmdUpdatePosition();	 	
	 	CommandParser.addCommand("<updateposition>",0,cmd);
	 	
	 	cmd = new CmdGetDevicesList();	 	
	 	CommandParser.addCommand("<deviceslist>",0,cmd);
	 	
	 	cmd = new CmdCriarMina();	 	
	 	CommandParser.addCommand("<criarmina>",0,cmd);
	 	
	 	cmd = new CmdCriarBarricada();	 	
	 	CommandParser.addCommand("<criarbarricada>",0,cmd);
	 	
	 	cmd = new CmdVisibleDevices();	 	
	 	CommandParser.addCommand("<visibledevices>",0,cmd);
	}
}
