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

public class Friends_controller implements Initializable{
	@FXML private Label Friend_time;
	@FXML private Label logon_id;
	
	@FXML private TextField friends_search;
	@FXML private Button friends_friends_btn;
	@FXML private Button friends_chats_btn;
	@FXML private Button friends_search_btn;
	@FXML private Button friends_more_btn;
	@FXML private VBox vboxlist;
	
	private FriendListPane [] friendListPane  = new FriendListPane [UserDTO.friends.size()];
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		friends_friends_btn.setOnAction(e->handleBtnFriends(e));
		friends_chats_btn.setOnAction(e->handleBtnChats(e));
		friends_search_btn.setOnAction(e->handleBtnSearch(e));
		friends_more_btn.setOnAction(e->handleBtnMore(e));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Friend_time.setText(sdf.format(date));
		logon_id.setText(UserDTO.nowUser.getName());
		for (int i = 0; i < friendListPane.length; i++) {
			friendListPane[i] = new FriendListPane(UserDTO.friends.get(i));
			int a = i;
			friendListPane[i].getPane().setOnMouseClicked(e->handletoFriendClick(e, UserDTO.friends.get(a)));
			vboxlist.getChildren().add(friendListPane[i].getPane());
		}	
	}
	//친구 클릭시
	public void handletoFriendClick (MouseEvent event, UserDTO friend) {
		try {
			//접속하는 채팅방 친구 입력
			UserDTO.withFriend = friend;

			UserDAO dao = new UserDAO();
			int room_num = dao.roomCheck(UserDTO.nowUser, UserDTO.withFriend); //방이 존재하지 않다면 만들고 결과적으로 방번호 리턴
			
			Chat_w_01_controller.room_num = room_num;
			
			//scene의 경로
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chat_w_01.fxml"));
			//받아온 경로로 객체만들기
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
