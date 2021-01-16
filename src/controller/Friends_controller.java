package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import model.FriendListPane;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserDTO;

public class Friends_controller implements Initializable{
	@FXML private Label Friend_time;
	@FXML private Label logon_id;
	@FXML private Label Friends_myprofile_label;
	@FXML private Label user_status;
	@FXML private ImageView userImage;

	@FXML private TextField friends_search;
	@FXML private ImageView friends_friends_btn;
	@FXML private ImageView friends_chats_btn;
	@FXML private ImageView friends_search_btn;
	@FXML private ImageView friends_more_btn;

	@FXML private ScrollPane list;
	@FXML private VBox vboxlist;

	private FriendListPane [] friendListPane  = new FriendListPane [UserDTO.friends.size()];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserDAO dao = new UserDAO();
		dao.friendReview();
		
		
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
					primaryStage.setTitle("Friends");
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
					primaryStage.setTitle("Friends");
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
					primaryStage.setTitle("Friends");
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
						friends_friends_btn.setOpacity(1);
					}
				});
		friends_chats_btn.setCursor(Cursor.HAND);
		friends_chats_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_chats_btn.setOpacity(1);
					}else {
						friends_chats_btn.setOpacity(0.8);
					}
				});
		friends_search_btn.setCursor(Cursor.HAND);
		friends_search_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_search_btn.setOpacity(1);
					}else {
						friends_search_btn.setOpacity(0.8);
					}
				});
		friends_more_btn.setCursor(Cursor.HAND);
		friends_more_btn.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						friends_more_btn.setOpacity(1);
					}else {
						friends_more_btn.setOpacity(0.8);
					}
				});
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Friend_time.setText(sdf.format(date));

		list.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		list.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

		Friends_myprofile_label.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile.fxml"));
						Scene scene = new Scene(signup);
						Stage primaryStage = (Stage) Friends_myprofile_label.getScene().getWindow();
						primaryStage.setTitle("Profile");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});

		logon_id.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: NanumGothic; -fx-font-size: 18; -fx-text-fill: #868686;");
		logon_id.setText(UserDTO.nowUser.getName());
		userImage.setImage(UserDTO.nowUser.getImage());
		user_status.setText(UserDTO.nowUser.getStatus());
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

			//scene의 경로
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile_Friends.fxml"));
			//받아온 경로로 객체만들기
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
			primaryStage.setTitle("Profile");
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	//네비게이션 바
//	public void handleBtnFriends (ActionEvent event) {
//		//db에 저장해야함
//		try {
//			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
//			Scene scene = new Scene(login);
//			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
//			primaryStage.setTitle("Friends");
//			primaryStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void handleBtnChats (ActionEvent event) {
//		//db에 저장해야함
//		try {
//			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
//			Scene scene = new Scene(login);
//			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
//			primaryStage.setTitle("Chats");
//			primaryStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void handleBtnSearch (ActionEvent event) {
//		//db에 저장해야함
//		try {
//			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Search.fxml"));
//			Scene scene = new Scene(login);
//			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
//			primaryStage.setTitle("Search");
//			primaryStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void handleBtnMore (ActionEvent event) {
//		//db에 저장해야함
//		try {
//			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/More.fxml"));
//			Scene scene = new Scene(login);
//			Stage primaryStage = (Stage) friends_friends_btn.getScene().getWindow();
//			primaryStage.setTitle("More");
//			primaryStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}