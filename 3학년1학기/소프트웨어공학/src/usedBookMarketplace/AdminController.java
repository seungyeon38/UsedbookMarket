package usedBookMarketplace;

import java.util.Scanner;

public class AdminController {
	Admin admin = new Admin();
	Scanner scnr = new Scanner(System.in);
	
	private boolean isAdmin = false; 
	
	public boolean getIsAdmin() {
		return isAdmin;
	} 
	
	//������ �α��� 
	public void login() {
		String adminId;
		String adminPw;
		int count = 3;
		System.out.printf("�α����� �����մϴ�.(�α��� ����Ƚ��: %d)\n", count);
		System.out.print("���̵�: "); 
		adminId = scnr.nextLine();
		System.out.print("��й�ȣ: ");
		adminPw = scnr.nextLine();
		count--;
		while(((adminId.equals(admin.getAdminID()) && adminPw.equals(admin.getAdminPassword())) == false) && count > 0){
			System.out.println("");
			System.out.printf("���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��Է����ּ���.(�α��� ����Ƚ��: %d)\n", count);
			System.out.print("���̵�: ");
			adminId = scnr.nextLine();
			System.out.print("��й�ȣ: ");
			adminPw = scnr.nextLine();
			count--;
		}
		if(((adminId.equals(admin.getAdminID()) && adminPw.equals(admin.getAdminPassword())) == false) && count == 0) {
			System.out.println("");
			System.out.println("�α����� 3���̻� Ʋ�������Ƿ� ���α׷��� ����˴ϴ�.");
			System.exit(0);
		}
		System.out.println("�α��εǼ̽��ϴ�.");
		isAdmin = true;
	}
	
	//������ �α׾ƿ�(���α׷� ����)
	public void logout() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);
	}
}
