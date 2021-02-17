package usedBookMarketplace;

import java.util.Scanner;

public class UserControllerforUser extends UserController{
	Scanner scnr = new Scanner(System.in);
	
	public UserControllerforUser() {}

	//�α��� �� id������ ���ؼ� �ʿ� 
	private String userId;
	public String getUserId() { return userId; }
		
	//ȸ������
	public void signUp()
	{	
		User user = new User();
		String name;
		String id;
		String pw;
		String phoneNum;
		String email;
		
		System.out.println("ȸ������>>");
		System.out.println("������ �Է����ּ���.");
		System.out.print("�̸�: "); 
		name = scnr.nextLine(); 
		System.out.print("ID: ");
		id = scnr.nextLine();
		//������ id�� ����� 
		while(userModel.isThereSearchedUser(id)) {
			System.out.println("������ ID�� �����մϴ�. �ٸ� ID�� �Է����ּ���.");
			System.out.print("ID: ");
			id = scnr.nextLine();
		}
		//'0'�� ��� ����ÿ� ���̹Ƿ�
		while(id.equals("0")) {
			System.out.println("�ٸ� ID�� �Է����ּ���.");
			System.out.print("ID: ");
			id = scnr.nextLine();
		}
		System.out.print("��й�ȣ: ");
		pw = scnr.nextLine();
		System.out.print("��ȭ��ȣ: ");
		phoneNum = scnr.nextLine();
		System.out.print("�̸���: ");
		email = scnr.nextLine();
		
		//������ �� �Է����� �ʾ��� ��
		while((name.isEmpty() || id.isEmpty() || pw.isEmpty() || phoneNum.isEmpty() || email.isEmpty()) == true) {
			System.out.println("������ �� �Էµ��� �ʾҽ��ϴ�. �ٽ� �Է����ּ���.");
			
			System.out.println("������ �Է��ϼ���.");
			System.out.print("�̸�: ");
			name = scnr.nextLine();
			System.out.print("ID: "); 
			id = scnr.nextLine();
			System.out.print("��й�ȣ: ");
			pw = scnr.nextLine();
			System.out.print("��ȭ��ȣ: ");
			phoneNum = scnr.nextLine();
			System.out.print("�̸���: ");
			email = scnr.nextLine();
		}
		
		user.setName(name);
		user.setId(id);
		user.setPassword(pw);
		user.setPhoneNum(phoneNum);
		user.setEmail(email);
		
		userModel.addUser(user);
		
		System.out.println("���ԵǼ̽��ϴ�.");
		System.out.println("");
	}

	//����� �α��� 
	public void login(){	
		String id;
		String pw;
		int count = 3;
		System.out.printf("�α����� �����մϴ�.(�α��� ����Ƚ��: %d)\n", count);
		System.out.print("���̵�: "); 
		id = scnr.nextLine();
		System.out.print("��й�ȣ: ");
		pw = scnr.nextLine();
		System.out.println("");	
		count--;
		while((userModel.isThereUserWthIdPw(id,pw) == false) && count > 0) {
			System.out.printf("���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��Է����ּ���.(�α��� ����Ƚ��: %d)\n", count);
			System.out.print("���̵�: ");
			id = scnr.nextLine();
			System.out.print("��й�ȣ: ");
			pw = scnr.nextLine();
			System.out.println("");	
			count--;
		}
		//����ڰ� ��Ȱ��ȭ ������ �� 
		if(userModel.isUserDeactivated(id)) {
			System.out.println("��Ȱ��ȭ ������ ������Դϴ�. �α����� �� �����ϴ�.");
			System.out.println("���α׷��� ����˴ϴ�.");
			System.exit(0); //���α׷� ���� 
		}
		//�α����� 3���̻� Ʋ���� �� 
		else if((userModel.isThereUserWthIdPw(id,pw) == false) && count == 0) {
			System.out.println("�α����� 3���̻� Ʋ�������Ƿ� ���α׷��� ����˴ϴ�.");
			System.exit(0); //���α׷� ����
		}		
		//�α��� ����
		else {
			System.out.println("�α��εǾ����ϴ�.");
			userId = id;	
		}
	}

	//����� �α׾ƿ�(���α׷� ����)
	public void logout() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);
	}
}
