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
import javafx.scene.control.TextFormatter;

public class AddMusic extends Component{
	public AddMusic(Statement statement, ObservableList<Item> items, Table table) {
		Label nameLabel = new Label("Name: ");
		Label composerLabel = new Label("Composer: ");
		Label copiesLabel = new Label("Number of Copies: ");
		TextField name = new TextField();
		TextField composer = new TextField();
		TextField copies = new TextField();
		copies.setTextFormatter(new TextFormatter<>(textField ->  {
			if(!textField.getControlNewText().matches("\\d*")) {
				textField.setText(textField.getControlText());
			}
			return textField;
		}));
		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			try {
				String sql = "INSERT INTO music VALUES ('" + name.getText() + "','" + composer.getText() + "', " + copies.getText() + ") RETURNING rowid, *";
				
				statement.execute(sql);
				ResultSet results = statement.getResultSet();
				while(results.next()) {
					int id = results.getInt("rowid");
					String n = results.getString("name");
					String com = results.getString("composer");
					int cop = results.getInt("copies");
					items.add(new MusicItem(id, n, com, cop));
					table.setItems(items);
				}
				//items.add(new MusicItem(id, name, composer, copies));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		List<Node> children = pane.getChildren();
		children.add(nameLabel);
		children.add(name);
		children.add(composerLabel);
		children.add(composer);
		children.add(copiesLabel);
		children.add(copies);
		children.add(submit);
	}
	
}
