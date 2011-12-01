package br.ufc.activity;

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

		okConfig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText ip = (EditText) findViewById(R.id.editTextIP);
				Intent i = new Intent("login.activity");
				i.putExtra("ip", ip.getText().toString());
				startActivity(i);
			}
		});

	}

}
