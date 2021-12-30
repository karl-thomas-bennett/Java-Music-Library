package application.components;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class Table<T extends Item> extends Component{
	public Table(ObservableList<Item> items, Statement statement) {
		super();
		if(items.isEmpty()) {
			Label label = new Label("No items yet");
			pane.getChildren().add(label);
		}else {
			setItems(items, statement);
		}
	}
	
	public GridPane setItems(ObservableList<Item> items, Statement statement) {
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
			final int fi = i;
			for(int j = 0; j <= keys.size(); j++) {
				if(j == keys.size()) {
					Button button = new Button("Edit");
					button.setOnAction((e) -> {
						GridPane newTable = setItems(items, statement);
						List<TextField> fields = new ArrayList<TextField>();
						for(int k = 1; k < keys.size() + 2; k++) {
							if(k == keys.size()) {
								Button submit = new Button("Submit");
								submit.setOnAction((ev) -> {
									Item item = items.get(0);
									items.remove(fi);
									boolean valid = true;
									for(int m = 0; m < fields.size(); m++) {
										if(fields.get(m).getText().length() == 0) {
											valid = false;
										}
									}
									if(valid) {
										try {
											List<Object> arguments = fields.stream().map((f) -> {
												if(f.getTextFormatter() != null) {
													return Integer.parseInt(f.getText());
												}
												return f.getText();
												}).collect(Collectors.toList());
											arguments.add(0, fi + 1);
											Item it = (Item)(item.getClass().getDeclaredConstructors()[0].newInstance(arguments.toArray()));
											String sql = "UPDATE " + it.getItemType() + " SET";
											for(int m = 1; m < keys.size(); m++) {
												if(keys.get(m).equals("copies")) {
													sql += " " + keys.get(m) + " = " + it.pairs.get(keys.get(m));
												}else {
													sql += " " + keys.get(m) + " = '" + it.pairs.get(keys.get(m)) + "'";
												}
												if(m != keys.size() - 1) {
													sql += ",";
												}
											}
											sql += " WHERE rowid = " + (fi + 1);
											statement.execute(sql);
											items.add(fi, it);
										} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
												| InvocationTargetException | SecurityException | SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										setItems(items, statement);
									}
									
								});
								newTable.add(submit, k, fi + 1);
							}else if(k > keys.size()) {
								Button cancel = new Button("Cancel");
								cancel.setOnAction((ev) -> {
									setItems(items, statement);
								});
								newTable.add(cancel, k, fi + 1);
							}else {
								TextField input = new TextField(pairs.get(keys.get(k)));
								if(keys.get(k).equals("copies")) {
									input.setTextFormatter(new TextFormatter<>(textField ->  {
										if(!textField.getControlNewText().matches("\\d*")) {
											textField.setText(textField.getControlText());
										}
										return textField;
									}));
								}
								fields.add(input);
								newTable.add(input, k, fi + 1);
							}
							
						}
					});
					table.add(button, j, i + 1);
				}else {
					Label label = new Label(pairs.get(keys.get(j)));
					label.setStyle("-fx-padding: 5px");
					table.add(label, j, i + 1);
				}
			}
		}
		children.add(table);
		return table;
	}
}
