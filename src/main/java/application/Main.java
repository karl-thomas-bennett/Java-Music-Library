package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import application.groups.Music;
import application.groups.Nav;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) {
		String dbUrl = "jdbc:sqlite:music.db";
		boolean dbExists = false;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			String sql = "create table music (name varchar(100), composer varchar(100), copies int)";
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			if(!e.getMessage().contains("table music already exists")) {
				e.printStackTrace();
			}else {
				dbExists = true;
			}
		}
		
		try {
			if(!dbExists) {
				String sql = "create table singer (name varchar(100))";
				statement.executeUpdate(sql);
				sql = "create table musicBorrowed (issued int, returned int, singerId int, musicId int, FOREIGN KEY(singerId) REFERENCES singer(rowid),  FOREIGN KEY(musicId) REFERENCES music(rowid))";
				statement.executeUpdate(sql);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Pane root = new Pane();
			root.getStyleClass().add("root");
			//Table table = new Table();
			ArrayList<String> navList = new ArrayList<String>(Arrays.asList("Music", "Singers"));
			Nav nav = new Nav(navList, stage, root, statement);
			Music music = new Music(nav, statement);
			root.getChildren().add(music.group);
			Image icon = new Image("icon.png");
			stage.getIcons().add(icon);
			stage.setTitle("Music Library");
			Scene scene = new Scene(root,1400,800);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
