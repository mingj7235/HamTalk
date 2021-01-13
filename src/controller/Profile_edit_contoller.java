package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Profile_edit_contoller implements Initializable{
	@FXML private Label Profile_edit_user_name;
	@FXML private Label profile_exit;
	
	@FXML private VBox profile_edit_exit;
	@FXML private ImageView profile_exit_back;
	@FXML private ImageView Profile_edit_photo; //변경할 이미지 핸들
	@FXML private Label profile_edit_status; // 변경할 status 레이블 핸들
	@FXML private Button profile_edit_status_btn; //status 변경 버튼
	@FXML private Button profile_edit_photo_btn; // photo 변경 버튼
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		profile_exit_back.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile.fxml"));
						Scene scene = new Scene(signup);
						Stage primaryStage = (Stage) profile_exit_back.getScene().getWindow();
						primaryStage.setTitle("Sign up");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});
		profile_edit_exit.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile.fxml"));
						Scene scene = new Scene(signup);
						Stage primaryStage = (Stage) profile_edit_exit.getScene().getWindow();
						primaryStage.setTitle("Sign up");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});
		
		
	}
}
