<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	$(function(){
		$("#isLockedCheckBox").change(function(){
			if($(this).is(":checked")){
				$("#lockPassword").attr("hidden",false);
			}
			else {
				$("#lockPassword").attr("hidden",true);
				$("#lockPassword").val('');
			}
		});
	});
</script>   

<form action="/reWriteAction.do" method="post" >    
	<div class="container" >
		 <table class="table table-striped" style="text-align: center">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center">게시판 글쓰기 양식</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row"><input type="text" class="form-control" value= "<c:out value = "${bbsInfo.bbsTitle}"></c:out>"
		      placeholder="글 제목" name="bbsTitle" maxlength="50"/>
		      
		      </th>
		    </tr>
		    <tr>
		      <th><textarea style="height: 350px" 
		      class="form-control" placeholder="글 내용" 
		      name="bbsContent" maxlength="2048">  <c:out value = "${bbsInfo.bbsContent}"></c:out></textarea> </th>
		    </tr>
		  </tbody>
		</table>
		<input type="hidden" name="bbsID" value="${bbsInfo.bbsID}">
		<div class="form-check form-check-inline float-none">
		  <input class="form-check-input" type="checkbox" id="isLockedCheckBox" value="lock">
		  <label class="form-check-label" for="isLockedCheckBox">잠금표시</label>
		</div>
		<input hidden="true" type="password" name= "lockPassword" id="lockPassword">	
		<button type="submit" class="btn btn-primary float-right" style="display: block;width:100px%">재작성</button>
	</div>
</form>