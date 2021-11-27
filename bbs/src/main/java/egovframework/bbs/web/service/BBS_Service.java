package egovframework.bbs.web.service;

import java.util.List;

import egovframework.bbs.Entity.BBS;
import egovframework.bbs.Entity.LoginCheckVO;
import egovframework.bbs.Entity.User;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface BBS_Service {
	
	EgovMap selectUser(LoginCheckVO loginCheck) throws Exception;

	List<EgovMap> selectUserList() throws Exception;

	EgovMap selectSameID(String id) throws Exception;

	EgovMap selectBBSID() throws Exception;

	void insertBBS(BBS bbs) throws Exception;

	void insertUser(User user) throws Exception;

	List<EgovMap> selectBBSList() throws Exception;

	List<EgovMap> selectPageNumberOfBBSList(Integer pageNumber) throws Exception;

	EgovMap selectBBS(int idNumber) throws Exception;

	void deleteBBS(int idNumber)  throws Exception;

	void updateBBS(BBS build) throws Exception;

	List<EgovMap> selectRankManyWriteBBSList() throws Exception;

	EgovMap selectCheckLikeOrUnlike(BBS bbs) throws Exception;

	void insertCheck(BBS bbs)throws Exception;

	void insertLikeAndUnlike(BBS bbs)throws Exception;

	void updateCheck(BBS build)throws Exception;

	void updateLikeAndUnlike(BBS bbs) throws Exception;

	EgovMap selectLikeAndUnlike(BBS bbs) throws Exception;

	void updateLike(BBS bbs)throws Exception;

	void updateUnlike(BBS bbs)throws Exception;

	EgovMap selectCheckAndisLike(BBS bbs) throws Exception;

	List<EgovMap> selectRankLikeBBSList() throws Exception;

	List<EgovMap> selectCountry()throws Exception;
	
}
