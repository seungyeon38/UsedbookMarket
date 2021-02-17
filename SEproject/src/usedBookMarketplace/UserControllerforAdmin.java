package usedBookMarketplace;

import java.util.Scanner;

public class UserControllerforAdmin extends UserController{
	Scanner scnr = new Scanner(System.in);
	
	public UserControllerforAdmin() {}
	
	//전체 사용자 목록보기 
	public void totalUserList(){
		userModel.setTotalUserList();
		//등록되어있는 사용자가 없을 때 
		if(userModel.isUserListEmpty()) {
			System.out.println("");
			System.out.println("가입한 사용자가 없습니다.");
			System.out.println("");
		}
		else {
			view.printUserList(userModel.totalUserList, "total");
		}
	}

	//비활성화 상태의 사용자 목록보기  
	public void deactivatedUserList() {	
		userModel.setDeactivatedUserList();
		view.printUserList(userModel.deactivatedUserList, "deactivated");
	}

	//사용자 상태 전환하기
	public void convertUserState() {
		String continue_convertState = "Y";
		String userId_toConvertState;
		System.out.println("");
		System.out.println("사용자 상태 전환>>");
		System.out.println("");
	loop:
		//사용자 상태전환을 계속 하고자하는 동안 
		while(continue_convertState.equals("Y")) {
			boolean userWthId = false;
			if(userModel.isUserListEmpty()) {
				System.out.println("등록되어있는 사용자가 없습니다.");
				System.out.println("");
				break;
			}
			totalUserList();

			System.out.print("상태를 전환할 사용자의 아이디를 입력해주세요.(사용자 탈퇴 종료하기 0) >> ");
			userId_toConvertState = scnr.next();
			//콘솔한정
			do {
				if(userId_toConvertState.equals("0") == false) {
					for(User user: userModel.totalUserList) {
						if(user.getId().equals(userId_toConvertState)) {
							userWthId = true;
							break;
						}
					}
					//입력한 아이디를 가진 사용자가 전체 사용자 목록에 없을 때
					if(userWthId == false){
						System.out.print("입력한 아이디의 사용자가 없습니다. 다시 입력해주세요>> ");
						userId_toConvertState = scnr.next();
					}
				}
				//사용자 상태전환시키기 종료
				else 
					break loop;
			}while(userWthId == false);
			
			System.out.print("선택하신 사용자의 상태를 전환하시겠습니까?[Y/N] >> ");
			String confirm_convertState = scnr.next();
			//콘솔한정
			while((confirm_convertState.equals("Y") || confirm_convertState.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				confirm_convertState = scnr.next();	
			}
			//사용자 상태전환
			if(confirm_convertState.equals("Y")) {
				userModel.convertUserState(userId_toConvertState);
				System.out.println("사용자의 상태를 전환하였습니다.");
				System.out.println("");
				totalUserList();
			}
			//사용자 상태전환 취소
			else {
				System.out.println("사용자의 상태 변경이 취소되었습니다.");
				System.out.println("");
			}
			System.out.print("사용자의 상태전환을 계속하시겠습니까?[Y/N] >> ");
			continue_convertState = scnr.next();
			//콘솔한정
			while((continue_convertState.equals("Y") || continue_convertState.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				continue_convertState = scnr.nextLine();	
			}
		}
		System.out.println("사용자 상태전환이 종료되었습니다.");
		System.out.println("");
	}

	//사용자 탈퇴시키기
	public void withdrawUser() {
		String continue_deleteUser = "Y";
		String userId_toDelete;
		System.out.println("");
		System.out.println("사용자 탈퇴>>");
		System.out.println("");
		
	loop:
		//사용자 탈퇴시키기를 계속 하고자하는 동안 
		while(continue_deleteUser.equals("Y")) {
			String userWthId = "F";
			if(userModel.isThereDeactivatedUser() == false) {
				System.out.println("비활성화 상태의 사용자가 존재하지 않습니다.");
				System.out.println("");
				continue_deleteUser = "N";
				break;
			}
				
			deactivatedUserList();

			System.out.print("탈퇴시킬 사용자의 아이디를 입력해주세요.(사용자 탈퇴 종료하기 0) >> ");
			userId_toDelete = scnr.next();
			//콘솔한정
			do {
				if(userId_toDelete.equals("0") == false) {
					for(User user: userModel.deactivatedUserList) {
						if(user.getId().equals(userId_toDelete)) {
							userWthId = "T";
							break;
						}
					}
					//입력한 아이디를 가진 사용자가 비활성화상태의 사용자 목록에 없을 때
					if(userWthId.equals("F")){
						System.out.print("입력한 아이디의 사용자가 없습니다. 다시 입력해주세요>> ");
						userId_toDelete = scnr.next();
					}
				}
				//사용자 탈퇴시키기 종료 
				else 
					break loop;
			}while(userWthId.equals("F"));
			
			System.out.print("선택하신 사용자를 탈퇴시키겠습니까?[Y/N] >> ");
			String confirm_withdraw = scnr.next();
			//콘솔한정
			while((confirm_withdraw.equals("Y") || confirm_withdraw.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> ");
				confirm_withdraw = scnr.next();	
			}
			//사용자 탈퇴
			if(confirm_withdraw.equals("Y")) {
				userModel.deleteUser(userId_toDelete);
				userModel.deleteBookListWthUsrId(userId_toDelete);
				System.out.println("선택하신 사용자가 탈퇴되었습니다.(이 사용자가 등록한 책도 함께 삭제됨)");
				System.out.println("");
			}
			//사용자 탈퇴 취소 
			else {
				System.out.println("사용자 탈퇴가 취소되었습니다.");
				System.out.println("");
			}
			System.out.print("사용자 탈퇴를 계속하시겠습니까?[Y/N] >> ");
			continue_deleteUser = scnr.next();
			//콘솔한정
			while((continue_deleteUser.equals("Y") || continue_deleteUser.equals("N")) == false) {
				System.out.print("잘못입력하셨습니다. 다시 입력해주세요.[Y/N] >> "); 
				continue_deleteUser = scnr.nextLine();	
			}
		}
		System.out.println("사용자 탈퇴가 종료되었습니다."); 
		System.out.println("");
	}

}
	
	