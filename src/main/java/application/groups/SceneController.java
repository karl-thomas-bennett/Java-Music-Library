package application.groups;


import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {
	
	public static void changeScene(ActionEvent e, Nav nav, Stage stage, String sceneName, Pane root, Statement statement) {
		Pane newRoot = new Pane();
		newRoot.getStyleClass().addAll(root.getStyleClass());
		Component component = null;
		try {
			component = (Component)(Class.forName("application.groups." + sceneName).getConstructors()[0].newInstance(nav, statement));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		newRoot.getChildren().add(component.group);
		Node navNode = ((Group)newRoot.getChildren().get(0)).getChildren().get(0);
		Group navGroup = (Group)navNode;
		for(Node n : navGroup.getChildren()) {
			n.getStyleClass().remove("focussed");
		}
		Node button = navGroup.getChildren().stream().filter((c) -> {
			Button b = (Button)c;
			return b.getText().equals(((Button)e.getSource()).getText());
		}).findAny().orElse(null);
		button.getStyleClass().add("focussed");
		
		Scene scene = new Scene(newRoot,1400,800);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
