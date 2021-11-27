package egovframework.bbs.Entity;

public class User {
	private String userEmail;
	private String userPassword;
	private String userName;
	private boolean userGender; // 여 0 남 1
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public boolean isUserGender() {
		return userGender;
	}
	public void setUserGender(boolean userGender) {
		this.userGender = userGender;
	}
}
