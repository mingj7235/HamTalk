package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.KakaoMessage;
import model.MessagePane;
import model.MyMessagePane;
import model.UserDTO;

public class Chat_w_01_controller implements Initializable{
	@FXML private Label Chats_time;
	@FXML private Label chat_chat_name_label;
	@FXML private BorderPane chat_w_01_mainpane;
	@FXML private Pane chat_w_01_pane;
	@FXML private Slider chat_slider_opacity;
	@FXML private TextField chat_write_messages;
	@FXML private TextArea chat_textarea;
	@FXML private Button chat_send_button;
	@FXML private Button chat_back_btn;
	@FXML private VBox chat_vbox;
	@FXML private ScrollPane chat_scroll;
	
	public static int room_num; //현재 내가 접속한 방번호
	
	Socket socket;
	
	void startClient () {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 5001));
					//내 번호 보내기
					sendMyNum(UserDTO.nowUser.getUser_num());
					
					Platform.runLater(() -> {
						chat_send_button.setDisable(false);
					}); 
				}catch (IOException e) {
					e.printStackTrace();
					if(!socket.isClosed()) {stopClient();}
					return;
				}
				chatHistoryReceive();
				receive();
			}
		};
		thread.start();
	}
	
	void stopClient() {
		try {
			Platform.runLater(() -> {
				chat_send_button.setDisable(true);
			});
			
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}catch (Exception e) {}
	}
	
	void chatHistoryReceive() {
		UserDAO dao = new UserDAO();
		ArrayList<KakaoMessage> arr = dao.chatHistory(10);
		for (int i = 0; i < arr.size(); i++) {
			KakaoMessage message = arr.get(i);
			if(message.getSendUserNum() == UserDTO.nowUser.getUser_num()) { //이게 내가 보낸 메세지였다면
				String data = message.getSendComment();
				MyMessagePane mine = new MyMessagePane(UserDTO.nowUser.getName(), data);
				Platform.runLater(() ->{
					chat_vbox.getChildren().add(mine.getVbox());
				});
			}else {//친구가보낸메세지였다면
				String data = message.getSendComment();
				MessagePane fmp = new MessagePane(UserDTO.withFriend.getName(), data);
				Platform.runLater(() -> {
					chat_vbox.getChildren().add(fmp.getVbox());
				});
			}
		}
	}
	void receive () {
		UserDAO ud = new UserDAO();
		
		chat_vbox.heightProperty().addListener((observable, oldValue, newValue) ->
			chat_scroll.setVvalue(newValue.doubleValue())
				);
		chat_scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		
		chat_vbox.setPadding(new Insets(5,0,15,0));
		
		while (true) {
			try {
				byte [] byteArr = new byte [1024];
				InputStream is = socket.getInputStream();
				
				int readByteCount = is.read(byteArr);
				if (readByteCount == -1) {
					throw new IOException();
				}
//				String data = new String (byteArr, 0, readByteCount, "UTF-8");
				KakaoMessage getMessage = toMessage(byteArr, KakaoMessage.class);
				
				
				if(room_num == getMessage.getRoom_num()) { //받은 채팅이 내가 접속한 방번호랑 같으면?
					String data;
					if(getMessage.getSendUserNum() == UserDTO.nowUser.getUser_num()) { //내가보낸거면
//						data = "나: "+getMessage.getSendComment();
						data = getMessage.getSendComment();
						MyMessagePane mine = new MyMessagePane(UserDTO.nowUser.getName(), data);
						Platform.runLater(() ->{
							chat_vbox.getChildren().add(mine.getVbox());
						});
					}else {//남이보낸거면
//						data = getMessage.getSendUserName() +": "+getMessage.getSendComment();
						data = getMessage.getSendComment();
						
						MessagePane fmp = new MessagePane(getMessage.getSendUserName(), data);
						Platform.runLater(() -> {
							chat_vbox.getChildren().add(fmp.getVbox());
						});
					}
//					Platform.runLater(() -> chat_textarea.appendText(data+"\n"));					
				}else { //내가 접속한 방이 아니라면?
					
				}
				
			}catch (Exception e) {
				stopClient();
				break;
			}
		}
	}
	
	void send (String data) { //보내기구현
		if(data != null && !data.equals("")) {			
			Thread thread = new Thread () {
				public void run() {
					try {
//					String withName = UserDTO.nowUser.getName()+":\n"+data;
//					byte[] byteArr = withName.getBytes("UTF-8");
						//메세지객체로변경
						KakaoMessage message = new KakaoMessage(UserDTO.nowUser.getUser_num(), UserDTO.nowUser.getName()
								, UserDTO.withFriend.getUser_num(), UserDTO.withFriend.getName(), data, room_num);
						
						byte [] bytes = null;
						ByteArrayOutputStream bos = new ByteArrayOutputStream(); //바이트배열 데이터 입출력 스트림
						ObjectOutputStream oos = new ObjectOutputStream(bos); //객체 직렬화
						oos.writeObject(message); //객체 직렬화 메소드 사용
						oos.flush(); //잔류바이트 출력
						oos.close();
						bytes = bos.toByteArray();//byte array로 변환
						
						byte [] data = bytes;
						OutputStream os = socket.getOutputStream();
						os.write(data);
						os.flush();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		}
	}
	
	void sendMyNum(int num) { //내 번호 보내는 메소드
		try {
			OutputStream os = socket.getOutputStream();
			String StrNum = ""+num;
			byte[] byteArr = StrNum.getBytes("UTF-8");
			os.write(byteArr);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chat_send_button.setOnAction(e->handleBtnSend(e));
		chat_back_btn.setOnAction(e->handleBtnBack(e));
		chat_chat_name_label.setText(UserDTO.withFriend.getName());
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Chats_time.setText(sdf.format(date));
		chat_write_messages.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode() ==KeyCode.ENTER) {
					try {
						send(chat_write_messages.getText());
						chat_write_messages.setText("");
					}catch (Exception e) {
					}
				}
			};
		});

		chat_slider_opacity.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				chat_w_01_mainpane.setOpacity(chat_slider_opacity.getValue() /100.0);
			}
		});
		startClient(); //바로 서버시작
	}
	
	
	public void handleBtnBack(ActionEvent event) {
		try {
			stopClient();
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) chat_back_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String writeMessages () { //?어디다쓴걸까
		return chat_write_messages.getText();
	}


	public void handleBtnSend(ActionEvent event) {
		send(chat_write_messages.getText());
		chat_write_messages.setText("");
	}
	
	// 새로 추가
	private KakaoMessage toMessage(byte[] byteArr, Class<KakaoMessage> class1) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (Exception e) {
		}
		return class1.cast(obj);
	}
	

	
}
