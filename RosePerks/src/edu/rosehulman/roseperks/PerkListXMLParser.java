package edu.rosehulman.roseperks;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class PerkListXMLParser {
	static final String KEY_TAG = "company";
	static final String KEY_NAME = "name";
	static final String KEY_ID = "id";
	static final String KEY_LOCATION = "location";
	static final String KEY_NUMBER = "number";
	static final String KEY_DISCOUNT = "discount";
	static final String KEY_WEBSITE = "website";
	static final String KEY_CATEGORY = "category";
	static final String KEY_COUPON = "coupon";
	
	public static ArrayList<Perk> parsePerkXML(InputStream stream) {
		// TODO: tidy extracted code
		

		ArrayList<Perk> perksListCollection = new ArrayList<Perk>();
		
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder
					.parse(stream);

			doc.getDocumentElement().normalize();

			NodeList perksList = doc.getElementsByTagName("company");

			if (perksList != null && perksList.getLength() > 0) {
				perksListCollection.clear();
				int len = perksList.getLength();

				for (int i = 0; i < len; i++) {

					Perk perk = new Perk();

					Node firstPerksNode = perksList.item(i);

					try {
						Log.d("HERPDERP", firstPerksNode.getNodeName());
						Log.d("HERPDERP", "id="+((Element) firstPerksNode).getAttribute("id"));
					} catch (Exception e) {
						Log.d("HERPDERP", "uh oh "+e.getMessage());
					} finally {
						Log.d("HERPDERP", "grumble");
					}
					
					// TODO: check for nulls that occur when fields are missing
					Element firstPerksElement = (Element) firstPerksNode;
					
					String idString = firstPerksElement.getAttribute(KEY_ID);
					perk.setId(Long.parseLong(idString));

					NodeList nameList = firstPerksElement
							.getElementsByTagName(KEY_NAME);
					Element firstNameElement = (Element) nameList.item(0);
					NodeList textNameList = firstNameElement.getChildNodes();
					perk.setCompanyName( ((Node) textNameList.item(0))
							.getNodeValue().trim());

					// TODO: allow many categories
					NodeList categoryList = firstPerksElement
							.getElementsByTagName(KEY_CATEGORY);
					Element firstCategoryElement = (Element) categoryList.item(0);
					NodeList textCategoryList = firstCategoryElement.getChildNodes();
					perk.setPerkCategory( ((Node) textCategoryList.item(0))
							.getNodeValue().trim());

					NodeList locationList = firstPerksElement
							.getElementsByTagName(KEY_LOCATION);
					Element firstLocationElement = (Element) locationList
							.item(0);
					NodeList textLocationList = firstLocationElement
							.getChildNodes();
					perk.setCompanyAddress( ((Node) textLocationList.item(0))
							.getNodeValue().trim());

					NodeList numberList = firstPerksElement
							.getElementsByTagName(KEY_NUMBER);
					Element firstNumberElement = (Element) numberList.item(0);
					NodeList textNumberList = firstNumberElement
							.getChildNodes();
					perk.setCompanyPhone( ((Node) textNumberList.item(0))
							.getNodeValue().trim());

					NodeList discountList = firstPerksElement
							.getElementsByTagName(KEY_DISCOUNT);
					Element firstDiscountElement = (Element) discountList
							.item(0);
					NodeList textDiscountList = firstDiscountElement
							.getChildNodes();
					perk.setPerkDescription( ((Node) textDiscountList.item(0))
							.getNodeValue().trim());
					
					NodeList websiteList = firstPerksElement
							.getElementsByTagName(KEY_WEBSITE);
					if (websiteList.getLength() > 0) {
						Element firstWebsiteElement = (Element) websiteList.item(0);
						NodeList textWebsiteList = firstWebsiteElement.getChildNodes();
						perk.setPerkWebsite( ((Node) textWebsiteList.item(0))
								.getNodeValue().trim());
					}
					
					NodeList couponList = firstPerksElement
							.getElementsByTagName(KEY_COUPON);
					if (couponList.getLength() > 0) {
						Element firstCouponElement = (Element) couponList.item(0);
						NodeList textCouponList = firstCouponElement.getChildNodes();
						perk.setPerkCoupon( ((Node) textCouponList.item(0))
								.getNodeValue().trim());
					}

					perksListCollection.add(perk);

				}
			}
		} catch (IOException ex) {
			Log.e("Error", ex.getMessage(), ex);
		} catch (Exception ex) {
			Log.e("Error", "Loading exception", ex);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				Log.e("Error", "Problem closing file", e);
			}
		}
		return perksListCollection;
	}
}
