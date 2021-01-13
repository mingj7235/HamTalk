package controller;

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

public class Profile_friends_contoller implements Initializable{
   
   @FXML private ImageView profile_friends_chat_label;
   @FXML private ImageView profile_friends_exit;
   @FXML private ImageView friendImage;
   @FXML private Label friend_name;
   @FXML private Label friend_status;
   
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   
	   friend_name.setText(UserDTO.withFriend.getName());
	   friend_status.setText(UserDTO.withFriend.getStatus());
	   friendImage.setImage(UserDTO.withFriend.getImage());
      profile_friends_chat_label.setOnMousePressed(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY) {
               try {
                  UserDAO dao = new UserDAO();
                  int room_num = dao.roomCheck(UserDTO.nowUser, UserDTO.withFriend);
                  Chat_w_01_controller.room_num = room_num;
                  
                  Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/Chat_w_01.fxml"));
                  Scene scene = new Scene(signup);
                  Stage primaryStage = (Stage) profile_friends_chat_label.getScene().getWindow();
                  primaryStage.setTitle("Chatting");
                  primaryStage.setScene(scene);
               }catch (Exception e) {
                  e.printStackTrace();
               }
            }
         };
      });
      
      profile_friends_exit.setOnMousePressed(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent event) {
            if(event.getButton() == MouseButton.PRIMARY) {
               try {
                     
                  Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
                  Scene scene = new Scene(signup);
                  Stage primaryStage = (Stage) profile_friends_exit.getScene().getWindow();
                  primaryStage.setTitle("Chatting");
                  primaryStage.setScene(scene);
               }catch (Exception e) {
                  e.printStackTrace();
               }
            }
         };
      });
      
   }
   
   
   
   
   
}