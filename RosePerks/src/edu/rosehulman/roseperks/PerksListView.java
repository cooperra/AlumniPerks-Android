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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PerksListView extends Activity {
//	static final String URL = "companylist.xml";
	static final String KEY_NAME = "name";
	static final String KEY_ID = "id";
	static final String KEY_LOCATION = "location";
	static final String KEY_NUMBER = "number";
	static final String KEY_DISCOUNT = "discount";
	static final String KEY_NAME_IMAGE = "name image";
	
	ListView list;
	PerksAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perks_list_items);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Document doc = null;
		try {
			doc = docBuilder.parse (getAssets().open("companyList.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<HashMap<String,String>> perksListCollection = new ArrayList<HashMap<String,String>>();
        
        doc.getDocumentElement().normalize();
        
        NodeList perksList = doc.getElementsByTagName("companyData");
        
        for (int i = 0 ; i < perksList.getLength(); i++){
        	HashMap<String, String> map = new HashMap<String, String>();
        	
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
				// TODO Auto-generated method stub
				
			}
        	
		});
	}
}
