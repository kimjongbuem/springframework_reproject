package egovframework.bbs.Entity;

public class BBS {
	
	private int bbsID;
	private String bbsTitle;
	private String lockPassword;
	private String userEmail;
	private String bbsDate; // db Now()
	private String bbsContent;
	private int bbsAvailable; // 추가시 1 삭제시 0으로 바로.
	private int isChecked;
	private int isLocked;
	private int isLiked;
	private int likeNum;
	private int unLikeNum;
	
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getUnLikeNum() {
		return unLikeNum;
	}
	public void setUnLikeNum(int unLikeNum) {
		this.unLikeNum = unLikeNum;
	}	
	public String getLockPassword() {
		return lockPassword;
	}
	public void setLockPassword(String lockPassword) {
		this.lockPassword = lockPassword;
	}
	public int getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}

	public int getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}
	public int getBbsID() {
		return bbsID;
	}
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getLockPasswordEmail() {
		return this.lockPassword;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public int getBbsAvailable() {
		return bbsAvailable;
	}
	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	public void setLockPasswordEmail(String lockPassword) {
		this.lockPassword = lockPassword;
	}
	public int getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}
}
