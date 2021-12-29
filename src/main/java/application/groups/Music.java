package application.groups;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class Music extends Component{
	public Music(Nav nav, Statement statement) {
		super();
		List<Node> children = group.getChildren();
		children.add(nav.group);
		List<Item> items = new ArrayList<Item>();
		try {
			ResultSet results = statement.executeQuery("SELECT rowid, * FROM music");
			while(results.next()) {
				int id = results.getInt("rowid");
				String name = results.getString("name");
				String composer = results.getString("composer");
				int copies = results.getInt("copies");
				items.add(new MusicItem(id, name, composer, copies));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		children.add(new Table(items).group);
		
	}
}
