package usedBookMarketplace;

import java.util.List;

public class View {
	//�˻��� å ����̳� ��ü å ��� ��� 
	public void printBookList(List<Book> bookList, String type){
		switch(type) {
		case "total":
			System.out.println("<<��ü å ���>>");
			break;
			
		case "searched":
			System.out.println("<<�˻��� å ���>>");
			break;
		}
		
		for(Book book : bookList) {
			System.out.printf("%d|����: %-20.20s	|ISBN: %-10.10s	|����: %-15.15s	|���ǻ�: %-10.10s	|���ǳ⵵: %-10.10s	|�Ǹ���id: %-20.20s	|����: %-15.15s	|����: %-15.15s	|�����ǵ�: %-5.5s\n", 
					book.getKey(), book.getTitle(), book.getISBN(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getBookSellerID(), book.getPrice(), book.getState(), book.getPurchaseIntention());
		}
		System.out.println("");
	}	

	//����ڰ� ����� å ��� ��� (�˻��� å ��ϰ� ��ü å ��� ����̶� �ٸ��� �� ������ ����ڰ� ����� å ����� ��� �ÿ��� ����� id�� ��� ��) 
	public void printUsrBookList(List<Book> usrBookList) {
		System.out.println("<<����ڰ� ����� å ���>>");
		
		for(Book book : usrBookList) {
			System.out.printf("%d|����: %-20.20s	|ISBN: %-10.10s	|����: %-15.15s	|���ǻ�: %-10.10s	|���ǳ⵵: %-10.10s	|����: %-15.15s	|����: %-15.15s	|�����ǵ�: %-5.5s\n", 
					book.getKey(), book.getTitle(), book.getISBN(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getPrice(), book.getState(), book.getPurchaseIntention());
		}
	}

	//��Ȱ��ȭ ������ ����� ����̳� ��ü ����� ��� ���
	public void printUserList(List<User> UserList, String type)
	{
		System.out.println("");
		switch(type) {
			case "deactivated":
				System.out.println("<<��Ȱ��ȭ ������ ����� ���>>");
				break;
			case "total":
				System.out.println("<<��ü ����� ���>>");
				break;
		}
		
		for(User user : UserList) {
			System.out.printf("|�̸�: %-5.5s	|���̵�: %-15.15s	|��й�ȣ: %-15.15s	|��ȭ��ȣ: %-20.20s	|�̸���: %-25.25s	|����: %-15.15s\n", 
					user.getName(), user.getId(), user.getPassword(), user.getPhoneNum(), user.getEmail(), user.getState());
		}	
		System.out.println("");
	}

	//market���� �ʿ��� ������ ���
	public void printFirstPage() {
		System.out.println("<<�߰����� ���α׷�>>");
		System.out.println("");
		System.out.println("1. ȸ������ ");
		System.out.println("2. ����ڷα��� ");
		System.out.println("3. �����ڷα��� ");
		System.out.println("");
		System.out.print("������ �����Ͻðڽ��ϱ�? >> ");
	}
	
	public void printSecondPageforAdmin() {
		System.out.println("<< ��� >>");
		System.out.println("");
		System.out.println("1. å �˻��ϱ�");
		System.out.println("2. å �����ϱ�");
		System.out.println("3. ��ü ����� ��Ϻ���");
		System.out.println("4. ����� ���� ��ȯ�ϱ�");
		System.out.println("5. ����� Ż���Ű��");
		System.out.println("");
		System.out.println("���α׷��� �����Ͻ÷��� 0�� �Է��ϼ���.");
		System.out.println("");
		System.out.print("������ �����Ͻðڽ��ϱ�? >> ");
	}  
	
	public void printSecondPageforUser() {
		System.out.println("<< ��� >>");
		System.out.println("");
		System.out.println("1. å �˻��ϱ�");
		System.out.println("2. å �����ϱ�");
		System.out.println("3. å ����ϱ�");
		System.out.println("4. ������ ����� å ��Ϻ���(���� �� �����ϱ�)");
		System.out.println("");
		System.out.println("���α׷��� �����Ͻ÷��� 0�� �Է��ϼ���.");
		System.out.println("");
		System.out.print("������ �����Ͻðڽ��ϱ�? >> ");
	}
	
}
