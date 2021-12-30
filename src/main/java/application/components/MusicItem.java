package application.components;

public class MusicItem extends Item {

	public MusicItem(int id, String name, String composer, int copies) {
		super();
		pairs.put("id", Integer.toString(id));
		pairs.put("name", name);
		pairs.put("composer", composer);
		pairs.put("copies", Integer.toString(copies));
	}
	@Override
	public String getItemType() {
		return "Music";
	}
}
