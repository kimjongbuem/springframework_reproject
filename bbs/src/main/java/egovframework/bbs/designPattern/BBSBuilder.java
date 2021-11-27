package egovframework.bbs.designPattern;

import egovframework.bbs.Entity.BBS;

public class BBSBuilder implements Builder {
	
	private BBS bbs;

	@Override
	public BBSBuilder builder() {
		bbs = new BBS();
		return this;
	}
	public BBSBuilder bbsID(int bbsID) {
		bbs.setBbsID(bbsID);
		return this;
	}
	public BBSBuilder bbsAvailable(int bbsAvailable) {
		bbs.setBbsAvailable(bbsAvailable);
		return this;
	}
	public BBSBuilder bbsTitle(String bbsTitle) {
		bbs.setBbsTitle(bbsTitle);
		return this;
	}
	public BBSBuilder bbsDate(String bbsDate) {
		bbs.setBbsDate(bbsDate);
		return this;
	}
	public BBSBuilder bbsContent(String bbsContent) {
		bbs.setBbsContent(bbsContent);
		return this;
	}
	public BBSBuilder userEmail(String userEmail) {
		bbs.setUserEmail(userEmail);
		return this;
	}
	public BBSBuilder lockPassword(String lockPassword) {
		bbs.setLockPasswordEmail(lockPassword);
		return this;
	}
	public BBSBuilder isLocked(int isLocked) {
		bbs.setIsLocked(isLocked);
		return this;
	}

	public BBSBuilder isLiked(int isLiked) {
		bbs.setIsLiked(isLiked);;
		return this;
	}
	public BBSBuilder isChecked(int isChecked) {
		bbs.setIsChecked(isChecked);
		return this;
	}
	public BBSBuilder likeNum(int likeNum) {
		bbs.setLikeNum(likeNum);
		return this;
	}
	public BBSBuilder unLikeNum(int unLikeNum) {
		bbs.setUnLikeNum(unLikeNum);
		return this;
	}
	public BBS build() {
		return bbs;
	}
}
