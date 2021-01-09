package model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FriendListPane {
	private Pane pane = new Pane();
	Label nameLb = new Label();
	Label statusLb = new Label();
	Label musicLb = new Label();
	
	public FriendListPane(UserDTO dto) {
		String name = dto.getName();
		int num = dto.getUser_num();
		
		pane.setPrefHeight(64);
		pane.setPrefWidth(265);
//		pane.setStyle("-fx-background-color: cyan;");
		pane.setId(num+"Pane");
		
		nameLb.setLayoutX(68);
		nameLb.setLayoutY(12);
		nameLb.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: Lucida Bright; -fx-font-size: 18;");
		nameLb.setText(name);
		Font nameFt = new Font("Lucida Bright Italic", 18);
		nameLb.setFont(nameFt);
		
		statusLb.setLayoutX(68);
		statusLb.setLayoutY(38);
		statusLb.setText("status message");
		Font statusFt = new Font("Lucida Bright Italic", 13);
		statusLb.setFont(statusFt);
		
		musicLb.setLayoutX(162);
		musicLb.setLayoutY(15);
		musicLb.setPrefWidth(100);
		musicLb.setStyle("-fx-border-radius: 10px; -fx-border-color: #01B925;");
		musicLb.setText("this is music");
		musicLb.setTextAlignment(TextAlignment.CENTER);
		musicLb.setTextFill(Paint.valueOf("#000000b2"));
		Font musicFt = new Font("Lucida Bright Italic", 11);
		musicLb.setFont(musicFt);
		
		pane.getChildren().add(nameLb);
		pane.getChildren().add(statusLb);
		pane.getChildren().add(musicLb);
		
	}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
}