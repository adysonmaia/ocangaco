package br.ufc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import br.ufc.model.Player;

public class TimesActivity extends Activity {

	private String nome;
	private String ip;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.times);

		Bundle bundle = this.getIntent().getExtras();
		nome = bundle.getString("nome");
		ip = bundle.getString("ip");

		TextView t = (TextView) findViewById(R.id.textViewTimes);
		t.setText("Escolha seu time " + nome);

		ImageButton time1 = (ImageButton) findViewById(R.id.imageButtonTime1);
		time1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.CANGACEIRO);
			}
		});
		
		ImageButton time2 = (ImageButton) findViewById(R.id.imageButtonTime2);
		time2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarIntent(Player.JAGUNCO);
			}
		});
	}

	public void enviarIntent(int time) {
		Intent i = new Intent("funcao.activity");
		i.putExtra("nome", nome);
		i.putExtra("time", time);
		i.putExtra("ip", ip);
		startActivity(i);
	}

}
