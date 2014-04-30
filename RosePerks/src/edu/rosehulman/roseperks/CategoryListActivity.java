package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CategoryListActivity extends Activity {
	private ImageButton Restaurant;
	private ImageButton Entertainment;
	private ImageButton Hotel;
	private ImageButton Banking;
	private ImageButton Stores;
	private ImageButton Other;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		Restaurant = (ImageButton) findViewById(R.id.restaurant_cat);
		Hotel = (ImageButton) findViewById(R.id.hotel_cat);
		Banking = (ImageButton) findViewById(R.id.banking_cat);
		Stores = (ImageButton) findViewById(R.id.stores_cat);
		Other = (ImageButton) findViewById(R.id.other_cat);
		
		Restaurant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent restaurantView = new Intent(getApplicationContext(),
						PerksListView.class);
				restaurantView.putExtra("category", "restaurant");
				startActivity(restaurantView);
				
			}
		});
		
		Hotel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent hotelView = new Intent(getApplicationContext(),
						PerksListView.class);
				hotelView.putExtra("category", "hotel");
				startActivity(hotelView);
				
			}
		});
		
		Banking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent bankingView = new Intent(getApplicationContext(),
						PerksListView.class);
				bankingView.putExtra("category", "banking");
				startActivity(bankingView);
				
			}
		});
		
		Stores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent storesView = new Intent(getApplicationContext(),
						PerksListView.class);
				storesView.putExtra("category", "store");
				startActivity(storesView);
				
			}
		});
		
		Other.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent otherView = new Intent(getApplicationContext(),
						PerksListView.class);
				otherView.putExtra("category", "other");
				startActivity(otherView);
				
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
