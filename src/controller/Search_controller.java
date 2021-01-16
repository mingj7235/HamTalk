package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Search_controller implements Initializable{
	@FXML private Label Search_time;
	
	@FXML private ImageView friends_friends_btn;
	@FXML private ImageView friends_chats_btn;
	@FXML private ImageView friends_search_btn;
	@FXML private ImageView friends_more_btn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		friends_friends_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
					Scene scene = new Scene(login);
					Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
					primaryStage.setTitle("Friends");
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
	         };
		});
		
		friends_chats_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
					Scene scene = new Scene(login);
					Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
					primaryStage.setTitle("Chats");
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
	         };
		});
		friends_search_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Search.fxml"));
					Scene scene = new Scene(login);
					Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
					primaryStage.setTitle("Search");
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
	         };
		});
		friends_more_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/More.fxml"));
					Scene scene = new Scene(login);
					Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
					primaryStage.setTitle("More");
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
	         };
		});
		
		friends_friends_btn.setCursor(Cursor.HAND);
		friends_friends_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_friends_btn.setOpacity(1);
					}else {
						friends_friends_btn.setOpacity(0.6);
					}
				});
		friends_chats_btn.setCursor(Cursor.HAND);
		friends_chats_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_chats_btn.setOpacity(1);
					}else {
						friends_chats_btn.setOpacity(0.6);
					}
				});
		friends_search_btn.setCursor(Cursor.HAND);
		friends_search_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_search_btn.setOpacity(1);
					}else {
						friends_search_btn.setOpacity(1);
					}
				});
		friends_more_btn.setCursor(Cursor.HAND);
		friends_more_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_more_btn.setOpacity(1);
					}else {
						friends_more_btn.setOpacity(0.6);
					}
				});
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Search_time.setText(sdf.format(date));
	}
	
	
}
