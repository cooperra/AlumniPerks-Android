package edu.rosehulman.roseperks;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PerksDetailView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_view);
		
		ImageView image =  (ImageView) findViewById(R.id.perk_image_detail);
		TextView name = (TextView) findViewById(R.id.perk_name_detail);
		TextView location = (TextView) findViewById(R.id.perk_location_detail);
		TextView number = (TextView) findViewById(R.id.perk_number_detail);
		TextView discount = (TextView) findViewById(R.id.perk_discount_detail);
		Button website_button = (Button) findViewById(R.id.perk_website_detail);
		Button coupon_button = (Button) findViewById(R.id.perk_coupon_detail);
		LinearLayout background = (LinearLayout) findViewById(R.id.perk_background_detail);
		
		Intent i = getIntent();
		String perk_name = i.getStringExtra("name");
		String perk_location = i.getStringExtra("location");
		String perk_number = i.getStringExtra("number");
		String perk_discount = i.getStringExtra("discount");
		final String perk_website = i.getStringExtra("website");
		String perk_image = i.getStringExtra("image");
		String perk_category = i.getStringExtra("category");
		final String perk_coupon = i.getStringExtra("coupon");
		
		name.setText(perk_name);
		location.setText(perk_location);
		number.setText(perk_number);
		discount.setText(perk_discount);
		
		int perk_background = getResources().getIdentifier(perk_category, "drawable", this.getPackageName());
		background.setBackgroundResource(perk_background);
		
		if (perk_category.equals("hotel")){
			name.setTextColor(Color.WHITE);
			location.setTextColor(Color.WHITE);
			number.setTextColor(Color.WHITE);
			discount.setTextColor(Color.WHITE);
		}
		
		if (perk_category.equals("car")){
			name.setTextColor(Color.WHITE);
			location.setTextColor(Color.WHITE);
			number.setTextColor(Color.WHITE);
			discount.setTextColor(Color.WHITE);
		}
		
		
		
		
		if (null != perk_website) {
			website_button.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View v) {
					Intent browserIntent =  
							new Intent(Intent.ACTION_VIEW, Uri.parse(perk_website));
					startActivity(browserIntent);
					
				}
				
			});
		} else {
			website_button.setVisibility(View.INVISIBLE);
		}
		
		if (null != perk_coupon) {
			coupon_button.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View v) {
					Intent browserIntent =  
							new Intent(Intent.ACTION_VIEW, Uri.parse(perk_coupon));
					startActivity(browserIntent);
					
				}
				
			});
		} else {
			coupon_button.setVisibility(View.INVISIBLE);
		}
		
		// Setting up the image
		Drawable imageDrawable;
		try {
			String uri = this.getApplicationContext().getFilesDir().getPath()
					+ File.separator
					+ perk_image;
			imageDrawable = Drawable.createFromPath(uri);
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Problem loading image", e);
			imageDrawable = this.getApplicationContext().getResources()
					.getDrawable(R.drawable.no_image);
		}
		image.setImageDrawable(imageDrawable);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (getIntent().getBooleanExtra("calledFromPerksList", false)) {
				finish();
				return true;
			}
		}

        return super.onOptionsItemSelected(item);
	}
}
