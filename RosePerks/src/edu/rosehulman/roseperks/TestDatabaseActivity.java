package edu.rosehulman.roseperks;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class TestDatabaseActivity extends ListActivity {
	private PerkDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_test);

		datasource = new PerkDataSource(this);
		datasource.open();

		List<Perk> values = datasource.getAllPerks();

		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Perk> adapter = new ArrayAdapter<Perk>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Perk> adapter = (ArrayAdapter<Perk>) getListAdapter();
		Perk perk = null;
		switch (view.getId()) {
		case R.id.add:
			int nextInt = new Random().nextInt(3);
			// save the new comment to the database
			perk = datasource.createPerk("a", "b", "c", "d");
			adapter.add(perk);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				perk = (Perk) getListAdapter().getItem(0);
				datasource.deletePerk(perk);
				adapter.remove(perk);
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}