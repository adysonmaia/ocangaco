package br.ufc.business.commands;

import myserver.kernel.CommandExecute;
import myserver.kernel.CommandParser;

public class CommandFactory {
	static CommandExecute cmd;
	
	public static void Create(){
		cmd = new CmdSoma();      	
	 	CommandParser.addCommand("<soma>",0,cmd);
	 	
	 	cmd = new CmdMovimentacao();
	 	CommandParser.addCommand("<movimentacao>",3,cmd);		
	}
}
