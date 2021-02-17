package usedBookMarketplace;

import java.util.List;

public class View {
	//검색된 책 목록이나 전체 책 목록 출력 
	public void printBookList(List<Book> bookList, String type){
		switch(type) {
		case "total":
			System.out.println("<<전체 책 목록>>");
			break;
			
		case "searched":
			System.out.println("<<검색된 책 목록>>");
			break;
		}
		
		for(Book book : bookList) {
			System.out.printf("%d|제목: %-20.20s	|ISBN: %-10.10s	|저자: %-15.15s	|출판사: %-10.10s	|출판년도: %-10.10s	|판매자id: %-20.20s	|가격: %-15.15s	|상태: %-15.15s	|구매의도: %-5.5s\n", 
					book.getKey(), book.getTitle(), book.getISBN(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getBookSellerID(), book.getPrice(), book.getState(), book.getPurchaseIntention());
		}
		System.out.println("");
	}	

	//사용자가 등록한 책 목록 출력 (검색된 책 목록과 전체 책 목록 출력이랑 다르게 한 이유는 사용자가 등록한 책 목록을 출력 시에는 사용자 id가 없어도 됨) 
	public void printUsrBookList(List<Book> usrBookList) {
		System.out.println("<<사용자가 등록한 책 목록>>");
		
		for(Book book : usrBookList) {
			System.out.printf("%d|제목: %-20.20s	|ISBN: %-10.10s	|저자: %-15.15s	|출판사: %-10.10s	|출판년도: %-10.10s	|가격: %-15.15s	|상태: %-15.15s	|구매의도: %-5.5s\n", 
					book.getKey(), book.getTitle(), book.getISBN(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getPrice(), book.getState(), book.getPurchaseIntention());
		}
	}

	//비활성화 상태의 사용자 목록이나 전체 사용자 목록 출력
	public void printUserList(List<User> UserList, String type)
	{
		System.out.println("");
		switch(type) {
			case "deactivated":
				System.out.println("<<비활성화 상태의 사용자 목록>>");
				break;
			case "total":
				System.out.println("<<전체 사용자 목록>>");
				break;
		}
		
		for(User user : UserList) {
			System.out.printf("|이름: %-5.5s	|아이디: %-15.15s	|비밀번호: %-15.15s	|전화번호: %-20.20s	|이메일: %-25.25s	|상태: %-15.15s\n", 
					user.getName(), user.getId(), user.getPassword(), user.getPhoneNum(), user.getEmail(), user.getState());
		}	
		System.out.println("");
	}

	//market에서 필요한 페이지 출력
	public void printFirstPage() {
		System.out.println("<<중고장터 프로그램>>");
		System.out.println("");
		System.out.println("1. 회원가입 ");
		System.out.println("2. 사용자로그인 ");
		System.out.println("3. 관리자로그인 ");
		System.out.println("");
		System.out.print("무엇을 선택하시겠습니까? >> ");
	}
	
	public void printSecondPageforAdmin() {
		System.out.println("<< 목록 >>");
		System.out.println("");
		System.out.println("1. 책 검색하기");
		System.out.println("2. 책 삭제하기");
		System.out.println("3. 전체 사용자 목록보기");
		System.out.println("4. 사용자 상태 전환하기");
		System.out.println("5. 사용자 탈퇴시키기");
		System.out.println("");
		System.out.println("프로그램을 종료하시려면 0을 입력하세요.");
		System.out.println("");
		System.out.print("무엇을 선택하시겠습니까? >> ");
	}  
	
	public void printSecondPageforUser() {
		System.out.println("<< 목록 >>");
		System.out.println("");
		System.out.println("1. 책 검색하기");
		System.out.println("2. 책 구입하기");
		System.out.println("3. 책 등록하기");
		System.out.println("4. 본인이 등록한 책 목록보기(삭제 및 수정하기)");
		System.out.println("");
		System.out.println("프로그램을 종료하시려면 0을 입력하세요.");
		System.out.println("");
		System.out.print("무엇을 선택하시겠습니까? >> ");
	}
	
}
