package edu.rosehulman.roseperks;

import android.app.Activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.util.ByteArrayBuffer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import edu.rosehulman.roseperks.PerkUpdater.HttpResponseException;
import edu.rosehulman.roseperks.PerkUpdater.NetworkDisconnectedException;
import edu.rosehulman.roseperks.PerkUpdater.PerkUpdateTask;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PerksListView extends Activity {

	ListView list;
	PerkDataSource pds;
	PerksAdapter adapter;
	List<Perk> perksListCollection;
	private String categoryFilter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perks_list_items);

		Intent i = getIntent();
		categoryFilter = i.getStringExtra("category"); //TODO support more complex filters
		// categoryFilter is null if there is no filter
		if (categoryFilter != null) {
			this.setTitle(categoryFilter);
		}

		this.pds = new PerkDataSource(this);
	    if (pds.isEmpty()) {
	    	// attempt to refresh perks
	    	startPerkRefresh();
	    	// TODO: assume that it didn't work show retry button
	    	// (if it does work, retry button will be hidden automatically)
	    	showRetryButton();
	    } else {
	    	loadPerks();
	    }
	}

	public void onPerkRefreshButton(MenuItem m) {
		startPerkRefresh();
	}
	
	/**
	 * Downloads new perk data
	 */
	private void startPerkRefresh() {
		// TODO: don't update if update already in progress
		final Activity thiss = this;
		
		PerkUpdateTask updater = new PerkUpdater.PerkUpdateTask() {
			
			@Override
			public Activity getCallingActivity() {
				return thiss;
			}

			@Override
			public void onNetworkProblem(IOException e) {
				super.onNetworkProblem(e);
				// show toaster notification
				Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_refresh_network_problem, Toast.LENGTH_SHORT);
				toast.show();
			}

			@Override
			public void onNonOKHttpResponse(HttpResponseException e) {
				super.onNonOKHttpResponse(e);
				// show toaster notification
				Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_refresh_server_problem, Toast.LENGTH_SHORT);
				toast.show();
			}

			@Override
			public void onNoConnection(NetworkDisconnectedException e) {
				super.onNoConnection(e);
				// show toaster notification
				Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_refresh_no_connection, Toast.LENGTH_LONG);
				toast.show();
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				showProgressView();
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				hideProgressView();
				if (result) {
					loadPerks();
					Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_refresh_done, Toast.LENGTH_SHORT);
					toast.show();
				}
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
				// This is where a progress bar could be controlled
				// TODO: implement or remove stub
			}
		};
		
		updater.execute();
	}

	/**
	 * Populates the ListView with perks from the XML
	 */
	private void loadPerks() {
		ArrayList<Perk> perkList = pds.getAllPerksViaXML();
		if (perkList == null) {
			return;
		} else {
			// filter perks by category if needed
			if (categoryFilter != null) {
				for (int i=0; i < perkList.size(); i++) {
					if (!categoryFilter.equals(perkList.get(i).getPerkCategory())) {
						perkList.remove(i);
						i--;
					}
				}
			}
			perksListCollection = perkList;
		}
		PerksAdapter adapter = new PerksAdapter(this, perksListCollection);

		list = (ListView) findViewById(R.id.list);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(PerksListView.this, PerksDetailView.class);
				String name = perksListCollection.get(position).getCompanyName();
				String location = perksListCollection.get(position).getCompanyAddress();
				String number= perksListCollection.get(position).getCompanyPhone();
				String discount = perksListCollection.get(position).getPerkDescription();
				String website =perksListCollection.get(position).getPerkWebsite();
				String image =perksListCollection.get(position).getPerkImage();
				String category = perksListCollection.get(position).getPerkCategory();
				String coupon = perksListCollection.get(position).getPerkCoupon();
				
				i.putExtra("name", name);
				i.putExtra("location", location);
				i.putExtra("number", number);
				i.putExtra("discount", discount);
				i.putExtra("website", website);
				i.putExtra("image", image);
				i.putExtra("category", category);
				i.putExtra("coupon", coupon);
				
				startActivity(i);
				
			}

		});
		
		if (list.getChildCount() > 0) {
			hideRetryButton();
		}
	}

	protected void showProgressView() {
		// TODO Auto-generated method stub
		
	}

	protected void hideProgressView() {
		// TODO Auto-generated method stub
		
	}

	private void showRetryButton() {
		// TODO Auto-generated method stub
		
	}

	private void hideRetryButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.perks_list, menu);
		return true;
	}
}
