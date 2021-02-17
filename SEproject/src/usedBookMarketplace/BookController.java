package usedBookMarketplace;

import java.util.Scanner;


public abstract class BookController{	
	Scanner scnr = new Scanner(System.in); 
	Scanner sn = new Scanner(System.in);
	BookModel bookModel = new BookModel(); //model객체 생성 
	View view = new View(); //view객체 생성 
	public BookController() {}
	
	//전체 책 목록 보기
	public void totalBookList(){
		bookModel.setTotalBookList();
		view.printBookList(bookModel.totalBookList, "total");
	}

	//책 검색
	public void searchBookMain() {
		totalBookList();

		System.out.print("어떤 정보로 찾으시겠습니까? >> ");
		String index = scnr.next();
		while((index.equals("제목") || index.equals("ISBN") || index.equals("저자") || index.equals("출판사") || index.equals("출판년도") || index.equals("판매자id")) == false){
			System.out.print("잘못 입력하셨습니다. 다시 입력해주세요. >> ");
			index = sn.nextLine();
		}
		System.out.printf("책의 %s를 입력해주세요.>> ", index);
		String info = sn.nextLine();
		System.out.println("");
		if(bookModel.isThereSearchedBook(index, info)) {
			bookModel.setSearchedBookList(index, info);
			view.printBookList(bookModel.searchedBookList, "searched");
		}
		else {
			bookModel.setSearchedBookList(index, info);
			System.out.println("조건을 만족하는 책이 존재하지 않습니다.");
			System.out.println("");
		}
	}
	
	//책 검색 (관리자, 사용자 공통 1번 기능)
	public void searchBook() {
		String continue_search = "Y";
		System.out.println("");
		System.out.println("책 검색>>");
		System.out.println("");

		while(continue_search.equals("Y")) {
			//등록된 책이 없을 때 
			if(bookModel.isBookListEmpty()) {
				System.out.println("등록되어있는 책이 없습니다.");
				System.out.println("");
				break;
			}
			searchBookMain(); // 조건을 받고 그 조건을 만족하는 책 목록 프린트 
			System.out.print("검색을 계속 하시겠습니까?[Y/N] >> ");
			continue_search = scnr.next();
			while((continue_search.equals("Y") || continue_search.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				continue_search = scnr.nextLine();	
			}
		}
		System.out.println("검색이 종료되었습니다.");
		System.out.println("");
	}
}
