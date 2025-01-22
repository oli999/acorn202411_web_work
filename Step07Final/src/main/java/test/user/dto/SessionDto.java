package test.user.dto;

//session 에 담을 내용
public class SessionDto {
	private long num;
	private String userName;
	private String role;
	
	public SessionDto() {}

	public SessionDto(long num, String userName, String role) {
		super();
		this.num = num;
		this.userName = userName;
		this.role = role;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
