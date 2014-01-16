package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreenActivity extends Activity {
	private Button PerksList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		PerksList = (Button) findViewById(R.id.Button01);
		PerksList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent DetailView = new Intent(getApplicationContext(),
						PerksListActivity.class);
				startActivity(DetailView);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}

}
