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

import edu.rosehulman.roseperks.PerkStorage.HttpResponseException;
import edu.rosehulman.roseperks.PerkStorage.NetworkDisconnectedException;
import edu.rosehulman.roseperks.PerkStorage.PerkUpdateTask;
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

public class PerksListView extends Activity {
	// static final String URL = "companylist.xml";
	static final String KEY_TAG = "company";
	static final String KEY_NAME = "name";
	static final String KEY_ID = "id";
	static final String KEY_LOCATION = "location";
	static final String KEY_NUMBER = "number";
	static final String KEY_DISCOUNT = "discount";
	static final String KEY_NAME_IMAGE = "name_image";
	static final String KEY_WEBSITE = "website";

	ListView list;
	PerksAdapter adapter;
	List<HashMap<String, String>> perksListCollection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perks_list_items);

	    if (PerkStorage.isEmpty(this)) {
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
		
		PerkUpdateTask updater = new PerkStorage.PerkUpdateTask() {
			
			@Override
			public Activity getCallingActivity() {
				return thiss;
			}

			@Override
			public void onNetworkProblem(IOException e) {
				super.onNetworkProblem(e);
				// TODO: show toaster notification
			}

			@Override
			public void onNonOKHttpResponse(HttpResponseException e) {
				super.onNonOKHttpResponse(e);
				//TODO: show toaster notification
			}

			@Override
			public void onNoConnection(NetworkDisconnectedException e) {
				super.onNoConnection(e);
				//TODO: show toaster notification
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
		FileInputStream file = PerkStorage.getXMLFile(this);
		if (file == null) {
			Log.e(PerksListView.class.getSimpleName(), "File not found");
			return;
		}

		perksListCollection = new ArrayList<HashMap<String, String>>();
		
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder
					.parse(file);

			doc.getDocumentElement().normalize();

			NodeList perksList = doc.getElementsByTagName("company");

			if (perksList != null && perksList.getLength() > 0) {
				perksListCollection.clear();
				int len = perksList.getLength();

				for (int i = 0; i < len; i++) {

					HashMap<String, String> map = new HashMap<String, String>();

					Node firstPerksNode = perksList.item(i);

					// TODO: check for nulls that occur when fields are missing
					Element firstPerksElement = (Element) firstPerksNode;
					NodeList idList = firstPerksElement
							.getElementsByTagName(KEY_ID);
					Element firstIdElement = (Element) idList.item(0);
					NodeList textIdList = firstIdElement.getChildNodes();
					map.put(KEY_ID, ((Node) textIdList.item(0)).getNodeValue()
							.trim());

					NodeList nameList = firstPerksElement
							.getElementsByTagName(KEY_NAME);
					Element firstNameElement = (Element) nameList.item(0);
					NodeList textNameList = firstNameElement.getChildNodes();
					map.put(KEY_NAME, ((Node) textNameList.item(0))
							.getNodeValue().trim());

					NodeList locationList = firstPerksElement
							.getElementsByTagName(KEY_LOCATION);
					Element firstLocationElement = (Element) locationList
							.item(0);
					NodeList textLocationList = firstLocationElement
							.getChildNodes();
					map.put(KEY_LOCATION, ((Node) textLocationList.item(0))
							.getNodeValue().trim());

					NodeList numberList = firstPerksElement
							.getElementsByTagName(KEY_NUMBER);
					Element firstNumberElement = (Element) numberList.item(0);
					NodeList textNumberList = firstNumberElement
							.getChildNodes();
					map.put(KEY_NUMBER, ((Node) textNumberList.item(0))
							.getNodeValue().trim());

					NodeList discountList = firstPerksElement
							.getElementsByTagName(KEY_DISCOUNT);
					Element firstDiscountElement = (Element) discountList
							.item(0);
					NodeList textDiscountList = firstDiscountElement
							.getChildNodes();
					map.put(KEY_DISCOUNT, ((Node) textDiscountList.item(0))
							.getNodeValue().trim());

					NodeList imageList = firstPerksElement
							.getElementsByTagName(KEY_NAME_IMAGE);
					Element firstImageElement = (Element) imageList.item(0);
					NodeList textImageList = firstImageElement.getChildNodes();
					map.put(KEY_NAME_IMAGE, ((Node) textImageList.item(0))
							.getNodeValue().trim());
					
					NodeList websiteList = firstPerksElement
							.getElementsByTagName(KEY_WEBSITE);
					Element firstWebsiteElement = (Element) websiteList.item(0);
					NodeList textWebsiteList = firstWebsiteElement.getChildNodes();
					map.put(KEY_WEBSITE, ((Node) textWebsiteList.item(0))
							.getNodeValue().trim());

					perksListCollection.add(map);

				}
			}
		} catch (IOException ex) {
			Log.e("Error", ex.getMessage(), ex);
		} catch (Exception ex) {
			Log.e("Error", "Loading exception", ex);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				Log.e("Error", "Problem closing file", e);
			}
		}
		PerksAdapter adapter = new PerksAdapter(this, perksListCollection);

		list = (ListView) findViewById(R.id.list);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
//					Intent i = new Intent();
//					i.setClass(PerksListView.this, PerksAdapter.class);
//					i.putExtra("position", String.valueOf(position + 1));
//					i.putExtra("name",
//							perksListCollection.get(position).get(KEY_NAME));
//					i.putExtra("location", perksListCollection.get(position)
//							.get(KEY_LOCATION));
//					i.putExtra("number",
//							perksListCollection.get(position).get(KEY_NUMBER));
//					i.putExtra("discount", perksListCollection.get(position)
//							.get(KEY_DISCOUNT));
//					i.putExtra("name_image", perksListCollection.get(position)
//							.get(KEY_NAME_IMAGE));
//					startActivity(i);
				Intent browserIntent =  
						new Intent(Intent.ACTION_VIEW, Uri.parse(perksListCollection.get(position).get(KEY_WEBSITE)));
				startActivity(browserIntent);
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
//	public void DownloadDatabase(String DownloadUrl, String fileName) {
//	    try {
//	        File root = android.os.Environment.getExternalStorageDirectory();
//	        File dir = new File(root.getAbsolutePath());
//	        if(dir.exists() == false){
//	             dir.mkdirs();  
//	        }
//
//	        URL url = new URL(DownloadUrl);
//	        File file = new File(dir,fileName);
//
//	        long startTime = System.currentTimeMillis();
//	        Log.d("DownloadManager" , "download url:" +url);
//	        Log.d("DownloadManager" , "download file name:" + fileName);
//
//	        URLConnection uconn = url.openConnection();
//	        uconn.setReadTimeout(1000);
//	        uconn.setConnectTimeout(1000);
//
//	        InputStream is = uconn.getInputStream();
//	        BufferedInputStream bufferinstream = new BufferedInputStream(is);
//
//	        ByteArrayBuffer baf = new ByteArrayBuffer(5000);
//	        int current = 0;
//	        while((current = bufferinstream.read()) != -1){
//	            baf.append((byte) current);
//	        }
//
//	        FileOutputStream fos = new FileOutputStream( file);
//	        fos.write(baf.toByteArray());
//	        fos.flush();
//	        fos.close();
//	        Log.d("DownloadManager" , "download ready in" + ((System.currentTimeMillis() - startTime)/1000) + "sec");
//	        int dotindex = fileName.lastIndexOf('.');
//	        if(dotindex>=0){
//	            fileName = fileName.substring(0,dotindex);
//	        }
//	    }
//	    catch(IOException e) {
//	        Log.d("DownloadManager" , "Error:" + e);
//	    }
//
//	}
}
