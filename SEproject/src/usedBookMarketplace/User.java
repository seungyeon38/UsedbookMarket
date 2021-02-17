package usedBookMarketplace;

public class User {
	//user의 정보 : 이름, id, 비밀번호, 전화번호, 이메일, 상태(activated, deactivated)
	private String name; 
	private String id;
	private String pw;
	private String phoneNum;
	private String email;
	private String state = "activated";
	
	public User(){}
	
	public User(String name, String id, String pw, String phoneNum,	String email) 
	{
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	//getter
	public String getName() {return name;}
	public String getId() {return id;}
	public String getPassword() {return pw;}
	public String getPhoneNum() {return phoneNum;}
	public String getEmail() {return email;}
	public String getState() {return state;}
	
	//setter 
	public void setName(String name) {this.name = name;}
	public void setId(String id) {this.id = id;}
	public void setPassword(String pw) {this.pw = pw;}
	public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}
	public void setEmail(String email) {this.email = email;}
	public void setState(String state) {this.state = state;}
}

