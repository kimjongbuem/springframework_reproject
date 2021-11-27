package egovframework.bbs.web;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import egovframework.bbs.Entity.LoginCheckVO;
import egovframework.bbs.Entity.User;
import egovframework.bbs.designPattern.BBSBuilder;
import egovframework.bbs.designPattern.UserBuilder;
import egovframework.bbs.web.service.BBS_Service;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class BBSController {

	@Resource
	private BBS_Service bbsService;

	//로그인 관련 함수 //
	@RequestMapping(value = "/loginFirst.do")
	public String BBSLoginInit() throws Exception {
		return "login/login.tiles";
	}
	
	@RequestMapping(value = "/loginCheck.do")
	public String BBSLoginCheck(HttpServletRequest req,
			@RequestParam(required=false) Map<String, String> paramMap) throws Exception {
		
		String emailId = paramMap.get("emailId");
		String password = paramMap.get("password");
		
		LoginCheckVO loginCheckVO = new LoginCheckVO();
		
		loginCheckVO.setUserEmail(emailId); loginCheckVO.setUserPassword(password);
		
		EgovMap info = null;
		
		try{
			info = bbsService.selectUser(loginCheckVO);
		}catch(Exception e) {
			System.out.println("db err");
			e.printStackTrace();
		}

		if(info != null) {
			
			req.getSession().setAttribute("emailId", emailId);
			
			return "login/main.tiles";
		}
			
		else
			return "login/no.tiles";
	}

	@RequestMapping(value = "/signUp.do")
	public String signUpInit() {
		return "login/signUp.tiles";
	}

	@RequestMapping(value = "/signUpCheck.do")
	public String signUpCheck(HttpServletRequest req,
							  @RequestParam(required=true)
							  Map<String, String> paramMap) throws Exception {
		
		boolean isMale = true;
		
		if(paramMap.get("gender").equals("female")) isMale = false;
		
		User userInfo = new UserBuilder().builder().
				userEmail(paramMap.get("emailId")).userName(paramMap.get("userName")).
				userPassword(paramMap.get("password")).userGender(isMale).build();
		
		EgovMap data = bbsService.selectSameID(paramMap.get("emailId"));
		
		if(data != null) return "login/noSignUp.tiles";
		
		HttpSession session = req.getSession();
		
		session.setAttribute("emailId", paramMap.get("emailId"));
		
		bbsService.insertUser(userInfo);
			
		return "login/main.tiles";
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		System.out.println(session);
		
		session.invalidate();
		
		return "login/login.tiles";
	}
	
	@RequestMapping(value = "/main.do")
	public String mainInit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute("emailId");
		if(emailId == null) return "login/gobackLogin.tiles";
		else return "login/main.tiles";
	}
	
	// 게시판 목록 작성, 세부 게시판 보기 관련 함수 //
	
	@RequestMapping(value = "/bbs.do")
	public String bbsInit(HttpServletRequest req, Model model) throws Exception{
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute("emailId");
		if(emailId == null) return "login/gobackLogin.tiles";
		
		int pageNumber = 0;

		if(req.getParameter("pageNumber")==null) {}
		else {
			pageNumber = Integer.parseInt(req.getParameter("pageNumber"));	
		}
		
		List<EgovMap> bbsList = bbsService.selectPageNumberOfBBSList(pageNumber);
		for(int i = 0; i < bbsList.size();i++) {
			EgovMap map = bbsList.get(i);
			try {
				
				String s = String.valueOf(map.get(map.get(1)));
				map.setValue(1, s);
				s = String.valueOf(map.get(map.get(2)));
				map.setValue(2, s);
				s = String.valueOf(map.get(map.get(3)));
				map.setValue(3, s);
				s = String.valueOf(map.get(map.get(6)));
				map.setValue(6, s);
				
				
//				byte[] byteArray = (byte[]) map.get(map.get(1));
//				map.setValue(1, byteArray);
//				byteArray 		 = (byte[]) map.get(map.get(2));
//				map.setValue(2, byteArray);
//				byteArray 		 = (byte[]) map.get(map.get(3));
//				map.setValue(3, byteArray);
//				byteArray 		 = (byte[]) map.get(map.get(6));
//				map.setValue(6, byteArray);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		
		model.addAttribute("bbsInfoList", bbsList );
		req.setAttribute("pageNumber", pageNumber);
		
		
		List<EgovMap> nextBBSList = bbsService.selectPageNumberOfBBSList(pageNumber + 1);
		
		if(nextBBSList.isEmpty()) {
			req.setAttribute("isRight", 0);
		}
		else req.setAttribute("isRight", 1);
		
		return "login/bbs.tiles";
	}
	
	@RequestMapping(value = "/write.do")
	public String writeInit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute("emailId");
		if(emailId == null) return "login/gobackLogin.tiles";
		else return "login/write.tiles";
	}
	
	@RequestMapping(value = "/writeAction.do")
	public String writeAction(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute("emailId");
		if(emailId == null) return "login/gobackLogin.tiles";
		
		EgovMap bbsID = bbsService.selectBBSID();
		int primaryKey = 1;
		String bbsTitle = req.getParameter("bbsTitle").replaceAll("&nbsp;", " ").
				replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		String bbsContent = req.getParameter("bbsContent").
				replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		
		String isLocked = req.getParameter("lockPassword").
				replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		
		System.out.println(isLocked);
		
		if(bbsID == null) {
			if(isLocked.equals(""))
			bbsService.insertBBS(new BBSBuilder().builder().bbsID(primaryKey).userEmail(emailId).bbsTitle(bbsTitle).
					bbsContent(bbsContent).lockPassword("").isLocked(0).build());
			
			else bbsService.insertBBS(new BBSBuilder().builder().bbsID(primaryKey).userEmail(emailId).bbsTitle(bbsTitle).
					bbsContent(bbsContent).lockPassword(isLocked).isLocked(1).build());
		}
		else {
			primaryKey = (Integer)bbsID.get("bbsID") + 1;
			if(isLocked.equals("")) bbsService.insertBBS(new BBSBuilder().builder().bbsID(primaryKey).userEmail(emailId).
					bbsTitle(bbsTitle).bbsContent(bbsContent).lockPassword("").isLocked(0).build());
			
			else bbsService.insertBBS(new BBSBuilder().builder().bbsID(primaryKey).userEmail(emailId).bbsTitle(bbsTitle).
						bbsContent(bbsContent).lockPassword(isLocked).isLocked(1).build());
		}
		return "login/writeAction.tiles";
	}
	
	@RequestMapping(value = "/arrowAction.do")
	public String arrowAction(HttpServletRequest req,
			Model model ) throws Exception {
		int chkRight= Integer.parseInt(req.getParameter("isRight"));
		int pageNumber= Integer.parseInt(req.getParameter("pageNumber"));
		
		if(chkRight == 1) pageNumber++;
		else pageNumber--;
		
		List<EgovMap> nextBBSList = bbsService.selectPageNumberOfBBSList(pageNumber + 1);
		
		
		if(nextBBSList.isEmpty()) {
			req.setAttribute("isRight", 0);
		}
		else req.setAttribute("isRight", 1);
		
		req.setAttribute("pageNumber", pageNumber);
		
		List<EgovMap> bbsList = bbsService.selectPageNumberOfBBSList(pageNumber);
		
		model.addAttribute("bbsInfoList", bbsList );
		
		return "login/arrowStepping.tiles";
	}

	@RequestMapping(value = "/clickBBSAction.do")
	public String clickBBSAction(HttpServletRequest req, Model model) throws Exception {
		int idNumber= Integer.parseInt(req.getParameter("bbsID"));
		
		EgovMap map = bbsService.selectBBS(idNumber); 
		
		String s = String.valueOf(map.get(map.get(1)));
		map.setValue(1, s);
		s = String.valueOf(map.get(map.get(2)));
		map.setValue(2, s);
		s = String.valueOf(map.get(map.get(3)));
		map.setValue(3, s);
		s = String.valueOf(map.get(map.get(4)));
		map.setValue(4, s);
		s = String.valueOf(map.get(map.get(6)));
		map.setValue(6, s);
		s = String.valueOf(map.get(map.get(7)));
		map.setValue(7, s);
//		
//		byte[] byteArray = (byte[]) map.get(map.get(1));
//		map.setValue(1, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(2));
//		map.setValue(2, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(3));
//		map.setValue(3, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(4));
//		map.setValue(4, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(7));
//		map.setValue(7, new String(byteArray));
		
		model.addAttribute("bbsInfo",map);

//		//try {
//			String userEmail = (String)req.getSession(). getAttribute("emailId");
//			EgovMap clickLikeAndUnLike = bbsService.selectCheckAndisLike(
//					new BBSBuilder().builder().bbsID(idNumber).userEmail(userEmail).build());
//			model.addAttribute("checkLikeInfo",clickLikeAndUnLike);
//		System.out.println(clickLikeAndUnLike);
//		
		
		return "login/inner_bbs.tiles";
	}
	
	//게시판 재작성 내용들 함수 //
	@RequestMapping(value = "/reWrite.do")
	public String reWrite(HttpServletRequest req, Model model) throws Exception {
		
		int idNumber= Integer.parseInt(req.getParameter("bbsID"));
		
		EgovMap map = bbsService.selectBBS(idNumber);
		
		String s = String.valueOf(map.get(map.get(1)));
		map.setValue(1, s);
		s = String.valueOf(map.get(map.get(2)));
		map.setValue(2, s);
		s = String.valueOf(map.get(map.get(3)));
		map.setValue(3, s);
		s = String.valueOf(map.get(map.get(4)));
		map.setValue(4, s);
		s = String.valueOf(map.get(map.get(7)));
		map.setValue(7, s);
		
//		byte[] byteArray = (byte[]) map.get(map.get(1));
//		map.setValue(1, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(2));
//		map.setValue(2, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(3));
//		map.setValue(3, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(4));
//		map.setValue(4, new String(byteArray));
//		byteArray 		 = (byte[]) map.get(map.get(7));
//		map.setValue(7, new String(byteArray));
		
		model.addAttribute("bbsInfo",map);
		
		return "login/reWrite.tiles";
	}
	
	@RequestMapping(value = "/reWriteAction.do")
	public String reWriteAction(HttpServletRequest req, Model model) throws Exception {
		
		int bbsID = Integer.parseInt(req.getParameter("bbsID"));
		
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute("emailId");
		
		String bbsTitle = req.getParameter("bbsTitle").replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		String bbsContent = req.getParameter("bbsContent").replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		String isLocked = req.getParameter("lockPassword").
				replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"").
				replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("<br>", "\n");
		if(isLocked.equals(""))
		bbsService.updateBBS(new BBSBuilder().builder().bbsID(bbsID).userEmail(emailId).bbsTitle(bbsTitle).
				bbsContent(bbsContent).lockPassword("").isLocked(0).build());
		
		else bbsService.updateBBS(new BBSBuilder().builder().bbsID(bbsID).userEmail(emailId).bbsTitle(bbsTitle).
				bbsContent(bbsContent).lockPassword(isLocked).isLocked(1).build());
			
		return "stepping/stepBBS.tiles";
	}
	
	@RequestMapping(value = "/deleteBtn.do")
	public String deleteBtn(HttpServletRequest req, Model model) throws Exception {
		int idNumber= Integer.parseInt(req.getParameter("bbsID"));
		
		bbsService.deleteBBS(idNumber); // sql 제작해야함.
		
		return "stepping/stepBBS.tiles";
	}
	
	//AJAX 
	@RequestMapping(value = "/rankAjax.do", produces ="application/json; charset=utf-8")
	@ResponseBody
	public String chartAjaxInit(@RequestParam(required = true) String rankName,
			HttpServletResponse res) throws Exception {
		List<EgovMap> rankBBSList = null;
		if(rankName.equals("many")) rankBBSList = bbsService.selectRankManyWriteBBSList();
		else if(rankName.equals("like")) rankBBSList = bbsService.selectRankLikeBBSList();
		else if(rankName.equals("saw")) {} //구현해야함
		
		for(int i = 0; i < rankBBSList.size();i++) {
			EgovMap map = rankBBSList.get(i);

			String s = String.valueOf(map.get(map.get(1)));
			
			System.out.println(s);
			
//			int size = Integer.SIZE / 8;
//			ByteBuffer buff = ByteBuffer.allocate(size);
//			byte[] newByte = new byte[size];
//			for(int j = 0; j < size; j++) {
//				if(j + s.length() < size) {
//					newByte[j] = (byte) 0x00;
//				}else {
//					newByte[j] = 
//						byteArray[j + byteArray.length - size];
//				}
//			}
//
//			buff = ByteBuffer.wrap(newByte);
//			buff.order(ByteOrder.BIG_ENDIAN);
//			Integer data = 10;
//			if(i < 9) data = buff.getInt() - 48; // ? 10개되면 어떻게 될까???
//			map.setValue(0,  data);
//			
//			byteArray = (byte[]) map.get(map.get(1));
//				
//			map.setValue(1, new String(byteArray));
		}

		Gson gson = new Gson();
		
		return gson.toJson(rankBBSList);
	}
	
	@RequestMapping(value = "/likeAndUnlike.do", produces ="application/json; charset=utf-8")
	@ResponseBody
	public String likeAndUnlikeInit(@RequestParam(required = true) String bbsNum,
			String like, String id, HttpServletResponse res) throws Exception {
		
		int bbsID = Integer.parseInt(bbsNum);
		int isLike  = Integer.parseInt(like);
		String userEmail = id;

		Gson gson = new Gson();
		EgovMap returnMap = new EgovMap();
		
		
		EgovMap isExisted = bbsService.selectCheckLikeOrUnlike(
					new BBSBuilder().builder().bbsID(bbsID).userEmail(userEmail).build());
		
		if(isExisted == null) {
			bbsService.insertCheck(new BBSBuilder().builder().bbsID(bbsID).userEmail(userEmail).build());
			if(isLike == 1) {
				bbsService.insertLikeAndUnlike(new BBSBuilder().builder().
						bbsID(bbsID).userEmail(userEmail).isLiked(1).build());
				bbsService.updateLike(new BBSBuilder().builder().
						bbsID(bbsID).isLiked(1).build());
			}
			else {
				bbsService.insertLikeAndUnlike(new BBSBuilder().builder().
						bbsID(bbsID).userEmail(userEmail).isLiked(0).build());
				bbsService.updateUnlike(new BBSBuilder().builder().
						bbsID(bbsID).isLiked(1).build());
			}

			returnMap.put("data", "");
			return gson.toJson(returnMap);
			
		}else {
			
			if(Integer.parseInt(String.valueOf(isExisted.get(isExisted.get(2)))) == -1) { // check x => val : -1
				
				bbsService.updateCheck(new BBSBuilder().builder().bbsID(bbsID).
						userEmail(userEmail).isChecked(1).build());
				if(isLike == 1) {
					bbsService.updateLikeAndUnlike(new BBSBuilder().builder().
							bbsID(bbsID).userEmail(userEmail).isLiked(1).build());
					bbsService.updateLike(new BBSBuilder().builder().
							bbsID(bbsID).userEmail(userEmail).isLiked(1).build());
				}
						
				else {
					bbsService.updateLikeAndUnlike(
							new BBSBuilder().builder().bbsID(bbsID).userEmail(userEmail).isLiked(0).build());
					bbsService.updateUnlike(new BBSBuilder().builder().bbsID(bbsID).
							isLiked(1).build());
				}
				returnMap.put("data", "");
				return gson.toJson(returnMap);	
			}else { // check o

				bbsService.updateCheck(new BBSBuilder().builder().bbsID(bbsID).
						userEmail(userEmail).isChecked(0).build());
				
				EgovMap map = bbsService.selectCheckLikeOrUnlike(
					new BBSBuilder().builder().bbsID(bbsID).userEmail(userEmail).build());
				
				int chk = (int)map.get(map.get(2));
				
				if(chk != isLike) {
					returnMap.put("change", 2);
					return gson.toJson(returnMap);	
				}
				else if(chk == 1) { // 
					bbsService.updateLike(new BBSBuilder().builder().
								bbsID(bbsID).userEmail(userEmail).isLiked(-1).build());
					returnMap.put("change", 1);
				}else if(chk == 0){
						bbsService.updateUnlike(new BBSBuilder().builder().bbsID(bbsID).
								isLiked(-1).build());
						returnMap.put("change", 0);
				}
				// -1를 넣어주어서 선택을 안했다 1은 좋아요, 0은 싫어요 선택한거!
				bbsService.updateLikeAndUnlike(new BBSBuilder().builder().bbsID(bbsID).
						userEmail(userEmail).isLiked(-1).build());
				return gson.toJson(returnMap);	
			}
		}
	}
}
