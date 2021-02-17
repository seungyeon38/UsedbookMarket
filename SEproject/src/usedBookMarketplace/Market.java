package usedBookMarketplace;

import java.util.InputMismatchException;
import java.util.Scanner;


//controller
public class Market {
	Scanner scnr = new Scanner(System.in);
		
	UserControllerforUser userControllerforUser = new UserControllerforUser();
	UserControllerforAdmin userControllerforAdmin = new UserControllerforAdmin();
	BookControllerforUser bookControllerforUser = new BookControllerforUser();
	BookControllerforAdmin bookControllerforAdmin = new BookControllerforAdmin();
	AdminController adminController = new AdminController();
	View view = new View();
	
	private String userId;
	private boolean isAdmin = false;
	
	protected void marketOpen() {
		boolean start = true;
		while(start) { //���α׷��� ����� ������ ��� ����
			view.printFirstPage();
			int choice = 0;
			while(true) {
				try{
					choice = scnr.nextInt();
					if(choice != 1 && choice != 2 && choice != 3) {
						System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ��� [1~3]: ");
						choice = scnr.nextInt();
					}
					break;
				}
				catch(InputMismatchException e) {
					scnr = new Scanner(System.in);
					System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ��� [1~3]: ");
				}
			}
			System.out.println("");
			switch(choice) {
			case 1: 
					userControllerforUser.signUp();
					String startLogin;
					System.out.print("�α����Ͻðڽ��ϱ�? [Y/N] >> ");
					startLogin = scnr.next();
					System.out.println("");
					while((startLogin.equals("Y") || startLogin.equals("N")) == false) {
						System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���. �α����Ͻðڽ��ϱ�? [Y/N] >> ");
						startLogin = scnr.next();	
						System.out.println("");
					}
					if(startLogin.equals("Y")) {
						userControllerforUser.login();
						userId = userControllerforUser.getUserId();
					}
					else {
						System.out.println("���α׷��� ����˴ϴ�");
						System.exit(0); // ���α׷� �� 
					}
					start = false;
					break;
			case 2:	
					if(userControllerforUser.userModel.isUserListEmpty()) {				
						System.out.println("���Ե� ����ڰ� �����ϴ�. ȸ�������� �Ͻñ� �ٶ��ϴ�.");
						System.out.println("");
					}
					else {
						userControllerforUser.login();
						userId = userControllerforUser.getUserId();
						start = false;
					}
					break;
			case 3:	
					adminController.login();
					isAdmin = adminController.getIsAdmin();
					start = false;
					break;
			default:
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("");

			}
		}	
		secondPage();
	}
	
	private void secondPage() {		
		if(isAdmin == true) {
			while(true) {
				System.out.println("");
				view.printSecondPageforAdmin();

				int choice = 0;
				//choice = scnr.nextInt();
				while(true) {
					try{
						choice = scnr.nextInt();
						if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 0) {
							System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���. >> ");
							choice = scnr.nextInt();
						}
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���. >> ");
					}
				}
				String returnOrExit;

				switch(choice) {
				case 1:
						bookControllerforAdmin.searchBook();
						break;
				case 2:
						bookControllerforAdmin.deleteBook();
						break;
				case 3: 
						userControllerforAdmin.totalUserList();
						break;
				case 4:
						userControllerforAdmin.convertUserState();
						break;
				case 5: 
						userControllerforAdmin.withdrawUser();
						break;
				case 0: 
						System.out.println("���α׷��� ����˴ϴ�");
						System.exit(0);
				default: 
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						choice = scnr.nextInt();
				}
				
				System.out.print("ó������ ���ư����� f�� �Է�, ���α׷��� �����Ϸ��� e�� �Է��ϼ���. >> ");
				returnOrExit = scnr.next();
				while((returnOrExit.equals("f") || returnOrExit.equals("e")) == false) {
					System.out.print("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.[ó������: f, ����: e] >> ");
					returnOrExit = scnr.next();
				}
				if (returnOrExit.equals("e")){
					adminController.logout();
				}
			}				
		}
		
		else {
			while(true) {
				System.out.println("");
				view.printSecondPageforUser();
				int choice = 0;
				//choice = scnr.nextInt();
				while(true) {
					try{
						choice = scnr.nextInt();
						if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 0) {
							System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���. >> ");
							choice = scnr.nextInt();
						}
						break;
					}
					catch(InputMismatchException e) {
						scnr = new Scanner(System.in);
						System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���. >> ");
					}
				}
				String returnOrExit;
				switch(choice) {
				case 1:
						bookControllerforUser.searchBook();
						break;
				case 2:
						bookControllerforUser.buyBook(userId);
						break;
				case 3: 
						bookControllerforUser.registerBook(userId);
						break;
				case 4:
						bookControllerforUser.deleteReviseBookforUser(userId);
						break;
				case 0:
						System.out.println("���α׷��� ����˴ϴ�");
						System.exit(0);
				default: 
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
				System.out.println("");
				System.out.print("ó������ ���ư����� f�� �Է�, ���α׷��� �����Ϸ��� e�� �Է��ϼ���. >> ");
				returnOrExit = scnr.next();
				System.out.println("");
				System.out.println("");
				while((returnOrExit.equals("f") || returnOrExit.equals("e")) == false) {
					System.out.print("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.[ó������: f, ����: e] >> ");
					returnOrExit = scnr.next();
				}
				if (returnOrExit.equals("e")){
					userControllerforUser.logout();
				}
			}
		}	
	}

	
}
