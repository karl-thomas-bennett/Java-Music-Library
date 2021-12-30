package application.components;

import java.util.HashMap;
import java.util.Map;

public class Item {
	public Map<String, String> pairs;
	
	public Item() {
		pairs = new HashMap<String, String>();
	}
	
	public String getItemType() {
		return "Item";
	}
}
