package br.ufc.util;

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
		resposta = reader.readLine();
		return resposta;
	}
}