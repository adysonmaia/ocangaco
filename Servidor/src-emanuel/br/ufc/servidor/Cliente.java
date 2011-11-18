package br.ufc.servidor;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.ufc.util.Conexao;

import myserver.kernel.CmdSoma;
import myserver.kernel.CommandExecute;
import myserver.kernel.CommandParser;
import myserver.kernel.ServerService;
import myserver.kernel.Service;

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
