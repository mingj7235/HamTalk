package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MessagePane {
	
	private Pane name_pane = new Pane();
	private Pane msg_pane = new Pane();
	Label nameLb = new Label();
	Label msgLb = new Label();
	Label timeLb = new Label();
	
	
	public MessagePane(String name, String msg) {
		
		//Pane 설치
		name_pane.setPrefWidth(250);
		name_pane.setPrefHeight(20);
		
		msg_pane.setPrefWidth(250);
		msg_pane.setPrefHeight(50);

		//label 설정
		
		nameLb.setLayoutX(20);
		nameLb.setLayoutY(20);
		nameLb.setText(name);
		
		msgLb.setLayoutX(20);
		msgLb.setLayoutY(20);
		msgLb.setPrefWidth(150);
		msgLb.setWrapText(true);
		
		
		msgLb.setPadding(new Insets(10));
		msgLb.setStyle("-fx-background-color: #fee100");
		msgLb.setText(msg);
		
		name_pane.getChildren().add(nameLb);
		msg_pane.getChildren().add(msgLb);
		
	}

	public Pane getName_pane() {
		return name_pane;
	}

	public void setName_pane(Pane name_pane) {
		this.name_pane = name_pane;
	}

	public Pane getMsg_pane() {
		return msg_pane;
	}

	public void setMsg_pane(Pane msg_pane) {
		this.msg_pane = msg_pane;
	}

	
	
	
}
