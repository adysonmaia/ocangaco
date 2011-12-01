package br.ufc.activity;

import br.ufc.util.Properties;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);

		Button okConfig = (Button) findViewById(R.id.buttonOKConfig);
		EditText ip = (EditText) findViewById(R.id.editTextIP);
		EditText port = (EditText) findViewById(R.id.EditTextPort);
		ip.setText(Properties.SERVER_IP.toString());
		port.setText((new Integer(Properties.SERVER_PORT)).toString());

		okConfig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText ip = (EditText) findViewById(R.id.editTextIP);
				EditText port = (EditText) findViewById(R.id.EditTextPort);
				Intent i = new Intent("login.activity");
				i.putExtra("ip", ip.getText().toString());
				i.putExtra("port", port.getText().toString());
				startActivity(i);
			}
		});

	}

}
