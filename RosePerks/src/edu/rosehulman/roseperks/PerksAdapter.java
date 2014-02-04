package edu.rosehulman.roseperks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class PerksAdapter extends BaseAdapter {
	
	static final String KEY_TAG = "company";
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_LOCATION = "location";
	static final String KEY_NUMBER = "number";
	static final String KEY_DISCOUNT = "discount";
	static final String KEY_NAME_IMAGE = "name_image";
	LayoutInflater inflater;
	ImageView imagePerk;
	List<HashMap<String,String>> perkListCollection;
	ViewHolder holder;
	
	public PerksAdapter(){
		
	}
	public PerksAdapter(Activity a, List<HashMap<String, String>> d){
		this.perkListCollection = d;
		inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return this.perkListCollection.size();
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
			holder = new ViewHolder();
			holder.tvname = (TextView)vi.findViewById(R.id.name);
			holder.tvlocation = (TextView)vi.findViewById(R.id.location);
			holder.tvnumber = (TextView)vi.findViewById(R.id.number);
			holder.tvdiscount = (TextView)vi.findViewById(R.id.discount);
			holder.tvName_image = (ImageView)vi.findViewById(R.id.name_image);
		
			vi.setTag(holder);
		} else {
			holder = (ViewHolder)vi.getTag();
		}
		//Setting up the values of Text
		holder.tvname.setText(perkListCollection.get(position).get(KEY_NAME));
		holder.tvlocation.setText(perkListCollection.get(position).get(KEY_LOCATION));
		holder.tvnumber.setText(perkListCollection.get(position).get(KEY_NUMBER));
		holder.tvdiscount.setText(perkListCollection.get(position).get(KEY_DISCOUNT));
		
		//Setting up the image
		String uri = "drawable/"+ perkListCollection.get(position).get(KEY_NAME_IMAGE);
		int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(
		   uri, null, vi.getContext().getApplicationContext().getPackageName());
		Drawable image = vi.getContext().getResources().getDrawable(imageResource);
		holder.tvName_image.setImageDrawable(image);
		return vi;
	}
	
	static class ViewHolder{
		TextView tvname;
		TextView tvlocation;
		TextView tvnumber;
		TextView tvdiscount;
		ImageView tvName_image;
	}
	
}


