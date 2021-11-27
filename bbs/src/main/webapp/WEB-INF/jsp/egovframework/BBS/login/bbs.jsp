<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	.arrowBtn{
		cursor:pointer;
		position : relative;
	}
</style>

<script>
	function pageChange() {
		var pageNumber = <%=request.getAttribute("pageNumber") %>;
		console.log(<%=request.getAttribute("isRight")%>);
		if(pageNumber != 0){
			$("#leftArrow").removeAttr("hidden");
			console.log(data);
		}else{
			
			$("#leftArrow").attr("hidden");
		}
		
	}

	$(function(){
		$("tr > td").click(function(){
			var id = ""
			id = $(this).attr('id');
			Click_C.bbsIDClick_A("clickBBSAction", id);
		});
	});
	/*
	$(function(){
		$("#rightArrow").click(function(){
			$.ajax({
				url : "/arrowAjax.do",
				data : {
						"pageNumber" : $("#pageNumber").val(),
						"isRight" : true
				},
				success : function(data) {
					
					pageChange(data);
				}
			});
		});
		
		$("#leftArrow").click(function(){
			$.ajax({
				url : "/arrowAjax.do",
				data : {
						"pageNumber" : $("#pageNumber").val(),
						"isRight" : false
				},
				success : function(data) {
					console.log(data);
				}
			});
		});
	});*/
	function arrowCheckFn() {
		var pageNumber = <%=request.getAttribute("pageNumber") %>;
		var isRight = <%=request.getAttribute("isRight")%>;
		if(pageNumber != 0){
			$("#leftArrow").removeAttr("hidden");
		}else{
			$("#leftArrow").attr("hidden");
		}
		if(isRight != 0){
			$("#rightArrow").removeAttr("hidden");
		}else{
			$("#rightArrow").attr("hidden");
		}
	}
	
	
	$(function(){
		$("#rightArrow").click(function(){
			
			location.href = "/arrowAction.do?"+"isRight="+"1"+
							"&pageNumber="+<%=request.getAttribute("pageNumber").toString()%>;
		});
		
		$("#leftArrow").click(function(){
			location.href = "/arrowAction.do?"+"isRight="+"0"+
			"&pageNumber="+<%=request.getAttribute("pageNumber").toString()%>;
		});
		arrowCheckFn();
	});
</script>

<form action="/write.do">
	
	<div class="container">
		 <table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">잠금표시</th>
		      <th scope="col">번호</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성일</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${bbsInfoList}" var="bbsInfoList">
		    <tr>
		   		 <c:set var="lock" value="${bbsInfoList.isLocked}" />
		   		 <c:set var="bbsAvailable" value="${bbsInfoList.bbsAvailable}" />
			<c:choose>
				 	<c:when test="${bbsAvailable eq 0}">
				       	 <td><img src="images/egovframework/bbs/x.png"></td>
				    </c:when>
				    <c:when test="${lock eq 0}">
				       	<td><img src="images/egovframework/bbs/unlock.png"></td>
				    </c:when>
		    		<c:when test="${lock eq 1}">
		        		<td><img src="images/egovframework/bbs/lock.png"></td>
		    		</c:when>
			</c:choose>
		      <td scope="row"><c:out value="${bbsInfoList.bbsID}"></c:out></td>
		      <td id="${bbsInfoList.bbsID}"><a href="#" ><c:out value="${bbsInfoList.bbsTitle}"></c:out> </a></td>
		      <td><c:out value="${bbsInfoList.userEmail}"></c:out></td>
		      <td><c:out value="${bbsInfoList.bbsDate}"></c:out></td>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
		
		
		<div class="arrowBtn">
				<i   hidden="" id="leftArrow" class="fas fa-caret-left fa-5x" ></i>
			
				<i  hidden= "" id="rightArrow" class="fas fa-caret-right fa-5x float-right" ></i>
		
			<input type ="hidden" id="pageNumber" name="pageNumber" value=<%=request.getAttribute("pageNumber")%>>
		</div>
		<br><br><br><br><br><br>
		<div class="text-right">
			<button type="submit" class="btn btn-primary float-right" style="display: block;width:100px%">Write</button>
		</div>
	</div>
</form>