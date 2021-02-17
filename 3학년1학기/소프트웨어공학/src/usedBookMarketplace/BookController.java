package usedBookMarketplace;

import java.util.Scanner;


public abstract class BookController{	
	Scanner scnr = new Scanner(System.in); 
	Scanner sn = new Scanner(System.in);
	BookModel bookModel = new BookModel(); //model��ü ���� 
	View view = new View(); //view��ü ���� 
	public BookController() {}
	
	//��ü å ��� ����
	public void totalBookList(){
		bookModel.setTotalBookList();
		view.printBookList(bookModel.totalBookList, "total");
	}

	//å �˻�
	public void searchBookMain() {
		totalBookList();

		System.out.print("� ������ ã���ðڽ��ϱ�? >> ");
		String index = scnr.next();
		while((index.equals("����") || index.equals("ISBN") || index.equals("����") || index.equals("���ǻ�") || index.equals("���ǳ⵵") || index.equals("�Ǹ���id")) == false){
			System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���. >> ");
			index = sn.nextLine();
		}
		System.out.printf("å�� %s�� �Է����ּ���.>> ", index);
		String info = sn.nextLine();
		System.out.println("");
		if(bookModel.isThereSearchedBook(index, info)) {
			bookModel.setSearchedBookList(index, info);
			view.printBookList(bookModel.searchedBookList, "searched");
		}
		else {
			bookModel.setSearchedBookList(index, info);
			System.out.println("������ �����ϴ� å�� �������� �ʽ��ϴ�.");
			System.out.println("");
		}
	}
	
	//å �˻� (������, ����� ���� 1�� ���)
	public void searchBook() {
		String continue_search = "Y";
		System.out.println("");
		System.out.println("å �˻�>>");
		System.out.println("");

		while(continue_search.equals("Y")) {
			//��ϵ� å�� ���� �� 
			if(bookModel.isBookListEmpty()) {
				System.out.println("��ϵǾ��ִ� å�� �����ϴ�.");
				System.out.println("");
				break;
			}
			searchBookMain(); // ������ �ް� �� ������ �����ϴ� å ��� ����Ʈ 
			System.out.print("�˻��� ��� �Ͻðڽ��ϱ�?[Y/N] >> ");
			continue_search = scnr.next();
			while((continue_search.equals("Y") || continue_search.equals("N")) == false) {
				System.out.print("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.[Y/N] >> ");
				continue_search = scnr.nextLine();	
			}
		}
		System.out.println("�˻��� ����Ǿ����ϴ�.");
		System.out.println("");
	}
}
