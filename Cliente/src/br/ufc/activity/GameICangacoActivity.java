package br.ufc.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameICangacoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button  config = (Button)findViewById(R.id.buttonConfig);

    	config.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent("config.activity");
				startActivity(i);
				
			}
		});
    }
}