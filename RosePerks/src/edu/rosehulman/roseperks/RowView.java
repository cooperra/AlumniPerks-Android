package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RowView extends LinearLayout {

	private Context mContext;
	private TextView mLeftTextView;
	private TextView mRightTextView;

	public RowView(Context context) {
		super(context);
		mContext = context;

		((Activity) context).getLayoutInflater().inflate(R.layout.row_view,
				this);

		mLeftTextView = (TextView) findViewById(R.id.left_text_view);
		mRightTextView = (TextView) findViewById(R.id.right_text_view);

	}

	public void setLeftText(String string) {
		mLeftTextView.setText(string);
	}

	public void setRightText(String string) {
		mRightTextView.setText(string);
	}

}
