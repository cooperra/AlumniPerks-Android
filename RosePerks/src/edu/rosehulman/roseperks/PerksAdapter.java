package edu.rosehulman.roseperks;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.io.FileNotFoundException;
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
	List<Perk> perkListCollection;
	ViewHolder holder;
	
	public PerksAdapter(){
		
	}
	
	public PerksAdapter(Activity a, List<Perk> d){
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
		holder.tvname.setText(perkListCollection.get(position).getCompanyName());
		holder.tvlocation.setText(perkListCollection.get(position).getCompanyAddress());
		holder.tvnumber.setText(perkListCollection.get(position).getCompanyPhone());
		holder.tvdiscount.setText(perkListCollection.get(position).getPerkDescription());
		
		//Setting up the image
		Drawable image;
		try {
			String uri = vi.getContext().getFilesDir().getPath() + File.separator+ perkListCollection.get(position).getPerkImage();
			image = Drawable.createFromPath(uri);
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Problem loading image", e);
			image = vi.getContext().getResources().getDrawable(R.drawable.no_image);
		}
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


