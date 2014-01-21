package edu.rosehulman.roseperks;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PerksListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perks_list);
		ListView listView = (ListView) findViewById(R.id.list_view);

		ArrayList<String> names = new ArrayList<String>();
		names.add("Arda");
		names.add("Eric");

		final RowNumberAdapterPerksList rowNumberAdapter = new RowNumberAdapterPerksList(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(
						PerksListActivity.this,
						"You pressed row " + rowNumberAdapter.getItem(position),
						Toast.LENGTH_SHORT).show();

			}
		});

		// addButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// rowNumberAdapter.addView();
		// rowNumberAdapter.notifyDataSetChanged();
		//
		// }
		// });
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, names);
		listView.setAdapter(rowNumberAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perks_list, menu);
		return true;
	}

}
