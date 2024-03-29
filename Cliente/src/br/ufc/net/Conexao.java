package br.ufc.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexao {

	public static String executaComando(String comando, String ip, int porta)
			throws UnknownHostException, IOException {
		Socket socket;
		OutputStream out;
		InputStream in;
		BufferedReader reader;
		DataOutputStream writer;
		String resposta;

		socket = new Socket(ip, porta);
		in = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(in));
		out = socket.getOutputStream();
		writer = new DataOutputStream(out);

		writer.writeBytes(comando);
		writer.write('\n');

		StringBuilder buffer = new StringBuilder();
		String line = reader.readLine();
//		System.out.println(line);
		if (line.equals("<response>")) {
			while (!(line = reader.readLine()).equals("</response>")) {
				buffer.append(line);
				buffer.append('\n');
			}
		}		
		
		resposta = buffer.toString();		
		return resposta;
	}
}