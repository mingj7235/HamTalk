package model;

import java.util.ArrayList;


public class UserDTO {
	public static UserDTO nowUser = new UserDTO();
	public static ArrayList<UserDTO> friends = new ArrayList<>();
	public static UserDTO withFriend = new UserDTO(); //채팅방접속시 함께하는 친구 정보
	
	
	private int user_num;
	private String phonenum;
	private String name;
	private String password;
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
