package br.ufc.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;
import br.ufc.net.ServerFactory;
import br.ufc.util.Properties;


public class MainActivity  extends Activity implements OnClickListener {
	
	private EditText editTextIP;
	private EditText editTextName;
	private Spinner spinnerTeam;
	private Spinner spinnerRole;
	private Button button;
	public static MainActivity activity;	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//		activity = this;
//		editTextName = (EditText)findViewById(R.id.editText1);
//		editTextIP = (EditText)findViewById(R.id.editText3);
//		editTextIP.setText(Properties.SERVER_IP);
//		
//		spinnerRole = (Spinner)findViewById(R.id.spinnerRole);
//		ArrayAdapter<CharSequence> adapterRole = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
//		adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerRole.setAdapter(adapterRole);
//		
//		spinnerTeam = (Spinner)findViewById(R.id.spinnerTeam);
//		ArrayAdapter<CharSequence> adapterTeam = ArrayAdapter.createFromResource(this, R.array.team_array, android.R.layout.simple_spinner_item);
//		adapterTeam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerTeam.setAdapter(adapterTeam);
//		
//		button = (Button)findViewById(R.id.button1);
//		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Properties.SERVER_IP = editTextIP.getText().toString();
		int role = spinnerRole.getSelectedItemPosition() +1;
		int team = spinnerTeam.getSelectedItemPosition() +1;

		ClientGameState.myPlayerOnClient = new Player(
				editTextName.getText().toString(), 
				team, role);
		
		ServerFactory.getServer().connect(ClientGameState.myPlayerOnClient);
		
		Intent i = new Intent(this, ClienteActivity.class);
		startActivity(i);
	}
	
}