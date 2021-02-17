package usedBookMarketplace;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookControllerforAdmin extends BookController{	
	public BookControllerforAdmin() {}
	
	//책 삭제 기능 (관리자 2번 기능)
	public void deleteBook(){	
		String continue_deleteBook = "Y";
		System.out.println("");
		System.out.println("책 삭제>>");
		System.out.println("");

		int key_toDelete = 0;
	loop:
		//삭제를 계속 하고자하는 동안 
		while(continue_deleteBook.equals("Y")) {
			boolean bookWthKey = false;
			//등록되어있는 책이 있는지
			if(bookModel.isBookListEmpty()) {
				System.out.println("등록되어있는 책이 없습니다.");
				System.out.println("");

				break;
			}
			//책 검색하기 
			searchBookMain();
			//검색한 책이 있을 때 
			if(bookModel.searchedBookList.isEmpty() == false) {
				System.out.print("삭제할 책의 번호를 입력해주세요.(책 삭제 종료하기 0) >> "); 
				//key 값에 int가 아닌 다른 유형의 값을 넣었을 때의 예외처리(콘솔한정)
				while(true) {
					try{
						key_toDelete = scnr.nextInt();
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("잘못 입력하셨습니다. 삭제할 책의 번호를 다시 입력해주세요.(책 삭제 종료하기 0) >> ");
					}
				}
				//콘솔한정
				do{
					//삭제 진행 
					if(key_toDelete != 0) {
						//입력한 번호의 책이 찾은 책목록 중 존재하는지 확인(콘솔한정)
						for(Book book: bookModel.searchedBookList) {
							if(book.key == key_toDelete) {
								bookWthKey = true;
								break;
							}
						}
						//입력한 번호의 책이 찾은 책목록 중 없을 때 
						if(bookWthKey == false) {
							System.out.print("입력한 번호의 책이 없습니다. 다시 입력해주세요. >> ");
							//key 값에 int가 아닌 다른 유형의 값을 넣었을 때의 예외처리(콘솔한정)
							while(true) {
								try{
									key_toDelete = scnr.nextInt();
									break;
								}
								catch(InputMismatchException e) {
									scnr = new Scanner(System.in);
									System.out.print("잘못 입력하셨습니다. 책의 번호를 다시 입력해주세요. >> ");
								}
							}
						}	
					}
					//삭제 종료하기(0)
					else 
						break loop;
				} while(bookWthKey == false);
				
				System.out.print("선택하신 책을 삭제하시겠습니까?[Y/N] >> ");
				String confirm_deleteBook = scnr.next();
				System.out.println("");

				// Y/N 외에 다른 것을 입력했다면(콘솔한정)
				while((confirm_deleteBook.equals("Y") || confirm_deleteBook.equals("N")) == false) {
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
					confirm_deleteBook = scnr.nextLine();	
				}
				//선택한 책을 삭제하길 원할 때(잘못 누르지 않았을 때)
				if(confirm_deleteBook.equals("Y")) {
					bookModel.deleteBook(key_toDelete);
					System.out.printf("책이 삭제되었습니다.");
					System.out.println("");
				}
				//책 삭제 취소 
				else {
					System.out.printf("책 삭제가 취소되었습니다.");
					System.out.println("");
				}
			}
			System.out.print("삭제를 계속하시겠습니까?[Y/N] >> ");
			continue_deleteBook = scnr.next();
			System.out.println("");
			//콘솔한정 
			while((continue_deleteBook.equals("Y") || continue_deleteBook.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				continue_deleteBook = scnr.nextLine();	
			}
			System.out.println("");
		}
		System.out.println("삭제가 종료되었습니다.");
		System.out.println("");
	}
}