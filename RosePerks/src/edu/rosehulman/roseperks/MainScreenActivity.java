package edu.rosehulman.roseperks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreenActivity extends FragmentActivity {
	private Button PerksList;
	private Button Help;
	private Button Category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		PerksList = (Button) findViewById(R.id.perks_List);
		Help = (Button) findViewById(R.id.help);
		Category = (Button) findViewById(R.id.category);
		Category.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent CategoryView = new Intent(getApplicationContext(),
						CategoryListActivity.class);
				startActivity(CategoryView);
				
			}
		});
		PerksList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent DetailView = new Intent(getApplicationContext(),
						PerksListActivity.class);
				startActivity(DetailView);
			}
		});
		Help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogFragment dialog = new DialogFragmentHelp();
				dialog.show(getSupportFragmentManager(), "help");

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
