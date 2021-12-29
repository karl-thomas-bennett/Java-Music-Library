package application.groups;

import java.util.List;

import javafx.scene.control.Label;

public class Table extends Component{
	public Table(List<Item> items) {
		super();
		if(items.isEmpty()) {
			Label label = new Label("No items yet");
			label.setStyle("-fx-translate-x: 50; -fx-translate-y: 100");
			group.getChildren().add(label);
		}
	}
}
