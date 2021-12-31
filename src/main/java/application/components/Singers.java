package application.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Singers extends Component{
	public Singers(Nav nav, Statement statement) {
		super();
		List<Node> children = pane.getChildren();
		children.add(nav.pane);
		ObservableList<Item> items = FXCollections.observableArrayList();
		items.clear();
		try {
			ResultSet results = statement.executeQuery("SELECT rowid, * FROM singer");
			while(results.next()) {
				int id = results.getInt("rowid");
				String name = results.getString("name");
				items.add(new SingerItem(id, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Table table = new Table(items, statement);
		table.pane.setStyle("-fx-translate-x: 50; -fx-translate-y: 50");
		children.add(new AddSinger(statement, items, table).pane);
		children.add(table.pane);
	}
}
