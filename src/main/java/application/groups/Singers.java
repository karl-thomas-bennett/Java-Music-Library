package application.groups;

import java.sql.Statement;

public class Singers extends Component{
	public Singers(Nav nav, Statement statement) {
		super();
		group.getChildren().add(nav.group);
	}
}
