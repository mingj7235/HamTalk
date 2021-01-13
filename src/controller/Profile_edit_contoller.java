package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.UserDTO;

public class Profile_edit_contoller implements Initializable{
	@FXML private StackPane Profile_edit;
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
		Profile_edit_user_name.setText(UserDTO.nowUser.getName());
		profile_edit_status.setText(UserDTO.nowUser.getStatus());
		
		profile_edit_photo_btn.setOnAction(e -> handlePhotoBtn(e));
		
		profile_exit_back.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent profile = FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile.fxml"));
						Scene scene = new Scene(profile);
						Stage primaryStage = (Stage) profile_exit_back.getScene().getWindow();
						primaryStage.setTitle("Profile edit");
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
						Parent profile =FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
						Scene scene = new Scene(profile);
						Stage primaryStage = (Stage) profile_edit_exit.getScene().getWindow();
						primaryStage.setTitle("Profile edit");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});
		
		
	}

	public void handlePhotoBtn (ActionEvent event) {
		
		FileChooser fc = new FileChooser();
		fc.setTitle("프로필 사진 수정");
		
		Stage primaryStage = (Stage) profile_edit_photo_btn.getScene().getWindow();
		File file = fc.showOpenDialog(primaryStage);
		Image image = new Image(file.toURI().toString());
		Profile_edit_photo.setImage(image);


	}




}
