package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class ChatDatePane {
	private VBox vBox = new VBox();
	private Label dateTextLb = new Label();
	public ChatDatePane(String date) {
		String realDate = date.substring(0, 4)+"년 "+date.substring(4, 6)+"월 "+date.substring(6, 8)+"일";
		
		vBox.setAlignment(Pos.TOP_CENTER);
		dateTextLb.setText(realDate);
		dateTextLb.setAlignment(Pos.CENTER);
		dateTextLb.setPrefHeight(27);
		dateTextLb.setStyle("-fx-background-color: rgba(0, 0, 3, 0.2); -fx-background-radius: 10px; -fx-font-size: 11; -fx-font-family: NanumGothic;");
		dateTextLb.setTextFill(Paint.valueOf("#f8f5f5"));
		dateTextLb.setPadding(new Insets(0, 7, 0, 7));
		vBox.getChildren().add(dateTextLb);
	}
	public VBox getvBox() {
		return vBox;
	}
	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}
}
