package view;

import controller.Chat_w_01_controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class KakaoMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.setProperty("prism.lcdtext", "false");
		Font.loadFont(getClass().getResourceAsStream("/resources/NanumGothic.ttf"), 10);
		
		primaryStage.setTitle("Login");
		Parent root = FXMLLoader.load(KakaoMain.class.getResource("/view/Login.fxml"));
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println("윈도우창 닫힘");
				Chat_w_01_controller cwc = new Chat_w_01_controller();
				cwc.exitClient();
			}
		});
		

		System.out.println("김민재");
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}