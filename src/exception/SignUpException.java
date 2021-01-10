package exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import db.DBConn;

public class SignUpException {

	// 모든 정보가 입력됐는지 확인
	public void emptyCheck(String name, String phonenum, String pw1, String pw2) throws MyException {
		if(name.isEmpty() || phonenum.isEmpty() || pw1.isEmpty() || pw2.isEmpty()) {
			throw new MyException ("회원 가입", "정보를 모두 입력해 주십시오.");
		}
	}
	
	// 전화번호 확인
	public void phonenumCheck(String tel) throws MyException {
		boolean check = Pattern.matches(
				//"^[0-9]*$",
				"^\\d{11}",
				tel);
		if (!check) {
			throw new MyException("회원 가입", "11자리 숫자만 입력 가능합니다.");
		}
	}

	// 비밀번호 확인
	public void pwCheck(String pw1, String pw2) throws MyException {
		int cnt1 = 0;
		int cnt2 = 0;
		
		for(int i=0; i<pw1.length(); i++) {
			char ch = pw1.charAt(i);
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				cnt1++;
			} else if(ch >= '0' && ch <= '9') {
				cnt2++;
			}
		}
		
		if(cnt1 == 0 || cnt2 == 0) {
			throw new MyException (
					"회원 가입",
			"비밀번호는 영문자와 숫자를 포함해야 합니다."		
					);
		}
		
		if(!pw1.equals(pw2)) {
			throw new MyException (
					"회원 가입",
					"비밀번호가 일치하지 않습니다."
					);
		}
	}
	
	// 번호 중복 확인
	public void duplicationCheck (String phonenum) throws MyException {
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM kakaoUser WHERE phonenum = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  phonenum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				throw new MyException (
						"회원 가입",
						"이미 등록된 번호입니다."
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		
	}
	
}
