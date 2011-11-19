package br.ufc.servidor;

import java.io.IOException;

import br.ufc.util.Conexao;

/*  Definição do comando:

 Uma classe deve ser criada, herdando de CommandExecute, que deve 
 realizar a funcionalidade desejada.
 Tem que ser registrado na classe ServerService, logo no início 
 do bloco estático.

 Exemplo: 

 cmd = new CmdSoma();
 CommandParser.addCommand("<soma>",0,cmd);

 <nome_do_comando> - tem que ser entre "<" e ">"
 Sempre separado por vírgulas
 Tem que iniciar e finalizar com o nome do comando, como uma tag XML
 Entre as tags incluir os parâmetros separados por vírgula

 Exemplo: 

 <soma>,3,5,<soma>

 Enviar via socket e recebe uma String como resposta.

 */

public class Cliente {

	public static void main(String[] args) throws IOException {
		String comando = "<soma>," + "a1234e" + ",<soma>";
		int porta = 8080;
		String ip = "localhost";

		String resposta = Conexao.executaComando(comando, ip, porta);
		System.out.println("Soma de strings = " + resposta);
	}



}