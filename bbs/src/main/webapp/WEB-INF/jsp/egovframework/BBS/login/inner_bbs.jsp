<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	//애니메이션 사용하기 위한 객체와 메서드
	var ClickAnimateAjax = {
		animateLikeImg : function(unClick){
			if(unClick == 0){
				$("#likeImg").css('position','relative').animate( {
					 top : "-=15"
			          }, 500, function() {
			            $( this ).animate( {
			            	top : "+=15"
			            }, 500 );
			     });
				setTimeout(function() {
					 $("#likeImg").attr("src", "images/egovframework/bbs/clickLike.png");
				 }, 1000);
			}else{
				$("#likeImg").css('position','relative').animate( {
					 top : "-=15"
			          }, 500, function() {
			            $( this ).animate( {
			            	top : "+=15"
			            }, 500 );
			     });
				setTimeout(function() {
					 $("#likeImg").attr("src", "images/egovframework/bbs/like.png");
				 }, 1000);
			}
		},
		animateUnLikeImg : function(unClick){
			if(unClick == 0){
				$("#unLikeImg").css('position','relative').animate( {
					 top : "+=15"
			          }, 500, function() {
			            $( this ).animate( {
			            	top : "-=15"
			            }, 500 );
			     });
				setTimeout(function() {
					 $("#unLikeImg").attr("src", "images/egovframework/bbs/clickUnlike.png");
				 }, 1000);
			}else{
				$("#unLikeImg").css('position','relative').animate( {
					 top : "+=15"
			          }, 500, function() {
			            $( this ).animate( {
			            	top : "-=15"
			            }, 500 );
			     });
				setTimeout(function() {
					 $("#unLikeImg").attr("src", "images/egovframework/bbs/unlike.png");
				 }, 1000);
			}
		}
	}
	
	// ajax 이용해서 데이터 확인용 함수
	function checkData(data, isLike, that) {
		var bbsID = "${bbsInfo.bbsID}";
		var userEmail = "${bbsInfo.userEmail}";
		var likeNum = Number($("#likeNum").text());
		var unLikeNum = Number($("#unLikeNum").text());
		
		if(data.data == ""){
			if(isLike == 1) {
				that.animateLikeImg(0);
				$("#likeNum").text(likeNum + 1); 
			}
			else {
				that.animateUnLikeImg(0);
				$("#unLikeNum").text(unLikeNum + 1);
			}
		}
		else{
			if(data.change == 1){
				that.animateLikeImg(1);
				$("#likeNum").text(likeNum - 1); 
			}else if(data.change == 0){
				that.animateUnLikeImg(1);
				$("#unLikeNum").text(unLikeNum - 1);
			}else if(data.change == 2){
				alert("클릭 하셨던 좋아요 혹은 싫어요를\n 다시 클릭해 주시고 시도해주세요!");
			}
		}
		
		
	}

	// 버튼 객체, 메서드 구현
	var ClickBtn ={
			clickBackBtn : function(){
				window.history.back();
			},
			clickdeleteBtn : function(bbsID){
				var result = confirm("정말로 삭제 하시겠습니까?");
				if(result) location.href = "/deleteBtn.do?bbsID=" + bbsID;
			},
			clickReviseBtn :  function(bbsID){
				 location.href = "/reWrite.do?bbsID=" + bbsID;
			},
			clickLikeAndUnlikeBtn : function(bbsID, isLike){
				var that = this;
				var userEmail = "${bbsInfo.userEmail}";
				var session ="";
				session = "<%= (String) session.getAttribute("emailId")%>";
				
				if(userEmail == session) {
					alert("내가 쓴 글에는 좋아요 또는 싫어요를 표시할 수 없습니다!");
					return;
				}
				
				$.ajax({
					url : "/likeAndUnlike.do",
					data : {
						bbsNum : bbsID,
						like : isLike,
						id : session
					},
					success : function(data) {
						checkData(data, isLike,that);
					}
				});
			}
	}
	// 해당 게시판의 세부부분 할당과 체크 //
	function checkBBS() {
		var isAvailable = <c:out value = "${bbsInfo.bbsAvailable}"></c:out>;
		
		if(isAvailable == 0) {
			alert("삭제된 글 입니다.");
			window.history.back(); return;
		}
		var isLocked = <c:out value = "${bbsInfo.isLocked}"></c:out>;
		if(isLocked == 1) {
			var realPassword = '<c:out value = "${bbsInfo.lockPassword}"></c:out>';
			var inputPassword = prompt("해당 게시판은 잠금상태 입니다.\n비밀번호를 입력해주세요!");
			if(inputPassword != realPassword) {
				alert("비밀번호 틀림");
				window.history.back(); return;
			}
		}
		$("#underTable").removeAttr("hidden");
		$("#content").removeAttr("hidden");
		var userEmail = "${bbsInfo.userEmail}";
		
		var session ="";
		session = "<%= (String) session.getAttribute("emailId")%>";
		
		if(userEmail == session){
			$("#check").removeAttr("hidden");
		}else $("#check").attr("hidden");
		
		var isChk = "${checkLikeInfo.ischecked}"; console.log(isChk);
		if(isChk == 0) {
			$("#likeImg").attr("src", "images/egovframework/bbs/like.png");
			$("#unLikeImg").attr("src", "images/egovframework/bbs/unlike.png");
		}
		else{
			var isLiked = "${checkLikeInfo.isLiked}";
			if(isLiked == 0){
				$("#likeImg").attr("src", "images/egovframework/bbs/like.png");
				$("#unLikeImg").attr("src", "images/egovframework/bbs/clickUnlike.png");	
			}
			else if(isLiked == 1){
				$("#likeImg").attr("src", "images/egovframework/bbs/clickLike.png");
				$("#unLikeImg").attr("src", "images/egovframework/bbs/unlike.png");	
			}
		}
	}
	
	// 중복 클릭 방지 함수 //
	function prevent_overlap(){
		var that = this;
		that.setAttribute("disabled", "disabled");
		setTimeout(function () {
            that.removeAttribute("disabled")
        }, 1500);
	}
	
	$(function(){
		
		checkBBS();
		
		$("#backBtn").click(function(){
			ClickBtn.clickBackBtn();
		});
		
		$("#deleteBtn").click(function(){
			var bbsID = "${bbsInfo.bbsID}";
			ClickBtn.clickdeleteBtn(bbsID);
		});
		
		$("#reviseBtn").click(function(){
			var bbsID = "${bbsInfo.bbsID}";
			ClickBtn.clickReviseBtn(bbsID);
		});
		
		
		$("#likeBtn").click(function(){
			var bbsID = "${bbsInfo.bbsID}";
			prevent_overlap.call(this);
			ClickBtn.clickLikeAndUnlikeBtn.call(ClickAnimateAjax, bbsID, 1);
		});
		
		$("#unLikeBtn").click(function(){
			var bbsID = "${bbsInfo.bbsID}";
			prevent_overlap.call(this);
			ClickBtn.clickLikeAndUnlikeBtn.call(ClickAnimateAjax, bbsID, 0);
		});
	});
</script>
	<div id="content" class="container" hidden="">
		 <table class="table table-striped" style="text-align: center">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center; width: 90%" colspan="2" >글 보 기</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th>제목</th>
		      <td style = "text-align: center" scope="row">
		      <c:out value = "${bbsInfo.bbsTitle}"></c:out></td>
		    </tr>
		    <tr>
		      <th>아이디</th>
		      <td style = "text-align: center" scope="row">
		      <c:out value = "${bbsInfo.userEmail}"></c:out></td>
		    </tr>
		    <tr>
		      <th>작성시간</th>
		      <td style = "text-align: center"scope="row">
		      <c:out value = "${bbsInfo.bbsDate}"></c:out></td>
		    </tr>
		    <tr>
		      <td style="height: 350px; text-align: left" ><c:out value = "${bbsInfo.bbsContent}"></c:out></td>
		    </tr>
		  </tbody>
		</table>	
		
		<div id="underTable" class="container" hidden="">
		 <table class="table table-striped" style="text-align: center">
		  <thead>
		    <tr>
		      <td id="likeTd"><button id="likeBtn" type="button" class="btn btn-outline-primary btn-block"><img id="likeImg" 
		      src="images/egovframework/bbs/like.png">좋아요</button><div id="likeNum">${bbsInfo.likeNum}</div></td>
		      <td><button type="button" class="btn btn-outline-primary btn-block"><img src="images/egovframework/bbs/communicateBox.png">답글</button></td>
		      <td><button id="unLikeBtn" type="button" class="btn btn-outline-primary btn-block"><img id="unLikeImg" 
		      src="images/egovframework/bbs/unlike.png">싫어요</button><div id="unLikeNum">${bbsInfo.unLikeNum}</div></td>
		    </tr>
		  </thead>
		  <tbody>
		    
		  </tbody>
		</table>	
		</div>
		
		<div id="check" hidden="">
			<button id="reviseBtn" class="btn btn-primary float-left" style="display: block;width:100px%">글 수정</button> 
			<button id="deleteBtn" class="btn btn-primary float-left" style="display: block;width:100px%">글 삭제</button>
		</div>
		<button id="backBtn" class="btn btn-primary float-right" style="display: block;width:100px%">뒤로가기</button>
	</div> 