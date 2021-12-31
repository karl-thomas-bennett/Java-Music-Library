package application.components;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSinger extends Component{
	public AddSinger(Statement statement, ObservableList<Item> items, Table table) {
		Label nameLabel = new Label("Name: ");
		TextField name = new TextField();
		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			try {
				String sql = "INSERT INTO singer VALUES ('" + name.getText() + "') RETURNING rowid, *";
				
				statement.execute(sql);
				ResultSet results = statement.getResultSet();
				while(results.next()) {
					int id = results.getInt("rowid");
					String n = results.getString("name");
					items.add(new SingerItem(id, n));
					table.setItems(items, statement);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		List<Node> children = pane.getChildren();
		children.add(nameLabel);
		children.add(name);
		children.add(submit);
	}
	
}
