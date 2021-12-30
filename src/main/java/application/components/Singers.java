package application.components;

import java.sql.Statement;

public class Singers extends Component{
	public Singers(Nav nav, Statement statement) {
		super();
		pane.getChildren().add(nav.pane);
	}
}
