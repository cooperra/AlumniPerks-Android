package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	private Button Login_Button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Login_Button = (Button) findViewById(R.id.Loginbutton);
		Login_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent DetailView = new Intent(getApplicationContext(),
						MainScreenActivity.class);
				startActivity(DetailView);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
