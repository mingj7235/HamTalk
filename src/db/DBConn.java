package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	private static final String JDBC_Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	private static final String user = "kakao";
	private static final String pw = "kakao";
	private static Connection dbconn;

	public static final Connection getConnection() {
		if (dbconn == null) {
			try {
				Class.forName(JDBC_Driver);
				dbconn = DriverManager.getConnection(DB_URL, user, pw);

				System.out.println(dbconn.isClosed()? "접속 종료" : "접속 중");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dbconn;
	}

	public static void dbClose() {
		if(dbconn != null) {
			try {
				dbconn.close();

				System.out.println(dbconn.isClosed()? "접속 종료" : "접속 중");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbconn = null;
	}

	public static void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(dbconn != null) {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				System.out.println(dbconn.isClosed()? "접속 종료" : "접속 중");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dbconn = null;
		pstmt = null;
		rs = null;
	}


}
