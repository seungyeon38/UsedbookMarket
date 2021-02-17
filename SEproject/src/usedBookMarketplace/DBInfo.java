package usedBookMarketplace;

import java.sql.*;

public abstract class DBInfo {
	protected final String DB_URL = "jdbc:mysql://localhost:3306/UsedBookMarket?serverTimezone=Asia/Seoul&useSSL=false";
	protected final String USER_NAME = "root";
	protected final String PASSWORD = "Tmddus38*";
	
	protected Connection conn = null; 
	protected PreparedStatement pStmt = null;
	protected ResultSet rs = null;
	

	//db 연결해제 
	public void closeAll(ResultSet rs, PreparedStatement pStmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pStmt!=null) {
			try {
				pStmt.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}			}
		if(conn!=null) {
			try {
				pStmt.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
}
