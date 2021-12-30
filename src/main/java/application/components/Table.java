package application.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Table extends Component{
	public Table(ObservableList<Item> items) {
		super();
		if(items.isEmpty()) {
			Label label = new Label("No items yet");
			pane.getChildren().add(label);
		}else {
			setItems(items);
		}
	}
	
	public void setItems(ObservableList<Item> items) {
		List<Node> children = pane.getChildren();
		children.clear();
		GridPane table = new GridPane();
		table.setGridLinesVisible(true);
		String[] keysArray = new String[items.get(0).pairs.size()];
		keysArray = items.get(0).pairs.keySet().toArray(keysArray);
		List<String> keys = Arrays.asList(keysArray);
		Collections.reverse(keys);
		for(int i = 0; i < keys.size(); i++) {
			Label label = new Label(keys.get(i));
			label.setStyle("-fx-padding: 5px");
	        table.add(label, i, 0);
		}
		for(int i = 0; i < items.size(); i++) {
			Map<String, String> pairs = items.get(i).pairs;
			for(int j = 0; j < keys.size(); j++) {
				Label label = new Label(pairs.get(keys.get(j)));
				label.setStyle("-fx-padding: 5px");
				table.add(label, j, i + 1);
			}
		}
		children.add(table);
	}
}
