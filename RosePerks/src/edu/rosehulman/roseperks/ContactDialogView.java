package edu.rosehulman.roseperks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContactDialogView extends Activity {
	private Button Back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alunmi_contact);
		
		Back = (Button) findViewById(R.id.contact_button);
		
		Back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
				
			}
			
		});
	}
}
