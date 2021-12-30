package application.components;


import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {
	
	public static void changeScene(ActionEvent e, Nav nav, Stage stage, String sceneName, Pane root, Statement statement) {
		Pane newRoot = new Pane();
		newRoot.getStyleClass().addAll(root.getStyleClass());
		Component component = null;
		try {
			component = (Component)(Class.forName("application.components." + sceneName).getConstructors()[0].newInstance(nav, statement));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		newRoot.getChildren().add(component.pane);
		Node navNode = ((FlowPane)newRoot.getChildren().get(0)).getChildren().get(0);
		FlowPane navPane = (FlowPane)navNode;
		for(Node n : navPane.getChildren()) {
			n.getStyleClass().remove("focussed");
		}
		Node button = navPane.getChildren().stream().filter((c) -> {
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
