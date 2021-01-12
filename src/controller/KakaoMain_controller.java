package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import exception.LogInException;
import exception.MyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class KakaoMain_controller implements Initializable{
	@FXML private Label Login_time;
	@FXML private Button kakaoMain_login_btn;
	@FXML private TextField KakaoMain_login_email;
	@FXML private PasswordField KakaoMain_login_password;
	@FXML private Button kakaoMain_signup_btn;
	@FXML private StackPane mainPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kakaoMain_login_btn.setOnAction(e->handleBtnLogin(e));
		
		KakaoMain_login_email.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					try {
						String id = KakaoMain_login_email.getText();
						String pw = KakaoMain_login_password.getText();
						UserDAO dao = new UserDAO();
						LogInException lie = new LogInException();
						
						try {
							lie.userCheck(id, pw);
							
							if (dao.login(id, pw)) {
								Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
								Scene scene = new Scene(login);
								Stage primaryStage = (Stage) kakaoMain_login_btn.getScene().getWindow();
								primaryStage.setTitle("Friends");
								primaryStage.setScene(scene);
							} else {
								System.out.println("로그인 실패");
							}
							
						} catch (MyException e) {
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}catch (Exception e) {
						
					}
				}
			};
		});
		
		
		KakaoMain_login_password.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					try {
						String id = KakaoMain_login_email.getText();
						String pw = KakaoMain_login_password.getText();
						UserDAO dao = new UserDAO();
						LogInException lie = new LogInException();
						
						try {
							lie.userCheck(id, pw);
							
							if (dao.login(id, pw)) {
								Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
								Scene scene = new Scene(login);
								Stage primaryStage = (Stage) kakaoMain_login_btn.getScene().getWindow();
								primaryStage.setTitle("Friends");
								primaryStage.setScene(scene);
							} else {
								System.out.println("로그인 실패");
							}
							
						} catch (MyException e) {
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}catch (Exception e) {
						
					}
				}
			};
		});
		kakaoMain_signup_btn.setOnAction(e->handleBtnSignup(e));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Login_time.setText(sdf.format(date));
	}
	
	public void handleBtnLogin (ActionEvent event) {
		String id = KakaoMain_login_email.getText();
		String pw = KakaoMain_login_password.getText();
		UserDAO dao = new UserDAO();
		LogInException lie = new LogInException();
		
		try {
			lie.userCheck(id, pw);
			
			if (dao.login(id, pw)) {
				Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Friends.fxml"));
				Scene scene = new Scene(login);
				Stage primaryStage = (Stage) kakaoMain_login_btn.getScene().getWindow();
				primaryStage.setTitle("Friends");
				primaryStage.setScene(scene);
			} else {
				System.out.println("로그인 실패");
			}
			
		} catch (MyException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	} // handleBtnLogin
	
	public void handleBtnSignup(ActionEvent event) {
		try {
			Parent signup=FXMLLoader.load(getClass().getClassLoader().getResource("view/signup.fxml"));
			Scene scene = new Scene(signup);
			Stage primaryStage = (Stage) kakaoMain_signup_btn.getScene().getWindow();
			primaryStage.setTitle("Sign up");
			primaryStage.setScene(scene);
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // handleBtnSignup



} // class end
