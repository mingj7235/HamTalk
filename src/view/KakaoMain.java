package view;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
		System.out.println("고희광");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
