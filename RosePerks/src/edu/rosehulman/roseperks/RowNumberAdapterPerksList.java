package edu.rosehulman.roseperks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RowNumberAdapterPerksList extends BaseAdapter {
	private Context mContext;
	private int mCount;
	private String[] mMonthNames;

	public RowNumberAdapterPerksList(Context context) {
		mContext = context;
		mCount = 10;
		mMonthNames = context.getResources().getStringArray(
				R.array.food_stores_names);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCount;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mMonthNames[position % 12];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RowView view = null;
		if (convertView == null) {
			view = new RowView(mContext);
			// view.setBackground(mContext.getResources().getDrawable(R.drawable.ic_launcher));
		} else {
			view = (RowView) convertView;
		}
		// view.setText("Row " + position);
		// view.setBackgroundColor(Color.rgb(position * 10, 255 - position * 10,
		// 200));
		// view.setBackgroundColor(Color.HSVToColor(new float[] {(position *
		// 10)%360, 1, 1}));
		view.setLeftText("" + (position + 1) + ". ");
		view.setRightText(this.mMonthNames[position % 12]);
		return view;
	}

	public void addView() {
		this.mCount++;
	}
}
