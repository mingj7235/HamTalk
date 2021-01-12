package model;

import javax.swing.GroupLayout.Alignment;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class ChatListPane {
	private Pane pane = new Pane();
	Label borderLb = new Label();
	Label msgLb = new Label();
	Label timeLb = new Label();
	Label nameLb = new Label();
	
	
	public ChatListPane(UserDTO dto) {
		String name = dto.getName();
		int num = dto.getUser_num();
		String message = "recent message";
		String time = "time";
		
		pane.setPrefHeight(68);
		pane.setPrefWidth(290);
		pane.setId(num+"ChatListPane");
		
		String url = "/imgs/profile.jpg";
		ImageView imageView = new ImageView(url);
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		imageView.setLayoutX(6);
		imageView.setLayoutY(6);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		
		borderLb.setLayoutX(4);
		borderLb.setLayoutY(1);
		borderLb.setPrefHeight(64);
		borderLb.setPrefWidth(64);
		borderLb.setStyle("-fx-border-color: CCCCCC; -fx-border-radius: 10px; -fx-border-width: 5px;" );
			
		nameLb.setLayoutX(80);
		nameLb.setLayoutY(9);
		nameLb.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: NanumGothic; "
				+ "-fx-font-size: 18; -fx-text-fill: #868686;");
		nameLb.setText(name);
		
		msgLb.setLayoutX(80);
		msgLb.setLayoutY(39);
		msgLb.setStyle("-fx-font-style: italic; -fx-font-family: NanumGothic; -fx-font-size: 13;"
				+ "-fx-text-fill: #868686;");
		msgLb.setText(message);
		
		timeLb.setAlignment(Pos.CENTER_RIGHT);
		timeLb.setContentDisplay(ContentDisplay.RIGHT);
		timeLb.setLayoutX(172);
		timeLb.setLayoutY(13);
		timeLb.setPrefHeight(15);
		timeLb.setPrefWidth(109);
		timeLb.setText(time);
		timeLb.setStyle("-fx-font-style: italic; -fx-font-family: NanumGothic; -fx-font-size: 12;"
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
	
}