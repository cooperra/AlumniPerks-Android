package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class PerksListActivity extends Activity {
	String position = "1";
	String name = "";
	String location = "";
	String number = "";
	String discount = "";
	String name_image = "";
	ImageView imagePerk;
	
	TextView tvname;
	TextView tvlocation;
	TextView tvnumber;
	TextView tvdiscount;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perks_list);
        
        try{
        	imagePerk = (ImageView) findViewById(R.id.name_image);
        	tvname = (TextView) findViewById(R.id.name);
        	tvlocation = (TextView) findViewById(R.id.location);
        	tvnumber = (TextView) findViewById(R.id.number);
        	tvdiscount = (TextView) findViewById(R.id.discount);
        	
        	Intent i = getIntent();
        	
        	this.position = i.getStringExtra("position");
        	this.name = i.getStringExtra("name");
        	this.location = i.getStringExtra("location");
        	this.number = i.getStringExtra("number");
        	this.discount = i.getStringExtra("discount");
        	this.name_image = i.getStringExtra("name_image");
        	
        	String uri = "drawable-ldpi/" + name_image;
        	int imageViewResource = getResources().getIdentifier(uri, null, getPackageName());
        	Drawable dimgView = getResources().getDrawable(imageViewResource);
        	
        	tvname.setText(name);
        	tvlocation.setText(location);
        	tvnumber.setText(number);
        	tvdiscount.setText(discount);
        	
        	imagePerk.setImageDrawable(dimgView);
        }
        
        catch (Exception e){
        	Log.e("Error", "Loading exception");
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
}
