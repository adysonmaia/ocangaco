package br.ufc.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.util.Properties;


public class MainActivity  extends Activity implements OnClickListener {
	
	private EditText editTextIP;
	private EditText editTextName;
	private EditText editTextTeam;
	private Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		editTextName = (EditText)findViewById(R.id.editText1);
		editTextTeam = (EditText)findViewById(R.id.editText2);
		editTextIP = (EditText)findViewById(R.id.editText3);
		
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Properties.SERVER_IP = editTextIP.getText().toString();

		ClientGameState.eu = new Player(
				editTextName.getText().toString(), 
				Integer.parseInt(editTextTeam.getText().toString()));
		
		Intent i = new Intent(this, ClienteActivity.class);
		startActivity(i);
	}
	
}