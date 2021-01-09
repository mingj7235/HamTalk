package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import exception.MyException;
import exception.SignUpException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserDTO;

public class Signup_controller implements Initializable{
	@FXML private Label signup_time;
	@FXML private TextField signup_signup_phonenum;
	@FXML private TextField signup_signup_name;
	@FXML private PasswordField signup_signup_password;
	@FXML private PasswordField signup_signup_passwordcheck;
	@FXML private Button signup_signup_btn;
	@FXML private Button signup_Back_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signup_signup_btn.setOnAction(e->{
			try {
				handleBtnsignup(e);
			} catch (MyException e1) {
				System.out.println(e1.getMessage());
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println(e1.getMessage());
			}
		});
		signup_Back_btn.setOnAction(e->handleBtnBack(e));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		signup_time.setText(sdf.format(date));

	}

	public void handleBtnsignup (ActionEvent event) throws MyException, SQLIntegrityConstraintViolationException {

		UserDAO dao = new UserDAO();
		SignUpException sue = new SignUpException();
		boolean phonenum;
		boolean pw;
		String pw2;
		int result = 0;

		try {
			UserDTO dto = new UserDTO();

			try {
				dto.setPhonenum(signup_signup_phonenum.getText());
				sue.phonenumCheck(dto.getPhonenum());
				phonenum = true;

				if (phonenum) {
					dto.setName(signup_signup_name.getText());

					dto.setPassword(signup_signup_password.getText());
					pw2 = signup_signup_passwordcheck.getText();

					sue.pwCheck(dto.getPassword(), pw2);
					pw = true;

					if (pw) {
						result = dao.signUp(dto);
					}
				}

			} catch(MyException e) {
				System.out.println(e.getMessage());
			}

			if(result != 0) {
				Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
				Scene scene = new Scene(login);
				Stage primaryStage = (Stage) signup_signup_btn.getScene().getWindow();
				primaryStage.setScene(scene);
			}else {
				System.out.println("회원가입 실패,,,");
			}

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	} // handleBtnsignup

	//뒤로가기 버튼
	public void handleBtnBack (ActionEvent event) {
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) signup_Back_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

} // class end
