package edu.rosehulman.roseperks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class PerksAdapter extends BaseAdapter {
	
	private Activity activity;
	private List<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;
	
	public PerksAdapter(Activity a, List<HashMap<String, String>> d){
		activity = a;
		data = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Setting up the view
		View vi = convertView;
		if(convertView == null){
			vi = inflater.inflate(R.layout.activity_perks_list, null);
		}
		TextView name = (TextView)vi.findViewById(R.id.name);
		TextView location = (TextView)vi.findViewById(R.id.location);
		TextView number = (TextView)vi.findViewById(R.id.number);
		TextView discount = (TextView)vi.findViewById(R.id.discount);
		ImageView name_image = (ImageView)vi.findViewById(R.id.name_image);
		
		HashMap<String, String> company = new HashMap<String, String>();
		company = data.get(position);
		
		name.setText(company.get(PerksListView.KEY_NAME));
		location.setText(company.get(PerksListView.KEY_LOCATION));
		number.setText(company.get(PerksListView.KEY_NUMBER));
		discount.setText(company.get(PerksListView.KEY_DISCOUNT));
		String uri = "drawable/"+ data.get(position).get(PerksListView.KEY_NAME_IMAGE);
		//Making the image
		int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(
		   uri, null, vi.getContext().getApplicationContext().getPackageName());
		Drawable image = vi.getContext().getResources().getDrawable(imageResource);
		name_image.setImageDrawable(image);
		return vi;
	}

}
