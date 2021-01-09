package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MessagePane {
	private VBox vbox = new VBox();
	public MessagePane(String name, String msg) {
		Label nameLb = new Label(name);
		nameLb.setPadding(new Insets(0, 0, 2, 6));

		VBox vbox2 = new VBox();
		vbox2.setMaxWidth(180);
		Label messageLb = new Label(msg);
		messageLb.setStyle("-fx-background-radius: 10; -fx-background-color: #fee100; -fx-border-width: 1;");
		messageLb.setWrapText(true);
		messageLb.setPadding(new Insets(5, 10, 5, 10));
		vbox2.getChildren().add(messageLb);
		
		vbox.setMargin(messageLb, new Insets(0, 0, 5, 0));
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
