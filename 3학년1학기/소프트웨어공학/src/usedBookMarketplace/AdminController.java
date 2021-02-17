package usedBookMarketplace;

import java.util.Scanner;

public class AdminController {
	Admin admin = new Admin();
	Scanner scnr = new Scanner(System.in);
	
	private boolean isAdmin = false; 
	
	public boolean getIsAdmin() {
		return isAdmin;
	} 
	
	//관리자 로그인 
	public void login() {
		String adminId;
		String adminPw;
		int count = 3;
		System.out.printf("로그인을 진행합니다.(로그인 제한횟수: %d)\n", count);
		System.out.print("아이디: "); 
		adminId = scnr.nextLine();
		System.out.print("비밀번호: ");
		adminPw = scnr.nextLine();
		count--;
		while(((adminId.equals(admin.getAdminID()) && adminPw.equals(admin.getAdminPassword())) == false) && count > 0){
			System.out.println("");
			System.out.printf("아이디나 비밀번호가 틀렸습니다. 다시입력해주세요.(로그인 제한횟수: %d)\n", count);
			System.out.print("아이디: ");
			adminId = scnr.nextLine();
			System.out.print("비밀번호: ");
			adminPw = scnr.nextLine();
			count--;
		}
		if(((adminId.equals(admin.getAdminID()) && adminPw.equals(admin.getAdminPassword())) == false) && count == 0) {
			System.out.println("");
			System.out.println("로그인을 3번이상 틀리셨으므로 프로그램이 종료됩니다.");
			System.exit(0);
		}
		System.out.println("로그인되셨습니다.");
		isAdmin = true;
	}
	
	//관리자 로그아웃(프로그램 종료)
	public void logout() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
	}
}
