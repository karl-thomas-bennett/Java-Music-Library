package application.components;

public class SingerItem extends Item {

	public SingerItem(int id, String name) {
		super();
		pairs.put("id", Integer.toString(id));
		pairs.put("name", name);
		types.add("int");
		types.add("String");
	}
	@Override
	public String getItemType() {
		return "Singer";
	}
}
