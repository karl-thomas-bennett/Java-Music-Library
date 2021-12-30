package application.components;

import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Nav extends Component{
	public Nav(ArrayList<String> pages, Stage stage, Pane root, Statement statement) {
		super();
		for(int i = 0; i < pages.size(); i++) {
			String page = pages.get(i);
			Button button = new Button(page);
			
			button.setOnAction((ActionEvent e) -> {SceneController.changeScene(e, this, stage, page, root, statement);});
			button.getStyleClass().add("nav-button");
			if(i == 0) {
				button.getStyleClass().add("focussed");
			}
			//button.setStyle("-fx-translate-x: " + i*50);
			pane.getChildren().add(button);
		}
	}
}
