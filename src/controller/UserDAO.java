package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import db.DBConn;
import exception.MyException;
import javafx.scene.image.Image;
import model.AlertBox;
import model.ChatListPane;
import model.KakaoMessage;
import model.UserDTO;
import oracle.sql.BLOB;

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
			
			sql = "INSERT INTO user_data (user_num) "
					+ "VALUES (("
					+ "select k.user_num "
					+ "FROM kakaouser k "
					+ "WHERE k.NAME = ? AND k.PHONENUM = ?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhonenum());
			pstmt.execute();
			
			sql = "SELECT user_num FROM kakaouser WHERE phonenum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhonenum());
			rs = pstmt.executeQuery();
			int usernum = 0;
			if(rs.next()) {
				usernum = rs.getInt("user_num");
			}
			
			sql = "SELECT user_image from user_data WHERE user_num = ? for update";
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usernum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BLOB blob = (BLOB) rs.getBlob("user_image");
				File file = new File("./src/imgs/emptyUser.png");
				FileInputStream is = new FileInputStream(file);
				OutputStream os = blob.setBinaryStream(1);
				
				byte[] buffer = new byte[1024];
				int length = -1;
				while((length = is.read(buffer))!= -1) {
					os.write(buffer, 0, length);
				}
				os.close();
				
				conn.commit();
			}
			
			
			
			System.out.println("유저정보입력완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		String sql = "SELECT k.user_num, k.phonenum, k.name, k.password, u.user_status "
				+ "FROM kakaoUser k, user_data u "
				+ "WHERE phonenum = ? AND k.user_num = u.user_num";

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
				UserDTO.nowUser.setStatus(rs.getString("user_status"));
				if(getImage(rs.getInt("user_num"), conn) == null) {
					UserDTO.nowUser.setImage(new Image("/imgs/emptyUser.png"));					
				}else {
					UserDTO.nowUser.setImage(getImage(rs.getInt("user_num"), conn));					
				}
				// 친구 목록
				sql = "SELECT k.user_num, k.phonenum, k.name, k.password, u.user_status "
						+ "FROM kakaoUser k, user_data u "
						+ "WHERE phonenum != ? AND k.user_num = u.user_num";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phonenum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					UserDTO dto = new UserDTO();
					dto.setName(rs.getString("name"));
					dto.setPassword(rs.getString("password"));
					dto.setPhonenum(rs.getString("phonenum"));
					dto.setUser_num(rs.getInt("user_num"));
					dto.setStatus(rs.getString("user_status"));
					if(getImage(rs.getInt("user_num"), conn) == null) {
						dto.setImage(new Image("/imgs/emptyUser.png"));						
					}else {
						dto.setImage(getImage(rs.getInt("user_num"), conn));
					}
					UserDTO.friends.add(dto);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		
		
		System.out.println("친구목록추가 완료");
		return result;
	}
	public void friendReview() {
		UserDTO.friends.clear(); //친구전체삭제
		try {
			conn = DBConn.getConnection();
			String sql = "SELECT k.user_num, k.phonenum, k.name, k.password, u.user_status "
					+ "FROM kakaoUser k, user_data u "
					+ "WHERE phonenum != ? AND k.user_num = u.user_num";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UserDTO.nowUser.getPhonenum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setPhonenum(rs.getString("phonenum"));
				dto.setUser_num(rs.getInt("user_num"));
				dto.setStatus(rs.getString("user_status"));
				if(getImage(rs.getInt("user_num"), conn) == null) {
					dto.setImage(new Image("/imgs/emptyUser.png"));						
				}else {
					dto.setImage(getImage(rs.getInt("user_num"), conn));
				}
				UserDTO.friends.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
	}
	Image getImage(int userNum, Connection conn) {
		String sql = "SELECT user_image from user_data WHERE user_num = ?";
		File file = new File("file");
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userNum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				BLOB blob = (BLOB)rs.getBlob("user_image");
				InputStream is = blob.getBinaryStream();
				FileOutputStream os = new FileOutputStream(file);
				int size = blob.getBufferSize();
				byte [] buffer = new byte[size];
				int length = -1;
				while((length = is.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				os.close();
				is.close();
			}
			conn.commit();
			conn.setAutoCommit(true);			
		}catch (NullPointerException e) {
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image image = new Image(file.toURI().toString());
		file.delete();
		return image;
	}
	
	
	// 채팅방 목록 및 순서
	public int isChatExist (UserDTO nowUser, UserDTO friend) {
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
		String sql = "SELECT DISTINCT cm.room_num, cr.user1_num, cr.user2_num "
				+ "FROM chatmessage cm JOIN chatroom cr ON cm.room_num = cr.room_num "
				+ "WHERE user1_num = ? and user2_num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			if(rs.next()) {//방있으면
				System.out.println("이전 대화 내용 있음");
				result = rs.getInt("room_num");
				System.out.println("방번호: "+result);
			} else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // isChatExist

	// 채팅방 최신 순
	public ArrayList<Integer> roomOrder() {
		ArrayList<Integer> roomNum = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "SELECT DISTINCT room_num "
				+ "FROM (SELECT room_num, max(message_time) latest FROM chatmessage GROUP BY room_num) "
				+ "ORDER BY latest desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				roomNum.add(rs.getInt("room_num"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}

		return roomNum;
	}
	public ArrayList<ChatListPane> lastChatOrder(int myNum) {
		ArrayList<ChatListPane> arr = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql;
		try {
			sql = "SELECT DISTINCT cm1.room_num, cr.user1_num, k1.NAME name1 , cr.user2_num, k2.name name2, cm2.message, to_char(latest, 'YYYYMMDDHH24MI') time, latest "
					+ "FROM (SELECT room_num, max(message_time) latest FROM chatmessage GROUP BY room_num) cm1, chatroom cr, chatmessage cm2, kakaouser k1, kakaouser k2 "
					+ "WHERE cm1.room_num = cr.room_num AND latest = cm2.message_time "
					+ "AND (cr.USER1_NUM = ? OR cr.USER2_NUM = ?) AND k1.USER_NUM = cr.USER1_NUM AND k2.USER_NUM  = cr.USER2_NUM "
					+ "ORDER BY latest DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myNum);
			pstmt.setInt(2, myNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int friendNum = 0;
				String friendName = "";
				if(rs.getInt("user1_num") == myNum) {
					friendNum = rs.getInt("user2_num");
					friendName = rs.getString("name2");
				}else {
					friendNum = rs.getInt("user1_num");
					friendName = rs.getString("name1");
				}
				String message = rs.getString("message");
				String timeStr = rs.getString("time");

				
				int year = Integer.parseInt(timeStr.substring(0, 4));
				int month = Integer.parseInt(timeStr.substring(4, 6));
				int day = Integer.parseInt(timeStr.substring(6, 8));
				int hour = Integer.parseInt(timeStr.substring(8, 10));
				int minute = Integer.parseInt(timeStr.substring(10, 12));

				String ampm;
				if(hour <12) ampm = "오전";
				else ampm = "오후";

				if(hour>12) hour -= 12;

				String time;
				LocalDate ld = LocalDate.now();
				if(year == ld.getYear() && month == ld.getMonthValue() && day == ld.getDayOfMonth()) {
					String hour2, minute2;
					if(hour < 10) {
						hour2 = "0"+hour;
					}else {
						hour2 = ""+hour;
					}
					if(minute < 10) {
						minute2 = "0"+minute;
					}else {
						minute2 = ""+minute;
					}
					time = ampm+hour2+"시"+minute2+"분";
				}else {
					time = month+"월"+day+"일";
				}
				//여기부터 접속시간확인
				boolean lastOnOff;
				Time messageTime = rs.getTime("latest");
				if(myNum < friendNum) {
					sql = "SELECT lastLogOn_user1 FROM chatroom WHERE room_num = ?";					
				}else {
					sql = "SELECT lastLogOn_user2 FROM chatroom WHERE room_num = ?";					
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("room_num"));
				ResultSet rs2 = pstmt.executeQuery();
				Time myTime = null;
				if(rs2.next()) {
					myTime = rs2.getTime(1);
				}
				if(myTime.after(messageTime)) { //내 마지막 접속시간이 마지막 채팅보다 이후라면
					lastOnOff = false;
				}else {
					lastOnOff = true;
				}
				if(rs2 != null) rs2.close();
				ChatListPane clp = new ChatListPane(friendNum, friendName, message, time, lastOnOff);
				arr.add(clp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return arr;
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

	public ArrayList<KakaoMessage> chatHistory (int count) {
		int messageCnt = count;
		ArrayList<KakaoMessage> messageArr = new ArrayList<>();      
		conn = DBConn.getConnection();
		try {
			String sql = "SELECT message_num, message, to_char(MESSAGE_TIME, 'YYYYMMDDHH24MI') time, user_num, ROOM_NUM "
					+ "FROM (SELECT * FROM CHATMESSAGE ORDER BY MESSAGE_TIME DESC) "
					+ "WHERE rownum <= ? and ROOM_NUM = ? ORDER BY  MESSAGE_time ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageCnt);
			pstmt.setInt(2, Chat_w_01_controller.room_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				KakaoMessage kakaoMessage = new KakaoMessage();
				kakaoMessage.setSendComment(rs.getString("message"));
				kakaoMessage.setRoom_num(rs.getInt("room_num"));
				kakaoMessage.setSendUserNum(rs.getInt("user_num"));
				kakaoMessage.setTime(rs.getString("time"));
				messageArr.add(kakaoMessage);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return messageArr;
	}
	
	public void profileSave(File file, String status) {
		conn = DBConn.getConnection();
		//직렬화
		try {
			String sql = "SELECT user_image from user_data WHERE user_num = ? for update";
			if(file != null) {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, UserDTO.nowUser.getUser_num());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					BLOB blob = (BLOB) rs.getBlob("user_image");
					System.out.println(file);
					FileInputStream is = new FileInputStream(file);
					OutputStream os = blob.setBinaryStream(1);
					
					byte[] buffer = new byte[1024];
					int length = -1;
					while((length = is.read(buffer))!= -1) {
						os.write(buffer, 0, length);
					}
					os.close();
					
					conn.commit();
				}
			}
			sql = "UPDATE user_data SET user_status = ? WHERE user_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, UserDTO.nowUser.getUser_num());
			pstmt.execute();
			
		}catch (NullPointerException e) {
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
	}
	public void profileSave(String status) {
		conn = DBConn.getConnection();
		//직렬화
		String sql = "UPDATE user_data SET user_status = ? WHERE user_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, UserDTO.nowUser.getUser_num());
			pstmt.execute();
			
		}catch (NullPointerException e) {
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		
	}
	
	public void lastLogOnDate(UserDTO nowUser, UserDTO withFriend, int roomNum) {//채팅 마지막으로 확인한 시간 추가하는 메소드
		conn = DBConn.getConnection();
		String sql;
		if(nowUser.getUser_num() < withFriend.getUser_num()) {
			sql = "UPDATE chatroom "
					+ "SET lastLogOn_user1 = sysdate "
					+ "WHERE room_num = ?";			
		}else {
			sql = "UPDATE chatroom "
					+ "SET lastLogOn_user2 = sysdate "
					+ "WHERE room_num = ?";			
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // UserDAO