package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ChatListPane;
import model.FriendListPane;
import model.UserDTO;

public class Chats_controller implements Initializable{
	@FXML private Label Chats_time;
	//채팅방의 오른편 채팅 시간 label
	@FXML private Label chats_chatstime_1;
	@FXML private Label chats_chatstime_11;
	@FXML private Label chats_chatstime_111;
	@FXML private Label chats_chatstime_1111;

	@FXML private Button friends_friends_btn;
	@FXML private Button friends_chats_btn;
	@FXML private Button friends_search_btn;
	@FXML private Button friends_more_btn;

	@FXML private ScrollPane chatListScroll;

	@FXML private VBox vboxlist2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		friends_friends_btn.setOnAction(e->handleBtnFriends(e));
		friends_chats_btn.setOnAction(e->handleBtnChats(e));
		friends_search_btn.setOnAction(e->handleBtnSearch(e));
		friends_more_btn.setOnAction(e->handleBtnMore(e));

		chatListScroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		chatListScroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		
		UserDAO dao = new UserDAO();
		ArrayList<ChatListPane> chatListArr = dao.lastChatOrder(UserDTO.nowUser.getUser_num());
		for (int i = 0; i < chatListArr.size(); i++) {
			ChatListPane clp = chatListArr.get(i);
			for (int j = 0; j < UserDTO.friends.size(); j++) {
				if(UserDTO.friends.get(j).getUser_num() == clp.getFriendNum()) {
					int a = j;
					clp.getPane().setOnMouseClicked(e->handletochatlink(e, UserDTO.friends.get(a)));
					vboxlist2.getChildren().add(clp.getPane());
				}
			}
		}

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Chats_time.setText(sdf.format(date));
	}

	//채팅방 창에서 친구 클릭시 친구와 채팅창으로 넘어가는 이벤트
	public void handletochatlink(MouseEvent event, UserDTO friend) {
		try {
			UserDTO.withFriend = friend;

			UserDAO dao = new UserDAO();
			Chat_w_01_controller.room_num = dao.roomCheck(UserDTO.nowUser, UserDTO.withFriend);

			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chat_w_01.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setTitle("Chatting");
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
			primaryStage.setTitle("Friends");
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
			primaryStage.setTitle("Chats");
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
			primaryStage.setTitle("Search");
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
			primaryStage.setTitle("More");
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
