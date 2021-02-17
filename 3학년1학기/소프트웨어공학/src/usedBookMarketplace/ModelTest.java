package usedBookMarketplace;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ModelTest {
	
	BookModel bookModel = new BookModel();
	UserModel userModel = new UserModel();
	Book book1 = new Book("sametitle", "000000", "author1", "publisher1", "2020", "user1", "10000", "good");
	Book book2 = new Book("sametitle", "000001", "author2", "publisher2", "2020", "user2", "20000", "fair");
	Book book3 = new Book("only title", null, null, null, null, null, null, null);
	User user1 = new User("name1", "user1", "pw1", "01000000000", "@google.com");
	User user2 = new User("name2", "user2", "pw2", "01011111111", "@naver.com");
	List<Book> bookList = new ArrayList<Book>();
	List<User> userList = new ArrayList<User>();
	
	//user
	@Test
	void testAddandDeleteUser() {
		assertTrue(userModel.isUserListEmpty());
		userModel.addUser(user1);
		assertFalse(userModel.isUserListEmpty());
		userModel.deleteUser(user1.getId());
		assertTrue(userModel.isUserListEmpty());
	}

	@Test
	void testIsThereSearchedUser() {
		userModel.addUser(user1);
		assertTrue(userModel.isThereSearchedUser(user1.getId()));
		userModel.deleteUser(user1.getId());
		assertFalse(userModel.isThereSearchedUser(user1.getId()));
	}

	@Test
	void testIsThereUserWthIdPw() {
		userModel.addUser(user1);
		assertTrue(userModel.isThereUserWthIdPw(user1.getId(), user1.getPassword()));
		userModel.deleteUser(user1.getId());
		assertFalse(userModel.isThereUserWthIdPw(user1.getId(), user1.getPassword()));
	}
	//convertUserState, setDeactivatedUserList, isUserDeactivated, isThereDeactivatedUser
	@Test
	void testDeactivatedUser() {
		userModel.addUser(user1);
		assertFalse(userModel.isThereDeactivatedUser()); //사용자 초기상태는 activated
		assertFalse(userModel.isUserDeactivated(user1.getId()));
		userModel.convertUserState(user1.getId());
		assertTrue(userModel.isThereDeactivatedUser());
		assertTrue(userModel.isUserDeactivated(user1.getId()));
		userModel.setDeactivatedUserList();
		assertEquals(user1.getId(), userModel.deactivatedUserList.get(0).getId());
		userModel.deleteUser(user1.getId());
	}

	@Test
	void testSetTotalUserList() {
		assertEquals(0, userModel.totalUserList.size());
		userModel.addUser(user1);
		userModel.setTotalUserList();
		assertEquals(1, userModel.totalUserList.size());
		userModel.addUser(user2);
		userModel.setTotalUserList();
		assertEquals(2, userModel.totalUserList.size());
		userModel.deleteUser(user1.getId());
		userModel.deleteUser(user2.getId());
	}
	
	@Test
	void testDeleteBookListWthUsrId() {
		bookModel.truncateBookTable();
		
		bookModel.addBook(book1);
		bookModel.addBook(book2);
		userModel.deleteBookListWthUsrId(user1.getId());
		bookModel.setTotalBookList();
		assertEquals("000001", bookModel.totalBookList.get(0).getISBN());
	}

	//book
	@Test
	void testAddandDeleteBook() {
		bookModel.truncateBookTable();
		
		assertTrue(bookModel.isBookListEmpty());
		bookModel.addBook(book1);
		assertFalse(bookModel.isBookListEmpty());
		bookModel.deleteBook(1);
		assertTrue(bookModel.isBookListEmpty());
	}

	@Test
	void testSetTotalBookList() {
		bookModel.truncateBookTable();
		
		assertEquals(0, bookModel.totalBookList.size());
		bookModel.addBook(book1);
		bookModel.setTotalBookList();
		assertEquals(1, bookModel.totalBookList.size());
		bookModel.addBook(book2);
		bookModel.setTotalBookList();
		assertEquals(2, bookModel.totalBookList.size());
	}
	
	//setSearchedBookList, isThereSearchedBook
	@Test
	void testSearchedBook() {
		bookModel.truncateBookTable();

		bookModel.addBook(book1);
		bookModel.addBook(book2);
		bookModel.addBook(book3);
		assertTrue(bookModel.isThereSearchedBook("제목", "sametitle"));
		bookModel.setSearchedBookList("제목", "sametitle");
		assertEquals(2, bookModel.searchedBookList.size());
		bookModel.setSearchedBookList("ISBN", "000000");
		assertEquals(1, bookModel.searchedBookList.size());
		assertFalse(bookModel.isThereSearchedBook("ISBN", "sametitle"));
		bookModel.setSearchedBookList("ISBN", "sametitle");
		assertTrue(bookModel.searchedBookList.size() == 0);
	}
	//isThereUserOwnBook, getUserOwnBookList
	@Test
	void testIsThereUserOwnBook() {
		bookModel.truncateBookTable();
		
		assertFalse(bookModel.isThereUserOwnBook(user1.getId()));
		bookModel.addBook(book1); //user1이 book1을 등록했음을 가정 (book1생성시 초기화값)
		assertTrue(bookModel.isThereUserOwnBook(user1.getId()));
		assertEquals(1, bookModel.getUserOwnBookList(user1.getId()).size());
	}

	@Test
	void testModifyBookInfo() {
		bookModel.truncateBookTable();
		
		bookModel.addBook(book1);
		bookModel.modifyBookInfo("제목", "differentTitle", 1);
		bookModel.setTotalBookList();
		assertEquals("differentTitle", bookModel.totalBookList.get(0).getTitle());
	}

	//checkBuyBookIntention, getBookSellerEmail, getBookSellerID, getBuyerEmail
	@Test
	void testBuyBook() {
		bookModel.truncateBookTable();
		userModel.addUser(user1);
		userModel.addUser(user2);
		bookModel.addBook(book1); //user1이 책을 등록
		bookModel.setTotalBookList();
		assertEquals("N", bookModel.totalBookList.get(0).getPurchaseIntention());
		bookModel.checkBuyBookIntention(1); //key=1
		bookModel.setTotalBookList();
		assertEquals(user1.getId(), bookModel.getBookSellerID(1)); //key=1
		assertEquals(user1.getEmail(), bookModel.getBookSellerEmail(1)); //key=1
		assertEquals(user2.getEmail(), bookModel.getBuyerEmail(user2.getId()));//user2가 책을 산다고 가정 
		assertEquals("Y", bookModel.totalBookList.get(0).getPurchaseIntention());
		userModel.deleteUser(user1.getId());
		userModel.deleteUser(user2.getId());	
	}
}
