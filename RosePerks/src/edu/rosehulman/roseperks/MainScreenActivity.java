package edu.rosehulman.roseperks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.net.Uri;

public class MainScreenActivity extends FragmentActivity {
	private Button PerksList;
	private Button Help;
	private Button Category;
	private Button Google_Maps;
	private MenuItem Logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		PerksList = (Button) findViewById(R.id.perks);
		Help = (Button) findViewById(R.id.help);
		Category = (Button) findViewById(R.id.category);
		Google_Maps = (Button) findViewById(R.id.google_map);
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
						PerksListView.class);
				startActivity(DetailView);
			}
		});
		Help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ContactView = new Intent(getApplicationContext(),
						ContactDialogView.class);
				startActivity(ContactView);

			}
		});
		Google_Maps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent =  
						new Intent(Intent.ACTION_VIEW, Uri.parse("https://mapsengine.google.com/map/u/0/edit?mid=zRBarr6eawLk.kMs2XUUHErnM"));
				startActivity(browserIntent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);

		Logout = (MenuItem) menu.findItem(R.id.action_logout);
		Logout.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				CredentialManager.removeSessionKey(getApplicationContext());
				Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(loginIntent);
				finish();
				return true;
			}
		});
		return true;
	}

}
