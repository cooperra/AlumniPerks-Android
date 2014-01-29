package edu.rosehulman.roseperks;
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PerksListView extends Activity {
	static final String URL = "http://companylist.xml";
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
        
        ArrayList<HashMap<String, String>> companyList = new ArrayList<HashMap<String, String>>();
        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL);
        Document doc = parser.getDomElement(xml);
        
        NodeList n1 = doc.getElementsByTagName(KEY_NAME);
        for (int i = 0 ; i < n1.getLength(); i++){
        	HashMap<String, String> map = new HashMap<String, String>();
        	Element e = (Element) n1.item(i);
        	map.put(KEY_ID, parser.getValue(e, KEY_ID));
        	map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
        	map.put(KEY_LOCATION, parser.getValue(e, KEY_LOCATION));
        	map.put(KEY_NUMBER, parser.getValue(e, KEY_NUMBER));
        	map.put(KEY_DISCOUNT, parser.getValue(e, KEY_DISCOUNT));
        	map.put(KEY_NAME_IMAGE, parser.getValue(e, KEY_NAME_IMAGE));
        	
        	companyList.add(map);
        }
        
        list = (ListView)findViewById(R.id.list);
        
        adapter = new PerksAdapter(this, companyList);
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
