package usedBookMarketplace;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class BookModel extends DBInfo{	
	//사용자와 관리자가 찾는 책의 목록
	List<Book> searchedBookList = new ArrayList<Book>();
	List<Book> totalBookList = new ArrayList<Book>();

	//전체 책 목록 
	public void setTotalBookList(){	
		totalBookList.clear();
		String query = "SELECT * FROM book"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			rs = pStmt.executeQuery(query); 
			while(rs.next()) {
				Book book = new Book();
				book.setKey(rs.getInt("uniquekey"));
				book.setTitle(rs.getString("title"));						
				book.setISBN(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationYear(rs.getString("publication_year"));
				book.setBookSellerID(rs.getString("bookseller_id"));
				book.setPrice(rs.getString("price"));
				book.setState(rs.getString("state"));
				book.setPurchaseIntention(rs.getString("purchase_intention"));

				totalBookList.add(book);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}
	
	//지금까지 등록된 책이 있나 확인
	public boolean isBookListEmpty() {
		String query = "SELECT * FROM book";
		
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

	//사용자와 관리자가 찾는 책의 목록 set
	public void setSearchedBookList(String index, String info){
		searchedBookList.clear();
		String query;
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
	
			switch(index) {
				case "제목":
					query = "SELECT * FROM book WHERE title = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);	
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}	
					break;
				case "ISBN":
					query = "SELECT * FROM book WHERE isbn = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);
						
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}
					break;
				case "저자":
					query = "SELECT * FROM book WHERE author = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);
						
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}
					break;
				case "출판사":
					query = "SELECT * FROM book WHERE publisher = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);
						
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}
					break;
				case "출판년도":
					query = "SELECT * FROM book WHERE publication_year = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);
						
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}
					break;
				case "판매자id":
					query = "SELECT * FROM book WHERE bookseller_id = '" + info + "'"; 
					pStmt = conn.prepareStatement(query);
					rs = pStmt.executeQuery(query);
						
					while(rs.next()) {
						Book book = new Book();
						book.setKey(rs.getInt("uniquekey"));
						book.setTitle(rs.getString("title"));						
						book.setISBN(rs.getString("isbn"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setPublicationYear(rs.getString("publication_year"));
						book.setBookSellerID(rs.getString("bookseller_id"));
						book.setPrice(rs.getString("price"));
						book.setState(rs.getString("state"));
	
						searchedBookList.add(book);
					}
					break;
			}
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

	//주어진 제목, 저자, 판매자id의 정보를 가진 책이 존재하는지
	//사용자와 관리자가 책을 찾을 때 그 책이 있는지 없는지 확인(사용자, 관리자 1번 기능)
	public boolean isThereSearchedBook(String index, String info) {		
		String query;		
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 

			switch(index) {
				case "제목":
					query = "SELECT * FROM book WHERE title = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}
					break;	
				case "ISBN":
					query = "SELECT * FROM book WHERE isbn = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}	
					break;
				case "저자":
					query = "SELECT * FROM book WHERE author = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}		
					break;	
				case "판매자id":
					query = "SELECT * FROM book WHERE bookseller_id = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}
					break;
				case "출판사":
					query = "SELECT * FROM book WHERE publisher = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}	
					break;
				case "출판년도":
					query = "SELECT * FROM book WHERE publication_year = ?"; 
					pStmt = conn.prepareStatement(query);
					pStmt.setString(1, info);
					rs = pStmt.executeQuery();
					if(rs.next()) {
						return true;
					}	
					break;
			}
		}
		catch(Exception e) {
			System.out.println("db isThereSelectedBook 실패");
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		
		return false;
	}
	
	//구매자(사용자)가 판매의도를 표현(사용자 2번 기능) 
	public void checkBuyBookIntention(int key) {
			String query1 = "SELECT * FROM book WHERE uniquekey = ?";
			String query2 = "UPDATE book SET purchase_intention = ? WHERE uniquekey = ?";
			
			try {
				conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
				pStmt = conn.prepareStatement(query1);
				pStmt.setInt(1, key);			
				rs = pStmt.executeQuery(); 				
				
				PreparedStatement pStmt2 = conn.prepareStatement(query2);

				if(rs.next()) {
					if(rs.getString("purchase_intention").equals("N")) { 
						pStmt2.setString(1, "Y");
						pStmt2.setInt(2, key);
						pStmt2.executeUpdate(); 
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				closeAll(rs, pStmt, conn);
			}
		}
		
	//구매자(사용자)가 책을 구매시에(사용자 2번 기능)
	//판매자id
	//본인이 등록한 책 회수시에 본인확인
	public String getBookSellerID(int key) {
		String sellerId = null;
		String query = "SELECT bookseller_id FROM book WHERE uniquekey = ?"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setInt(1, key);
			rs = pStmt.executeQuery(); 
			if(rs.next()) {
				sellerId = rs.getString("bookseller_id");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return sellerId;
	}
	
	//판매자email
	public String getBookSellerEmail(int key) {
		String sellerId = getBookSellerID(key);
		String email = null;
		String query = "SELECT email FROM User WHERE id = ?"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setString(1, sellerId);
			rs = pStmt.executeQuery(); 
			if(rs.next()) {
				email = rs.getString("email");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return email;
	}
	
	//구매자email
	public String getBuyerEmail(String userId) {
		String email = null;
		String query = "SELECT email FROM User WHERE id = ?"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setString(1, userId);
			rs = pStmt.executeQuery();
			if(rs.next())
				email = rs.getString("email");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return email;
	}
	
	//사용자가 책 등록시(사용자 3번 기능) 
	public void addBook(Book book) {
		String query = "INSERT INTO book VALUE (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			
			pStmt.setString(1, book.getTitle());
			pStmt.setString(2, book.getISBN());
			pStmt.setString(3, book.getAuthor());
			pStmt.setString(4, book.getPublisher());
			pStmt.setString(5, book.getPublicationYear());
			pStmt.setString(6, book.getBookSellerID());
			pStmt.setString(7, book.getPrice());
			pStmt.setString(8, book.getState());
			pStmt.setString(9, book.getPurchaseIntention());
			pStmt.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

	//사용자가 등록한 책이 있는지(사용자 4번 기능)
	public boolean isThereUserOwnBook(String userId) {
		String query = "SELECT * FROM book WHERE bookseller_id = ?"; 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setString(1, userId);
			rs = pStmt.executeQuery(); 
			if(rs.next()) {
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

	//사용자 본인이 등록한 책의 리스트, 사용자가 자신의 책 삭제 및 수정(사용자 4번 기능) 
	public List<Book> getUserOwnBookList(String userId) {
		List<Book> userOwnBook = new ArrayList<Book>();
		
		String query = "SELECT * FROM book WHERE bookseller_id = ?"; //  table에 있는 모든 레코드 출력 
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query); 
			pStmt.setString(1, userId);
			rs = pStmt.executeQuery(); //SELECT문에서 필요 
			while(rs.next()) {
				Book book = new Book();
				book.setKey(rs.getInt("uniquekey"));
				book.setTitle(rs.getString("title"));						
				book.setISBN(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublicationYear(rs.getString("publication_year"));
				book.setBookSellerID(rs.getString("bookseller_id"));
				book.setPrice(rs.getString("price"));
				book.setState(rs.getString("state"));
				book.setPurchaseIntention(rs.getString("purchase_intention"));
				
				userOwnBook.add(book);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
		return userOwnBook;
	}

	//관리자가 문제가 되는 책 삭제(관리자 2번 기능), 사용자가 본인이 등록한 책 삭제(사용자 4번 기능)
	public void deleteBook(int key) {
		String query = "DELETE FROM book WHERE uniquekey = ?";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, key);			
			pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}
	
	//사용자가 본인이 등록한 책 수정할 때(사용자 4번 기능) 
	public void modifyBookInfo(String index, String info, int key){
		String query;
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			switch(index) {
			case "제목":
				query = "UPDATE book set title = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query); 
				break;
			case "ISBN":
				query = "UPDATE book set isbn = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query); 
				break;
			case "저자":
				query = "UPDATE book set author = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query); 
				break;
			case "출판사":
				query = "UPDATE book set publisher = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query); 
				break;
			case "출판년도":
				query = "UPDATE book set publication_year = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query);
				break;
			case "가격":
				query = "UPDATE book set price = ? where uniquekey = ?"; 
				pStmt = conn.prepareStatement(query);
				break;
			case "상태":
				query = "UPDATE book set state = ? where uniquekey = ?";
				pStmt = conn.prepareStatement(query);
				break;
			}
			pStmt.setString(1, info);
			pStmt.setInt(2, key);
			pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

	//junit test 때문에 추가 
	public void truncateBookTable() {
		String query = "TRUNCATE BOOK";
		try {
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); 
			pStmt = conn.prepareStatement(query);
			pStmt.execute(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pStmt, conn);
		}
	}

}

