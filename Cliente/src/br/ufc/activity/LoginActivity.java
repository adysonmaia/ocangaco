package br.ufc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private String ip = "";
	private String port = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    
	    Bundle b = this.getIntent().getExtras();
	    ip = b.getString("ip");
	    port = b.getString("port");
	    
	    Button okLogin = (Button)findViewById(R.id.buttonOKLogin);
	    okLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nome = (EditText)findViewById(R.id.editTextNome);
				if(nome.getText().toString().length()>0){
					Intent i = new Intent("times.activity");
					i.putExtra("nome", nome.getText().toString());
					i.putExtra("ip", ip);
					i.putExtra("port", port);
					startActivity(i);
				}
			}
		});
	}

}
