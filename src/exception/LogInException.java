package exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;

public class LogInException {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;


	// 모든 정보가 입력됐는지 확인
	public void emptyCheck(String phoneNum, String pw) throws MyException {
		if(phoneNum.isEmpty() || pw.isEmpty()) {
			throw new MyException ("로그인", "정보를 모두 입력해 주십시오.");
		}
	}

	// 가입된 회원인지 확인 후 비밀번호 확인
	public void userCheck (String phoneNum, String pw) throws MyException {
		conn = DBConn.getConnection();
		String sql = "SELECT * FROM kakaoUser WHERE phonenum = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			rs = pstmt.executeQuery();

			if(!rs.next()) {
				throw new MyException (
						"로그인",
						"존재하지 않는 회원입니다."
						);
			}
			else {
				if(!pw.equals(rs.getString("password"))) {
					throw new MyException (
							"로그인", 
							"비밀번호가 틀렸습니다."
							);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
	}


}
