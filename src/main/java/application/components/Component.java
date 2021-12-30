package application.components;

import javafx.scene.layout.FlowPane;

public abstract class Component {
	public FlowPane pane;
	public Component() {
		pane = new FlowPane();		
	}
}
