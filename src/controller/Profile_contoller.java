package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.UserDTO;

public class Profile_contoller implements Initializable{
	
	@FXML private Label Profile_user_name;
	@FXML private Label profile_status;
	@FXML private ImageView myImage;
	
	@FXML private ImageView profile_set_label;
	@FXML private ImageView profile_exit;
	@FXML private ImageView profile_git_link;
	@FXML private ImageView profile_to_chatsscreen;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Profile_user_name.setText(UserDTO.nowUser.getName());
		profile_status.setText(UserDTO.nowUser.getStatus());
		myImage.setImage(UserDTO.nowUser.getImage());
		
		profile_to_chatsscreen.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
	            if(event.getButton() == MouseButton.PRIMARY) {
	            	try {
	        			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
	        			Scene scene = new Scene(login);
	        			Stage primaryStage = (Stage) profile_to_chatsscreen.getScene().getWindow();
	        			primaryStage.setTitle("HAM Talk");
	        			primaryStage.setScene(scene);
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		}
	            }
	         };
		});
		
		profile_git_link.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
	            if(event.getButton() == MouseButton.PRIMARY) {
	               Runtime runtime = Runtime.getRuntime();
	               try {
	            	   Desktop.getDesktop().browse(new URI("https://github.com/"));
	               }catch (IOException e) {
	            	   
	               }catch (URISyntaxException e) {
	            	   
	               }
	            }
	         };
		});
		
		profile_set_label.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent profile=FXMLLoader.load(getClass().getClassLoader().getResource("view/Profile_edit.fxml"));
						Scene scene = new Scene(profile);
						Stage primaryStage = (Stage) profile_set_label.getScene().getWindow();
						primaryStage.setTitle("HAM Talk");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});
		profile_exit.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					try {
						Parent profile=FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
						Scene scene = new Scene(profile);
						Stage primaryStage = (Stage) profile_exit.getScene().getWindow();
						primaryStage.setTitle("HAM Talk");
						primaryStage.setScene(scene);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		});
		
		
	}
}
