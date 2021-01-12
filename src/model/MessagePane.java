package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MessagePane {
	private VBox vbox = new VBox();
	private String returnDate;
	public MessagePane(String name, String msg) {
		LocalDate ld = LocalDate.now();
		String year = ld.getYear()+"";
		String month;
		if(ld.getMonthValue() < 10) {
			month = "0"+ld.getMonthValue();
		}else {
			month = ld.getMonthValue()+"";
		}
		String day;
		if(ld.getDayOfMonth() < 10) {
			day = "0"+ld.getDayOfMonth();
		}else {
			day = ld.getDayOfMonth()+"";
		}
		returnDate = year+month+day;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("a HH:mm");
		
		Label nameLb = new Label(name);
		nameLb.setStyle("-fx-font-size: 12; -fx-font-family: NanumGothic");
		nameLb.setPadding(new Insets(0, 0, 2, 6));

		VBox vbox2 = new VBox();
		vbox2.setMaxWidth(180);
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_LEFT);
		
		
		Label messageLb = new Label(msg);
		messageLb.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 1; "
				+ "-fx-font-size: 14; -fx-font-family: NanumGothic;");
		messageLb.setWrapText(true);
		messageLb.setPadding(new Insets(5, 10, 5, 10));
		
		Label timeLb = new Label(sdf.format(date));
		timeLb.setPadding(new Insets(0, 0, 2, 5));
		timeLb.setStyle("-fx-font-family: Lucida Bright; -fx-font-size: 10;");

		vbox2.getChildren().add(messageLb);
		
		vbox.setMargin(hbox, new Insets(0, 0, 5, 0));
		
		vbox.getChildren().add(nameLb);
		vbox.getChildren().add(hbox);
		hbox.getChildren().add(vbox2);
		hbox.getChildren().add(timeLb);
	}
	public VBox getVbox() {
		return vbox;
	}
	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
}
