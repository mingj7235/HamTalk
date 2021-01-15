package model;

import javax.swing.GroupLayout.Alignment;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class ChatListPane {
	private Pane pane = new Pane();
	Label borderLb = new Label();
	Label msgLb = new Label();
	Label timeLb = new Label();
	Label nameLb = new Label();
	private int friendNum;
	String url = "/imgs/profile.jpg";
	
	public ChatListPane(int friendNum, String friendName, String message, String time, boolean lastOnOff) {
		System.out.println(lastOnOff);
		String name = friendName;
		this.friendNum = friendNum;
		int num = friendNum;
		String message2 = message;
		String time2 = time;
		
		pane.setPrefHeight(68);
		pane.setPrefWidth(290);
		pane.setId(num+"ChatListPane");
		
		ImageView imageView = new ImageView();
		for (int i = 0; i < UserDTO.friends.size(); i++) {
			if(UserDTO.friends.get(i).getUser_num() == friendNum) {
				imageView = new ImageView(UserDTO.friends.get(i).getImage());
			}
		}
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		imageView.setLayoutX(6);
		imageView.setLayoutY(4);
		imageView.setPickOnBounds(true);
		
		borderLb.setLayoutX(2);
		borderLb.setLayoutY(1);
		borderLb.setPrefHeight(68);
		borderLb.setPrefWidth(68);
		borderLb.setStyle("-fx-border-color: white; -fx-border-radius: 10px; -fx-border-width: 4px;");
			
		nameLb.setLayoutX(80);
		nameLb.setLayoutY(9);
		nameLb.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: NanumGothic; "
				+ "-fx-font-size: 18; -fx-text-fill: #868686;");
		nameLb.setText(name);
		
		msgLb.setLayoutX(80);
		msgLb.setLayoutY(39);
		msgLb.setStyle("-fx-font-style: italic; -fx-font-family: NanumGothic; -fx-font-size: 13;"
				+ "-fx-text-fill: #868686;");
		msgLb.setText(message2);
		
		timeLb.setAlignment(Pos.CENTER_RIGHT);
		timeLb.setContentDisplay(ContentDisplay.RIGHT);
		timeLb.setLayoutX(172);
		timeLb.setLayoutY(13);
		timeLb.setPrefHeight(15);
		timeLb.setPrefWidth(109);
		timeLb.setText(time2);
		timeLb.setStyle("-fx-font-style: italic; -fx-font-family: NanumGothic; -fx-font-size: 11;"
				+ "-fx-text-fill: #868686;" );
		timeLb.setTextAlignment(TextAlignment.CENTER);
		
		Separator separator = new Separator();
		separator.setLayoutY(65);
		separator.setPrefHeight(7);
		separator.setPrefWidth(290);
		
		pane.getChildren().add(imageView);
		pane.getChildren().add(borderLb);
		pane.getChildren().add(nameLb);
		pane.getChildren().add(msgLb);
		pane.getChildren().add(timeLb);
		pane.getChildren().add(separator);
	}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}


	public int getFriendNum() {
		return friendNum;
	}


	public void setFriendNum(int friendNum) {
		this.friendNum = friendNum;
	}

}