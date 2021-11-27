package egovframework.bbs.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.bbs.Entity.BBS;
import egovframework.bbs.Entity.LoginCheckVO;
import egovframework.bbs.Entity.User;
import egovframework.bbs.web.service.BBS_Service;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service
public class BBS_ServiceImpl implements BBS_Service{

	@Resource
	private BBS_Mapper bbsMapper;

	@Override
	public EgovMap selectUser(LoginCheckVO loginCheck) throws Exception{
		return bbsMapper.selectUser(loginCheck);
	}

	@Override
	public List<EgovMap> selectUserList() throws Exception {
		return bbsMapper.selectUserList();
	}

	@Override
	public void insertUser(User user) throws Exception {
		bbsMapper.insertUser(user);
	}

	@Override
	public EgovMap selectSameID(String id) throws Exception {
		return bbsMapper.selectSameID(id);
	}

	@Override
	public EgovMap selectBBSID() throws Exception {
		return bbsMapper.selectBBSID();
	}

	@Override
	public void insertBBS(BBS bbs) throws Exception {
		bbsMapper.insertBBS(bbs);
		
	}

	@Override
	public List<EgovMap> selectBBSList() throws Exception {
		return bbsMapper.selectBBSList();
	}

	@Override
	public List<EgovMap> selectPageNumberOfBBSList(Integer pageNumber) throws Exception{
		return bbsMapper.selectPageNumberOfBBSList(pageNumber);
	}

	@Override
	public EgovMap selectBBS(int idNumber) throws Exception {
		return bbsMapper.selectBBS(idNumber);
	}

	@Override
	public void deleteBBS(int idNumber) throws Exception {
		bbsMapper.deleteBBS(idNumber);
		
	}

	@Override
	public void updateBBS(BBS build) throws Exception {
		bbsMapper.updateBBS(build);
		
	}

	@Override
	public List<EgovMap> selectRankManyWriteBBSList() throws Exception {
		return bbsMapper.selectRankManyWriteBBSList();
	}

	@Override
	public EgovMap selectCheckLikeOrUnlike(BBS bbs) throws Exception {
		return bbsMapper.selectCheckLikeOrUnlike(bbs);
	}

	@Override
	public void insertCheck(BBS bbs) throws Exception {
		bbsMapper.insertCheck(bbs);
		
	}

	@Override
	public void insertLikeAndUnlike(BBS bbs) throws Exception {
		bbsMapper.insertLikeAndUnlike(bbs);
		
	}

	@Override
	public void updateCheck(BBS bbs) throws Exception {
		bbsMapper.updateCheck(bbs);
		
	}

	@Override
	public void updateLikeAndUnlike(BBS bbs) throws Exception {
		bbsMapper.updateLikeAndUnlike(bbs);
		
	}

	@Override
	public EgovMap selectLikeAndUnlike(BBS bbs) throws Exception {
		return bbsMapper.selectLikeAndUnlike(bbs);
	}

	@Override
	public void updateLike(BBS bbs) throws Exception {
		bbsMapper.updateLike(bbs);
		
	}

	@Override
	public void updateUnlike(BBS bbs) throws Exception {
		bbsMapper.updateUnlike(bbs);
		
	}

	@Override
	public EgovMap selectCheckAndisLike(BBS bbs) throws Exception {
		return bbsMapper.selectCheckAndisLike(bbs);
	}

	@Override
	public List<EgovMap> selectRankLikeBBSList() throws Exception {
		return bbsMapper.selectRankLikeBBSList();
	}

	@Override
	public List<EgovMap> selectCountry() throws Exception {
		// TODO Auto-generated method stub
		return bbsMapper.selectCountry();
	}

}
