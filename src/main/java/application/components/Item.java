package application.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Item {
	public Map<String, String> pairs;
	public List<String> types;
	
	public Item() {
		pairs = new LinkedHashMap<String, String>();
		types = new ArrayList<String>();
	}
	
	public String getItemType() {
		return "Item";
	}
}
