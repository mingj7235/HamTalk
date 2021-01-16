package model;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FriendListPane {
	private Pane pane = new Pane();
	ImageView image = new ImageView();
	Label nameLb = new Label();
	Label statusLb = new Label();
	Label musicLb = new Label();
	
	public FriendListPane(UserDTO dto) {
		String name = dto.getName();
		int num = dto.getUser_num();
		
		pane.setPrefHeight(71);
		pane.setPrefWidth(290);
		pane.setStyle("-fx-background-color: white;");
		pane.setId(num+"Pane");
		pane.setCursor(Cursor.HAND);
		pane.hoverProperty().addListener((ChangeListener<Boolean>) 
				(observable, oldValue, newValue) -> {
					if(newValue) {
						pane.setStyle("-fx-background-color: #ccdaf2;");
					}else {
						pane.setStyle("-fx-background-color: white;");
					}
				});
		
		image.setFitHeight(60);
		image.setFitWidth(60);
		image.setLayoutX(10);
		image.setLayoutY(4);
		image.setPickOnBounds(true);
		image.setImage(dto.getImage());
		
		Label imageLb = new Label();
		imageLb.setLayoutX(6);
		imageLb.setLayoutY(1);
		imageLb.setPrefHeight(68);
		imageLb.setPrefWidth(68);
		imageLb.setStyle("-fx-border-color: white; -fx-border-radius: 10px; -fx-border-width: 4px;");
		
		nameLb.setLayoutX(85);
		nameLb.setLayoutY(13);
		nameLb.setStyle("-fx-font-weight: bold; -fx-font-style: italic; "
				+ "-fx-font-family: NanumGothic; -fx-font-size: 18; -fx-text-fill: #868686;");
		nameLb.setText(name);
		Font nameFt = new Font("Lucida Bright Italic", 18);
		nameLb.setFont(nameFt);
		
		statusLb.setLayoutX(85);
		statusLb.setLayoutY(40);
		statusLb.setText(dto.getStatus());
		statusLb.setStyle("-fx-text-fill: #868686;");
		Font statusFt = new Font("Lucida Bright Italic", 13);
		statusLb.setFont(statusFt);
		
		
		musicLb.setAlignment(Pos.CENTER);;
		musicLb.setLayoutX(191);
		musicLb.setLayoutY(16);
		musicLb.setContentDisplay(ContentDisplay.CENTER);
		musicLb.setStyle("-fx-border-radius: 10px; -fx-border-color: #8aaae5;");
		musicLb.setText("this is music");
		musicLb.setTextAlignment(TextAlignment.CENTER);
		musicLb.setTextFill(Paint.valueOf("#000000b2"));
		Font musicFt = new Font("Lucida Bright Italic", 11);
		musicLb.setFont(musicFt);
		musicLb.setPadding(new Insets(0, 10, 0, 10));
		
		Separator sp = new Separator();
		sp.setLayoutX(11);
		sp.setLayoutY(69);
		sp.setPrefHeight(0);
		sp.setPrefWidth(269);
		
		pane.getChildren().add(nameLb);
		pane.getChildren().add(statusLb);
		pane.getChildren().add(musicLb);
		pane.getChildren().add(image);
		pane.getChildren().add(imageLb);
		pane.getChildren().add(sp);
	}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
}