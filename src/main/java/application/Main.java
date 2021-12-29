package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) {
		String dbUrl = "jdbc:sqlite:music.db";
		boolean dbExists = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			String sql = "create table music (name varchar(100), composer varchar(100), copies int)";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Table created");
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
				Statement statement = conn.createStatement();
				statement.executeUpdate(sql);
				sql = "create table musicBorrowed (issued int, returned int, singerId int, musicId int, FOREIGN KEY(singerId) REFERENCES singer(rowid),  FOREIGN KEY(musicId) REFERENCES music(rowid))";
				statement.executeUpdate(sql);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Group root = new Group();
			Image icon = new Image("icon.png");
			stage.getIcons().add(icon);
			stage.setTitle("Music Library");
			Scene scene = new Scene(root,1400,800, Color.GREY);
			
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
