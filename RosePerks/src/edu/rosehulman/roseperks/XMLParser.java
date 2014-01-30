package edu.rosehulman.roseperks;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * Default Notification handler class for receiving ContentHandler
 * events raised by the SAX Parser
 * 
 * */
public class XMLParser extends DefaultHandler {

	ArrayList<String> idlist = new ArrayList<String>();
	ArrayList<String> namelist = new ArrayList<String>();
	ArrayList<String> locationlist = new ArrayList<String>();
	ArrayList<String> numberlist = new ArrayList<String>();
	ArrayList<String> discountlist = new ArrayList<String>();
	ArrayList<String> name_imagelist = new ArrayList<String>();
	
	//temp variable to store the data chunk read while parsing 
	private String tempStore	=	null;
		
	public XMLParser() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Clears the tempStore variable on every start of the element
	 * notification
	 * 
	 * */
	public void startElement (String uri, String localName, String qName,
			   Attributes attributes) throws SAXException {
	
		super.startElement(uri, localName, qName, attributes);
		
		if (localName.equalsIgnoreCase("id")) {
			tempStore = "";
		} else if (localName.equalsIgnoreCase("name")) {
			tempStore = "";
		} 
		else if (localName.equalsIgnoreCase("location")) {
			tempStore = "";
		}
		else if (localName.equalsIgnoreCase("number")) {
			tempStore = "";
		}
		else if (localName.equalsIgnoreCase("discount")) {
			tempStore = "";
		}
		else if (localName.equalsIgnoreCase("name_image")) {
			tempStore = "";
		}
		else {
			tempStore = "";
		}
	}
	
	/*
	 * updates the value of the tempStore variable into
	 * corresponding list on receiving end of the element
	 * notification
	 * */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		if (localName.equalsIgnoreCase("id")) {
			idlist.add(tempStore);
		} 
		else if (localName.equalsIgnoreCase("name")) {
			namelist.add(tempStore);
		}
		else if (localName.equalsIgnoreCase("location")) {
			locationlist.add(tempStore);
		}
		else if (localName.equalsIgnoreCase("number")) {
			numberlist.add(tempStore);
		}
		else if (localName.equalsIgnoreCase("discount")) {
			discountlist.add(tempStore);
		}
		else if (localName.equalsIgnoreCase("name_image")) {
			name_imagelist.add(tempStore);
		}
		
		tempStore = "";
	}
	
	/*
	 * adds the incoming data chunk of character data to the 
	 * temp data variable - tempStore
	 * 
	 * */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		tempStore += new String(ch, start, length);
	}

}