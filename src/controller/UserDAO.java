package controller;

import java.sql.*;
import java.util.Scanner;

import db.DBConn;
import exception.MyException;
import javafx.application.Platform;
import model.AlertBox;
import model.KakaoMessage;
import model.MessagePane;
import model.MyMessagePane;
import model.UserDTO;

public class UserDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 회원가입
	public int signUp(UserDTO dto) throws MyException {
		int result = 0;
		conn = DBConn.getConnection();
		String sql = "INSERT INTO kakaoUser VALUES (user_seq.nextval, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPhonenum());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPassword());

			result = pstmt.executeUpdate();
			AlertBox.display("회원 가입", dto.getName()+"님 환영합니다!");
			System.out.println(result + "명 회원가입 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}

		return result;
	} // signUp()

	// 로그인
	public boolean login(String phonenum, String pw) throws MyException {
		boolean result = false;
		conn = DBConn.getConnection();
		String sql = "SELECT * FROM kakaoUser WHERE phonenum = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonenum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = true;
				UserDTO.nowUser.setUser_num(rs.getInt("user_num"));
				UserDTO.nowUser.setPhonenum(rs.getString("phonenum"));
				UserDTO.nowUser.setName(rs.getString("name"));
				UserDTO.nowUser.setPassword(rs.getString("password"));

				// 친구 목록
				sql = "SELECT * FROM kakaoUser WHERE phonenum != ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phonenum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					UserDTO dto = new UserDTO();
					dto.setName(rs.getString("name"));
					dto.setPassword(rs.getString("password"));
					dto.setPhonenum(rs.getString("phonenum"));
					dto.setUser_num(rs.getInt("user_num"));
					UserDTO.friends.add(dto);
				}
				System.out.println("친구목록추가 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return result;
	}

	// 채팅방 있으면 방번호리턴, 없으면 만들어서 방번호 리턴
	public int roomCheck(UserDTO nowUser, UserDTO friend) {
		int a, b; //작은번호가 a 큰번호 b
		int result = 0;
		if(nowUser.getUser_num() > friend.getUser_num()) {
			a = friend.getUser_num();
			b = nowUser.getUser_num();
		}else {
			a = nowUser.getUser_num();
			b = friend.getUser_num();
		}

		conn = DBConn.getConnection();
		try {
			String sql = "SELECT room_num FROM chatroom where user1_num = ? and user2_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			if(rs.next()) {//방있으면
				System.out.println("방있음");
				result = rs.getInt("room_num");
				System.out.println("방번호: "+result);
			}else {//방 없으면
				System.out.println("방없어서 방생성");
				sql = "INSERT INTO chatroom VALUES(chatroom_seq.NEXTVAL, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a);
				pstmt.setInt(2, b);
				pstmt.execute();//값넣기

				sql = "SELECT room_num FROM chatroom where user1_num = ? and user2_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a);
				pstmt.setInt(2, b);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt("room_num");
				}
				System.out.println("방번호: "+result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return result;
	}//roomCheck

	public void chatLog(KakaoMessage msg) {

		int user_num = msg.getSendUserNum();
		int room_num = msg.getRoom_num();

		conn = DBConn.getConnection();
		String sql = "INSERT INTO chatMessage VALUES(chatMessage_seq.NEXTVAL, ?, SYSDATE, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg.getSendComment());
			pstmt.setInt(2, user_num);
			pstmt.setInt(3, room_num);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
	}
	
	public String chatHistory (KakaoMessage getMessage) {
		String result ="";
		conn = DBConn.getConnection();
		try {
			if(Chat_w_01_controller.room_num == getMessage.getRoom_num()) { 
				if(getMessage.getSendUserNum() == UserDTO.nowUser.getUser_num()) { 
					String sql = "SELECT message FROM (SELECT * FROM CHATMESSAGE ORDER BY MESSAGE_TIME DESC) "
							+ "WHERE rownum <=10 and USER_NUM = ? and ROOM_NUM =?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, UserDTO.nowUser.getUser_num());
					pstmt.setInt(2, getMessage.getRoom_num());
					
					result = rs.getString("message");
					
				}else {
					String sql = "SELECT message FROM (SELECT * FROM CHATMESSAGE ORDER BY MESSAGE_TIME DESC) "
							+ "WHERE rownum <=10 and USER_NUM = ? and ROOM_NUM =?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, getMessage.getSendUserNum());
					pstmt.setInt(2, getMessage.getRoom_num());
					result = rs.getString("message");
				}
			}else { 
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return result;
		
	}


} // UserDAO
