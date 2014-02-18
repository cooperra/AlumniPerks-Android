package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		Entertainment = (ImageButton) findViewById(R.id.entertainment_cat);
		Hotel = (ImageButton) findViewById(R.id.hotel_cat);
		Banking = (ImageButton) findViewById(R.id.banking_cat);
		Stores = (ImageButton) findViewById(R.id.stores_cat);
		Other = (ImageButton) findViewById(R.id.other_cat);
		
		Restaurant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent restaurantView = new Intent(getApplicationContext(),
						RestaurantListView.class);
				startActivity(restaurantView);
				
			}
		});
		
		Entertainment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent entertainmentView = new Intent(getApplicationContext(),
						EntertainmentListView.class);
				startActivity(entertainmentView);
				
			}
		});
		
		Hotel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent hotelView = new Intent(getApplicationContext(),
						HotelListView.class);
				startActivity(hotelView);
				
			}
		});
		
		Banking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
<<<<<<< HEAD
//				Intent bankingView = new Intent(getApplicationContext(),
//						BankingListView.class);
//				startActivity(bankingView);
=======
				Intent bankingView = new Intent(getApplicationContext(),
						BankingListView.class);
				startActivity(bankingView);
>>>>>>> b11ddb5ebf2c4dc60a5c94f1c97167356c193b88
				
			}
		});
		
		Stores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
<<<<<<< HEAD
//				Intent storesView = new Intent(getApplicationContext(),
//						StoresListView.class);
//				startActivity(storesView);
=======
				Intent storesView = new Intent(getApplicationContext(),
						StoresListView.class);
				startActivity(storesView);
>>>>>>> b11ddb5ebf2c4dc60a5c94f1c97167356c193b88
				
			}
		});
		
		Other.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
<<<<<<< HEAD
//				Intent otherView = new Intent(getApplicationContext(),
//						OtherListView.class);
//				startActivity(otherView);
=======
				Intent otherView = new Intent(getApplicationContext(),
						OtherListView.class);
				startActivity(otherView);
>>>>>>> b11ddb5ebf2c4dc60a5c94f1c97167356c193b88
				
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
