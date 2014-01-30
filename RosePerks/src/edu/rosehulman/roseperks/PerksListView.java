package edu.rosehulman.roseperks;
import android.app.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PerksListView extends Activity {
//	static final String URL = "companylist.xml";
	static final String KEY_TAG = "company";
	static final String KEY_NAME = "name";
	static final String KEY_ID = "id";
	static final String KEY_LOCATION = "location";
	static final String KEY_NUMBER = "number";
	static final String KEY_DISCOUNT = "discount";
	static final String KEY_NAME_IMAGE = "name_image";
	
	ListView list;
	PerksAdapter adapter;
	List<HashMap<String,String>> perksListCollection;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perks_list_items);
        
		try {
        
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse (getAssets().open("companyList.xml"));
        
        perksListCollection = new ArrayList<HashMap<String,String>>();
        
        doc.getDocumentElement().normalize();
        
        NodeList perksList = doc.getElementsByTagName("company");
        
        HashMap<String, String> map = null;
        
        for (int i = 0 ; i < perksList.getLength(); i++){
        	
        	map = new HashMap<String,String>();
        	
        	Node firstPerksNode = perksList.item(i);
        	
        	if (firstPerksNode.getNodeType() == Node.ELEMENT_NODE){
        		Element firstPerksElement = (Element)firstPerksNode;
        		NodeList idList = firstPerksElement.getElementsByTagName(KEY_ID);
        		Element firstIdElement = (Element)idList.item(0);
        		NodeList textIdList = firstIdElement.getChildNodes();
        		map.put(KEY_ID, ((Node)textIdList.item(0)).getNodeValue().trim());
        		
        		NodeList nameList = firstPerksElement.getElementsByTagName(KEY_NAME);
        		Element firstNameElement = (Element)nameList.item(0);
        		NodeList textNameList = firstNameElement.getChildNodes();
        		map.put(KEY_NAME, ((Node)textNameList.item(0)).getNodeValue().trim());
        		
        		NodeList locationList = firstPerksElement.getElementsByTagName(KEY_LOCATION);
        		Element firstLocationElement = (Element)locationList.item(0);
        		NodeList textLocationList = firstLocationElement.getChildNodes();
        		map.put(KEY_LOCATION, ((Node)textLocationList.item(0)).getNodeValue().trim());
        		
        		NodeList numberList = firstPerksElement.getElementsByTagName(KEY_NUMBER);
        		Element firstNumberElement = (Element)numberList.item(0);
        		NodeList textNumberList = firstNumberElement.getChildNodes();
        		map.put(KEY_NUMBER, ((Node)textNumberList.item(0)).getNodeValue().trim());
        		
        		NodeList discountList = firstPerksElement.getElementsByTagName(KEY_DISCOUNT );
        		Element firstDiscountElement = (Element)discountList.item(0);
        		NodeList textDiscountList = firstDiscountElement.getChildNodes();
        		map.put(KEY_DISCOUNT, ((Node)textDiscountList.item(0)).getNodeValue().trim());
        		
        		NodeList imageList = firstPerksElement.getElementsByTagName(KEY_NAME_IMAGE);
        		Element firstImageElement = (Element)imageList.item(0);
        		NodeList textImageList = firstImageElement.getChildNodes();
        		map.put(KEY_NAME_IMAGE, ((Node)textImageList.item(0)).getNodeValue().trim());
        		
        		perksListCollection.add(map);
        	}
        }
        PerksAdapter adapter = new PerksAdapter(this, perksListCollection);
        
        list = (ListView)findViewById(R.id.list);
        
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
		        Intent i = new Intent();
		        i.setClass(PerksListView.this, PerksAdapter.class);
		        i.putExtra("position", String.valueOf(position + 1));
		        i.putExtra("name", perksListCollection.get(position).get(KEY_NAME));
		        i.putExtra("location", perksListCollection.get(position).get(KEY_LOCATION));
		        i.putExtra("number", perksListCollection.get(position).get(KEY_NUMBER));
		        i.putExtra("discount", perksListCollection.get(position).get(KEY_DISCOUNT));
		        i.putExtra("name_image", perksListCollection.get(position).get(KEY_NAME_IMAGE));
		        startActivity(i);
			}
        	
		});
	}
		catch (IOException ex) {
			Log.e("Error", ex.getMessage());
		}
		catch (Exception ex) {
			Log.e("Error", "Loading exception");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
}
