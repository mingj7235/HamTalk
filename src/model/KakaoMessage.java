package model;

import java.io.Serializable;

public class KakaoMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sendUserNum;//보낸이 번호
	private String sendUserName; //보낸이 이름
	private int receiveFriendNum;//받은이
	private String receiveFriendName;//받은이 이름
	private String sendComment;//메세지내용
	private int room_num; //방번호
	
	public KakaoMessage(int sendUserNum, String sendUserName, int receiveFriendNum, String receiveFriendName, 
			String sendComment, int room_num) {
		this.sendUserNum = sendUserNum;
		this.sendUserName = sendUserName;
		this.receiveFriendNum = receiveFriendNum;
		this.receiveFriendName = receiveFriendName;
		this.sendComment = sendComment;
		this.room_num = room_num;
	}

	public int getSendUserNum() {
		return sendUserNum;
	}

	public void setSendUserNum(int sendUserNum) {
		this.sendUserNum = sendUserNum;
	}

	public int getReceiveFriendNum() {
		return receiveFriendNum;
	}

	public void setReceiveFriendNum(int receiveFriendNum) {
		this.receiveFriendNum = receiveFriendNum;
	}

	public String getSendComment() {
		return sendComment;
	}

	public void setSendComment(String sendComment) {
		this.sendComment = sendComment;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getReceiveFriendName() {
		return receiveFriendName;
	}

	public void setReceiveFriendName(String receiveFriendName) {
		this.receiveFriendName = receiveFriendName;
	}

	public int getRoom_num() {
		return room_num;
	}

	public void setRoom_num(int room_num) {
		this.room_num = room_num;
	}
}
