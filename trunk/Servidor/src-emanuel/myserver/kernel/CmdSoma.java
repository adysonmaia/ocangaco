package myserver.kernel;

public class CmdSoma extends CommandExecute {

	public CmdSoma () {
	}	

	@Override
	public String execute(String[] param) {
		String valor;
		String resposta;
		
		valor = param[0];
		resposta = valor + valor;
		
		return resposta;
	}

}
