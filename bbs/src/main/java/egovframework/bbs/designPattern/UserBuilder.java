package egovframework.bbs.designPattern;

import egovframework.bbs.Entity.User;

public class UserBuilder implements Builder {
	
	private User user;

	@Override
	public UserBuilder builder() {
		user = new User();
		return this;
	}
	public UserBuilder userEmail(String userEmail) {
		user.setUserEmail(userEmail);
		return this;
	}
	public UserBuilder userPassword(String userPassword) {
		user.setUserPassword(userPassword);
		return this;
	}
	public UserBuilder userName(String userName) {
		user.setUserName(userName);
		return this;
	}
	public UserBuilder userGender(boolean userGender) {
		user.setUserGender(userGender);
		return this;
	}
	public User build() {
		return user;
	}
	
}
