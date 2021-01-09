package model;

import javax.swing.GroupLayout.Alignment;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ChatListPane {
	private Pane pane = new Pane();
	Label nameLb = new Label();
	Label msgLb = new Label();
	Label timeLb = new Label();
	final Separator separator = new Separator();
	
	public ChatListPane(UserDTO dto) {
		String name = dto.getName();
		int num = dto.getUser_num();
		
		pane.setPrefHeight(71);
		pane.setPrefWidth(300);
		pane.setId(num+"ChatListPane");
		
		nameLb.setLayoutX(85);
		nameLb.setLayoutY(13);
		nameLb.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-family: Lucida Bright; -fx-font-size: 18;" );
		nameLb.setText(name);
		Font nameFt = new Font("Lucida Bright Italic", 18);
		nameLb.setFont(nameFt);
		
		msgLb.setLayoutX(85);
		msgLb.setLayoutY(40);
		msgLb.setText("recent message");
		Font recentMessageFt = new Font("Lucida Bright Italic", 13);
		msgLb.setFont(recentMessageFt);
		
		timeLb.setLayoutX(198);
		timeLb.setLayoutY(6);
		timeLb.setPrefHeight(15);
		timeLb.setPrefWidth(90);
		timeLb.setAlignment(Pos.CENTER_RIGHT);
		timeLb.setText("time");
		Font timeFt = new Font("Lucida Bright Italic", 11);
		timeLb.setFont(timeFt);
		
		separator.setPrefWidth(290);
		
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