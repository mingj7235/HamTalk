package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MyMessagePane {
	private VBox vbox = new VBox();
	
	public MyMessagePane(String name, String msg) {
		Label nameLb = new Label(name);
		nameLb.setPadding(new Insets(0, 6, 2, 0));
		
		VBox vbox2 = new VBox();
		vbox2.setAlignment(Pos.TOP_RIGHT);
		vbox2.setMaxWidth(180);
		
		Label messageLb = new Label(msg);
		messageLb.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 1;");
		messageLb.setWrapText(true);
		messageLb.setPadding(new Insets(5, 10, 5, 10));
		vbox2.getChildren().add(messageLb);
		
		
		vbox.setMargin(vbox2, new Insets(0, 16, 5, 0));
		vbox.setMargin(nameLb, new Insets(0, 16, 0, 0));
		vbox.setAlignment(Pos.TOP_RIGHT);
		
		vbox.getChildren().add(nameLb);
		vbox.getChildren().add(vbox2);
	}

	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
	
	
	
}
