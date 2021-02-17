package usedBookMarketplace;

import java.util.InputMismatchException;
import java.util.Scanner;


public class BookControllerforUser extends BookController {
	Scanner scnr = new Scanner(System.in);
	Scanner scnrRegister = new Scanner(System.in);

	public BookControllerforUser() {}
	
	//책 구입 (사용자 2번 기능)
	public void buyBook(String userId) {
		String continue_buy = "Y";
		System.out.println("");
		System.out.println("책 구매>>");
		System.out.println("");

		int key_toBuy = 0;

	loop:
		//구입을 계속 하고자하는 동안 
		while(continue_buy.equals("Y")) {
			String bookWthKey = "F";
			if(bookModel.isBookListEmpty()) {
				System.out.println("등록되어있는 책이 없습니다.");
				System.out.println("");
				break;
			}
			searchBookMain();
			if(bookModel.searchedBookList.isEmpty() == false) {
				System.out.print("구매할 책의 번호를 입력해주세요.(책 구매 종료하기 0) >> ");
				//key 값에 int가 아닌 다른 유형의 값을 넣었을 때의 예외처리(콘솔한정)
				while(true) {
					try{
						key_toBuy = scnr.nextInt();
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("잘못 입력하셨습니다. 구매할 책의 번호를 다시 입력해주세요.(책 구매 종료하기 0) >> ");
					}
				}
				do{
					//구매진행 
					if(key_toBuy != 0) {
						//입력한 번호의 책이 찾은 책목록 중 존재하는지 확인(콘솔한정)
						for(Book book: bookModel.searchedBookList) {
							if(book.getKey() == key_toBuy) {
								bookWthKey = "T"; 
								break;
							}
						}
						//입력한 번호의 책이 찾은 책목록 중 없을 때 
						if(bookWthKey.equals("F")) {
							System.out.print("입력한 번호의 책이 없습니다. 다시 입력해주세요. >> ");
							//key 값에 int가 아닌 다른 유형의 값을 넣었을 때의 예외처리(콘솔한정)
							while(true) {
								try{
									key_toBuy = scnr.nextInt();
									break;
								}
								catch(InputMismatchException e) {
									scnr = new Scanner(System.in);
									System.out.print("잘못 입력하셨습니다. 구매할 책의 번호를 다시 입력해주세요.(책 구매 종료하기 0) >> ");
								}
							}
						}	
					}
					//구매 종료하기(0)
					else
						break loop;
				} while(bookWthKey.equals("F"));
			
				//자기가 구매한 책을 선택한 경우에 회수가능 
				if(bookModel.getBookSellerID(key_toBuy).equals(userId)) {
					System.out.print("본인이 등록한 책입니다. 회수하시겠습니까? [Y/N] >> ");
					String withdraw = scnr.next();
					//콘솔한정 
					while((withdraw.equals("Y") || withdraw.equals("N")) == false) {
						System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
						withdraw = scnr.nextLine();	
					}	
					//회수
					if(withdraw.equals("Y")) {
						bookModel.deleteBook(key_toBuy);
						System.out.println("회수되었습니다.");
					}
					//회수취소
					else if(withdraw.equals("N")){
						System.out.println("회수가 취소되었습니다.");
					}
				}
				
				else {
					System.out.print("선택하신 책을 구매하시겠습니까?[Y/N]>> ");
					String confirm_buyBook = scnr.next();
					//콘솔한정 
					while((confirm_buyBook.equals("Y") || confirm_buyBook.equals("N")) == false) {
						System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
						confirm_buyBook = scnr.nextLine();	
					}		
					//구매
					if(confirm_buyBook.equals("Y")) {
						String buyerEmail = bookModel.getBuyerEmail(userId);
						String sellerEmail = bookModel.getBookSellerEmail(key_toBuy);
						bookModel.checkBuyBookIntention(key_toBuy);
						System.out.println("");
						System.out.printf("구매자의 이메일(%s)로 메일이 발송되었습니다.", buyerEmail);
						System.out.println("");
						System.out.printf("판매자의 이메일(%s)로 메일이 발송되었습니다.", sellerEmail);
						System.out.println("");
					}
					//구매취소
					else {
						System.out.printf("책 구매가 취소되었습니다.");
						System.out.println("");
					}
				}
			}
			System.out.println("");
			System.out.print("구매를 계속하시겠습니까?[Y/N] >> ");
			continue_buy = scnr.next();
			System.out.println("");
			//콘솔한정
			while((continue_buy.equals("Y") || continue_buy.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				continue_buy = scnr.nextLine();	
			}
		}
		System.out.println("구매가 종료되었습니다.");
		System.out.println("");
	}

	//책 등록 (사용자 3번 기능)
	public void registerBook(String bookSellerId) {
		System.out.println("책 등록>>");
		Book book = new Book();
		
		String continue_register = "Y";

		String title = null;
		String isbn = null;
		String author = null;
		String publisher = null;
		String publication_year = null;
		String price = null;
		String state = null;
					
		boolean isntThereTitle = true;
		//등록을 계속 하고자하는 동안 
		while(continue_register.equals("Y")) {
			while(isntThereTitle) {
				System.out.println("책의 정보를 입력하세요.");
				System.out.print("제목: ");
				title = scnrRegister.nextLine();
				System.out.print("ISBN: "); 
				isbn = scnrRegister.nextLine();
				System.out.print("저자: ");
				author = scnrRegister.nextLine();
				System.out.print("출판사: ");
				publisher = scnrRegister.nextLine();
				System.out.print("출판년도: ");
				publication_year = scnrRegister.nextLine();
				System.out.print("가격: ");
				price = scnrRegister.nextLine();
				System.out.print("상태(excellent, good, fair): ");
				state = scnrRegister.nextLine();
			
				while((state.equals("excellent") || state.equals("good") || state.equals("fair") || state.isEmpty()) == false) {
					System.out.println("상태를 잘못 입력하셨습니다. 다시 입력해주세요.");
					System.out.print("상태(excellent, good, fair): ");
					state = scnrRegister.nextLine();
				}
				
				if(title.isEmpty()) {
					System.out.println("제목이 입력되지 않았습니다. 다시 입력해주세요.");
					isntThereTitle = true; 
				}
				else
					isntThereTitle = false;
				}
			
			book.setTitle(title);
			book.setISBN(isbn);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setPublicationYear(publication_year);
			book.setBookSellerID(bookSellerId); //판매자id
			book.setPrice(price);
			book.setState(state);
			bookModel.addBook(book);
			//bookDAO.insertBook(title, isbn, author, publisher, publication_year, bookSellerId, price, state);
			System.out.println("책을 등록하였습니다.");
			System.out.println("");	
			System.out.print("등록을 계속하시겠습니까?[Y/N] >> ");
			continue_register = scnr.next();
			while((continue_register.equals("Y") || continue_register.equals("N")) == false) {
				System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				continue_register = scnr.nextLine();	
			}
			if(continue_register.equals("Y"))
				isntThereTitle = true;
		}
		System.out.println("책 등록을 종료합니다.");
	}
	
	//책 삭제(deleteBook), 책 정보 수정(reviseBook)은 deleteReviseBookforUser를 위한 클래스 : private 선언
	//본인이 등록한 책 삭제 로직
	private void deleteBook(String userId, int key) {
		System.out.print("선택하신 책을 삭제하시겠습니까?[Y/N] >> ");
		String confirm_deleteBook = scnr.next();
		//콘솔한정 
		while((confirm_deleteBook.equals("Y") || confirm_deleteBook.equals("N")) == false) {
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
			confirm_deleteBook = scnr.nextLine();	
			System.out.println("");
		}
		System.out.println("");
		//선택한 책을 삭제하길 원할 때
		if(confirm_deleteBook.equals("Y")) {
			bookModel.deleteBook(key);
			System.out.printf("책이 삭제되었습니다.");
			System.out.println("");
			
			if(bookModel.isThereUserOwnBook(userId)) {
				view.printUsrBookList(bookModel.getUserOwnBookList(userId));
			}
			else {
				System.out.println("사용자가 등록하신 책이 없습니다.");
			}
			System.out.println("");	
			System.out.println("");
		}
	}

	//본인이 등록한 책 정보수정 로직 
	private  void reviseBook(String userId, int key) {
		System.out.print("선택하신 책을 수정하시겠습니까?[Y/N] >> ");
		String confirm_reviseBook = scnr.next();
		//콘솔한정 
		while((confirm_reviseBook.equals("Y") || confirm_reviseBook.equals("N")) == false) {
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
			confirm_reviseBook = scnr.nextLine();	
			System.out.println("");
		}
		//선택한 책을 수정하길 원할 때
		if(confirm_reviseBook.equals("Y")) {
			System.out.print("어떤 정보를 수정하시겠습니까? >> ");
			String index = scnr.next();
			//콘솔한정
			while((index.equals("제목") || index.equals("ISBN") || index.equals("저자") || index.equals("출판사") || index.equals("출판년도") || index.equals("가격") || index.equals("상태")) == false) {
				System.out.print("잘못 입력하셨습니다. 다시 입력해주세요. >> ");
				index = scnrRegister.nextLine();
				System.out.println("");
			}
			System.out.println("");
			System.out.print("어떻게 수정하시겠습니까? >> ");
			String info = scnr.next();
			System.out.println("");
			bookModel.modifyBookInfo(index, info, key);
			System.out.println("책 정보를 수정하였습니다.");
			view.printUsrBookList(bookModel.getUserOwnBookList(userId));
			System.out.println("");
			System.out.println("");
		}
	}

	//자기가 등록한 책 보여주고 삭제나 수정 선택 가능 (사용자 4번 기능)
	public void deleteReviseBookforUser(String userId) {
		String continue_deleteBook = "Y";
		System.out.println("");
		System.out.println("본인 책 삭제 및 수정>>");
		System.out.println("");
		int key = 0;
	loop:	
		//삭제나 수정을 계속 하고자하는 동안 
		while(continue_deleteBook.equals("Y")) {
			boolean bookWthKey = false;
			//등록되어있는 책이 있는지
			if(bookModel.isBookListEmpty()) {
				System.out.println("등록되어있는 책이 없습니다.");
				break;
			}
			//자신이 등록했던 책이 있는지
			else if(bookModel.isThereUserOwnBook(userId)) {
				view.printUsrBookList(bookModel.getUserOwnBookList(userId));
				System.out.println("");
				System.out.print("삭제하거나 수정할 책의 번호를 입력하세요. (삭제 및 수정하기 종료 0) >> ");
				//key 값에 int가 아닌 다른 유형의 값을 넣었을 때의 오류처리(콘솔한정) 
				while(true) {
					try{
						key = scnr.nextInt();
						System.out.println("");
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("잘못 입력하셨습니다. 삭제하거나 수정할 책의 번호를 다시 입력해주세요.(삭제 및 수정하기 종료 0) >> ");
					}
				}
	
				//콘솔한정
				do{
					if(key != 0) {
						for(Book book: bookModel.getUserOwnBookList(userId)) {
							if(book.key == key) {
								bookWthKey = true;
								break;
							}
						}
						//입력한 번호의 책이 자신의 책목록 중 없을 때 
						if(bookWthKey == false) {
							System.out.print("입력한 번호의 책이 없습니다. 다시 입력해주세요>> ");
							key = scnr.nextInt();	
							System.out.println("");
						}	
					}
					else 
						break loop;
				} while(bookWthKey == false);
				System.out.println("1. 삭제하기");
				System.out.println("2. 수정하기");
				System.out.println("");
				System.out.print("무엇을 진행하시겠습니까? >> ");
				int choice = 0;
				//콘솔한정
				while(true) {
					try{
						choice = scnr.nextInt();
						if(choice != 1 && choice != 2) {
							System.out.print("잘못 입력하셨습니다. 다시 선택해주세요. >> ");
							choice = scnr.nextInt();
						}
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("잘못 입력하셨습니다. 다시 선택해주세요. >> ");
					}
				}
				System.out.println("");
				switch(choice) {
				case 1:
					deleteBook(userId, key);
					break;
				case 2:
					reviseBook(userId, key);
					break;
				}
			}
			//자신이 등록했던 책이 없으면 
			else {
				System.out.println("본인이 등록한 책이 없습니다.");
				System.out.println("");
				continue_deleteBook = "F";
			}
			
			if(continue_deleteBook.equals("Y")) {
				System.out.print("삭제 및 수정을 계속하시겠습니까?[Y/N] >> ");
				continue_deleteBook = scnr.next();
				System.out.println("");
				while((continue_deleteBook.equals("Y") || continue_deleteBook.equals("N")) == false) {
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
					continue_deleteBook = scnr.nextLine();	
				}
			}
		}
		System.out.println("삭제 및 수정하기가 종료되었습니다.");
		System.out.println("");
	}
	
}
