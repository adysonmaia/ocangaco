package br.ufc.activity;

import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.net.ServerFactory;
import br.ufc.util.Properties;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import br.ufc.model.Player;

public class FuncaoActivity extends Activity {

	private String nome;
	private int time;
	private String ip;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.funcao);

		Bundle bundle = this.getIntent().getExtras();
		nome = bundle.getString("nome");
		ip = bundle.getString("ip");
		time = bundle.getInt("time");

		TextView t = (TextView) findViewById(R.id.textViewFuncao);
		t.setText(nome + " do time " + time + " escolha sua funcao" + ip);

		ImageButton f1 = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton f2 = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton f3 = (ImageButton) findViewById(R.id.imageButton3);
		ImageButton f4 = (ImageButton) findViewById(R.id.imageButton4);

		f1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.MUNICIADOR);
			}
		});
		f2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.MEDICO);
			}
		});
		f3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.ENGENHEIRO);
			}
		});
		f4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.ESPIAO);
			}
		});
	}

	public void enviarIntent(int funcao) {
		Properties.SERVER_IP = ip;

		ClientGameState.myPlayerOnClient = new Player(nome, time, funcao);

		ServerFactory.getServer().connect(ClientGameState.myPlayerOnClient);

		Intent i = new Intent(this, ClienteActivity.class);
		startActivity(i);
	}
}
