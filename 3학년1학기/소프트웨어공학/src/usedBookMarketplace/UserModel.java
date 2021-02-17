package usedBookMarketplace;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/* 관리자 user 관련 기능
 * 3. 전체 사용자 리스트 보기 
 * 4. 사용자 상태 전환하기 
 * 5. 사용자 탈퇴시키기
 */

public class UserModel extends DBInfo{
	
	List<User> totalUserList = new ArrayList<User>();
	List<User> deactivatedUserList = new ArrayList<User>();

		
	//사용자 회원가입
	public void addUser(User user) {
		String query = "INSERT INTO User VALUE (?, ?, ?, ?, ?, ?)";
		try {
			//User user = new User(name, id, pw, phoneNum, email);
			/*
			user.setName(name);
			user.setId(id);
			user.setPassword(pw);
			user.setPhoneNum(phoneNum);
			user.setEmail(email);
			user.setState("activated");
			*/
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getId());
			pStmt.setString(3, user.getPassword());
			pStmt.setString(4, user.getPhoneNum());
			pStmt.setString(5, user.getEmail());
			pStmt.setString(6, user.getState());
			
			pStmt.executeUpdate();

			pStmt.close(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}
	
	//회원가입시에 동일한 id가 존재해서는 안됨(사용자 로그인)
	public boolean isThereSearchedUser(String id) {
		String query = "SELECT * FROM User WHERE id = ?";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();

			while(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return false;
	}
	
	//사용자가 입력한 아이디와 비밀번호를 가진 사용자가 사용자(회원가입된) 목록에 있는지(사용자 로그인) 
	public boolean isThereUserWthIdPw(String id, String pw) {
		String query = "SELECT * FROM User WHERE id = ? AND password = ?";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);
			pStmt.setString(2, pw);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return false;
	}
	
	//사용자가 비활성화 상태(로그인 불가상태)인지(사용자 로그인)
	public boolean isUserDeactivated(String id) {
		String query = "SELECT * FROM User WHERE state = 'deactivated'";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			rs = pStmt.executeQuery(query);
			while(rs.next()) {
				if(id.equals(rs.getString("id"))) {
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return false;
	}

	//전체 목록 출력 (관리자 기능 3번, 4번)
	public void setTotalUserList(){
		totalUserList.clear();
		
		String query = "SELECT * FROM User"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			rs = pStmt.executeQuery(query); 
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name")); 
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNum(rs.getString("phoneNum")); 
				user.setEmail(rs.getString("email"));
				user.setState(rs.getString("state"));
				totalUserList.add(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

	//사용자 상태 전환(관리자 기능 4번)
	public void convertUserState(String id) {
			String query1 = "SELECT * FROM User WHERE id = ?"; 
			String query2 = "UPDATE User SET state = ? WHERE id = ?";
			try {
				conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
				pStmt = conn.prepareStatement(query1);
				pStmt.setString(1, id);
				rs = pStmt.executeQuery();
				
				PreparedStatement pStmt2 = conn.prepareStatement(query2);
				
				if(rs.next()) {
					if(rs.getString("state").equals("activated")) {
						pStmt2.setString(1, "deactivated");
					}
					else
						pStmt2.setString(1, "activated");
					
					pStmt2.setString(2, id);
					pStmt2.executeUpdate(); 
				}
				if(pStmt2 != null) {
					pStmt2.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				closeAll(rs, pStmt, conn);
			}
		}
		
	//비활성화된 사용자가 있는지(관리자 기능 5번)
	public boolean isThereDeactivatedUser() {
			String query = "SELECT * FROM User WHERE state = 'deactivated'";
			try {
				conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
				pStmt = conn.prepareStatement(query);
				rs = pStmt.executeQuery(query);
				while(rs.next()) {
					return true;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				closeAll(rs, pStmt, conn);
			}
			return false;
		}
	
	//비활성화 상태의 사용자 리스트(관리자 기능 5번)
	public void setDeactivatedUserList() {
		deactivatedUserList.clear();
		
		String query = "SELECT * FROM User WHERE state = 'deactivated'"; 
		
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			rs = pStmt.executeQuery();
				
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name")); 
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNum(rs.getString("phoneNum")); 
				user.setEmail(rs.getString("email"));
				user.setState(rs.getString("state"));
				deactivatedUserList.add(user);
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}	

	//(관리자 기능 5번)
	public void deleteUser(String id) {
		String query = "DELETE FROM User WHERE id = ?";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, id);			
			pStmt.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

	//등록되어있는 사용자가 없을 때 (관리자 기능 3번, 5번)
	public boolean isUserListEmpty() {
		String query = "SELECT * FROM User";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			rs = pStmt.executeQuery(query); 
			if(rs.next()) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return true;
	}

	//사용자 탈퇴시 그 사용자가 등록했던 책들도 함께 삭제 (관리자 기능 5번)
	public void deleteBookListWthUsrId(String userId) {
		String query = "DELETE FROM book WHERE bookseller_id = ?";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, userId);			
			pStmt.executeUpdate(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}	
	
}

