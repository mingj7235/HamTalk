package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import model.FriendListPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserDTO;

public class Search_controller implements Initializable{
	@FXML private Label Search_time;
	
	@FXML private Button friends_friends_btn;
	@FXML private Button friends_chats_btn;
	@FXML private Button friends_search_btn;
	@FXML private Button friends_more_btn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		friends_friends_btn.setOnAction(e->handleBtnFriends(e));
		friends_chats_btn.setOnAction(e->handleBtnChats(e));
		friends_search_btn.setOnAction(e->handleBtnSearch(e));
		friends_more_btn.setOnAction(e->handleBtnMore(e));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Search_time.setText(sdf.format(date));
	}
	
	//네비게이션 바
	public void handleBtnFriends (ActionEvent event) {
		//db에 저장해야함
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleBtnChats (ActionEvent event) {
		//db에 저장해야함
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleBtnSearch (ActionEvent event) {
		//db에 저장해야함
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Search.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleBtnMore (ActionEvent event) {
		//db에 저장해야함
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/More.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
