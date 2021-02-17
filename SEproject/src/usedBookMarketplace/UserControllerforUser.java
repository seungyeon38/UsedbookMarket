package usedBookMarketplace;

import java.util.Scanner;

public class UserControllerforUser extends UserController{
	Scanner scnr = new Scanner(System.in);
	
	public UserControllerforUser() {}

	//로그인 후 id저장을 위해서 필요 
	private String userId;
	public String getUserId() { return userId; }
		
	//회원가입
	public void signUp()
	{	
		User user = new User();
		String name;
		String id;
		String pw;
		String phoneNum;
		String email;
		
		System.out.println("회원가입>>");
		System.out.println("정보를 입력해주세요.");
		System.out.print("이름: "); 
		name = scnr.nextLine(); 
		System.out.print("ID: ");
		id = scnr.nextLine();
		//동일한 id가 존재시 
		while(userModel.isThereSearchedUser(id)) {
			System.out.println("동일한 ID가 존재합니다. 다른 ID를 입력해주세요.");
			System.out.print("ID: ");
			id = scnr.nextLine();
		}
		//'0'은 기능 종료시에 쓰이므로
		while(id.equals("0")) {
			System.out.println("다른 ID를 입력해주세요.");
			System.out.print("ID: ");
			id = scnr.nextLine();
		}
		System.out.print("비밀번호: ");
		pw = scnr.nextLine();
		System.out.print("전화번호: ");
		phoneNum = scnr.nextLine();
		System.out.print("이메일: ");
		email = scnr.nextLine();
		
		//정보를 다 입력하지 않았을 때
		while((name.isEmpty() || id.isEmpty() || pw.isEmpty() || phoneNum.isEmpty() || email.isEmpty()) == true) {
			System.out.println("정보가 다 입력되지 않았습니다. 다시 입력해주세요.");
			
			System.out.println("정보를 입력하세요.");
			System.out.print("이름: ");
			name = scnr.nextLine();
			System.out.print("ID: "); 
			id = scnr.nextLine();
			System.out.print("비밀번호: ");
			pw = scnr.nextLine();
			System.out.print("전화번호: ");
			phoneNum = scnr.nextLine();
			System.out.print("이메일: ");
			email = scnr.nextLine();
		}
		
		user.setName(name);
		user.setId(id);
		user.setPassword(pw);
		user.setPhoneNum(phoneNum);
		user.setEmail(email);
		
		userModel.addUser(user);
		
		System.out.println("가입되셨습니다.");
		System.out.println("");
	}

	//사용자 로그인 
	public void login(){	
		String id;
		String pw;
		int count = 3;
		System.out.printf("로그인을 진행합니다.(로그인 제한횟수: %d)\n", count);
		System.out.print("아이디: "); 
		id = scnr.nextLine();
		System.out.print("비밀번호: ");
		pw = scnr.nextLine();
		System.out.println("");	
		count--;
		while((userModel.isThereUserWthIdPw(id,pw) == false) && count > 0) {
			System.out.printf("아이디나 비밀번호가 틀렸습니다. 다시입력해주세요.(로그인 제한횟수: %d)\n", count);
			System.out.print("아이디: ");
			id = scnr.nextLine();
			System.out.print("비밀번호: ");
			pw = scnr.nextLine();
			System.out.println("");	
			count--;
		}
		//사용자가 비활성화 상태일 때 
		if(userModel.isUserDeactivated(id)) {
			System.out.println("비활성화 상태의 사용자입니다. 로그인할 수 없습니다.");
			System.out.println("프로그램이 종료됩니다.");
			System.exit(0); //프로그램 종료 
		}
		//로그인을 3번이상 틀렸을 때 
		else if((userModel.isThereUserWthIdPw(id,pw) == false) && count == 0) {
			System.out.println("로그인을 3번이상 틀리셨으므로 프로그램이 종료됩니다.");
			System.exit(0); //프로그램 종료
		}		
		//로그인 성공
		else {
			System.out.println("로그인되었습니다.");
			userId = id;	
		}
	}

	//사용자 로그아웃(프로그램 종료)
	public void logout() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
	}
}
