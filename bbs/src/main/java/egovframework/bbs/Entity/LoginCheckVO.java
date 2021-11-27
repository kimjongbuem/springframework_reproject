package egovframework.bbs.Entity;

public class LoginCheckVO {
	
	private String userEmail;
	private String userPassword;
	

	public LoginCheckVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LoginCheckVO(String id, String password) {
		this.userEmail = id;
		this.userPassword = password;
	}

}
