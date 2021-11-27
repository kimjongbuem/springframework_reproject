<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- header -->
<header id="header" class="main">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <h3><a class="navbar-brand" href="/main.do">웹 사이트</a></h3>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" id="tagUl">
      <li class="nav-item" id="main">
        <a class="nav-link"  href="#">메인</a>
      </li>
      <li class="nav-item" id="bbs">
        <a class="nav-link"  href="#">게시판</a>
      </li>
    </ul>
    
    <%

    	if(session.getAttribute("emailId") == null){
    %>
   
    
    <ul class="nav-item navbar-nav navbar-right">
    	<li class="dropdown">
    		<a href="#" class="dropdown-toggle" id="navbarDropdown"
    		data-toggle="dropdown" role="button" aria-haspopup="true"
    		aria-expanded="false">접속하기<span class="caret"></span></a>
    		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
    			<ul id="listUl">
          			<li id="loginFirst"><a class="dropdown-item" href="#">로그인</a></li>
          			<li id="signUp"><a class="dropdown-item" href="#">회원가입</a></li>
          		</ul>
        	</div>
    	</li>
    </ul>
    <%
    	}else{    
    %>
	    <ul class="nav-item navbar-nav navbar-right">
	    	<li class="dropdown">
	    		<a href="#" class="dropdown-toggle" id="navbarDropdown"
	    		data-toggle="dropdown" role="button" aria-haspopup="true"
	    		aria-expanded="false">회원관리<span class="caret"></span></a>
	    		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
	    			<ul id="listUl">
	          			<li id="logout"><a class="dropdown-item" href="#">로그아웃</a></li>
	          		</ul>
	        	</div>
	    	</li>
	    </ul>
    <%
    	}
    %>
  </div>
</nav>
</header>
<!--// header -->

<script>
	var Click_C ={
			listClick_A : function(loc){
				$("#pageFrm").attr("action","/"+loc+".do");
				$("#pageName").val(loc);
				$("#pageFrm").submit();
			},
			bbsIDClick_A : function(loc, id){
				$("#pageFrm").attr("action","/"+loc+".do");
				$("#bbsID").val(id);
				$("#pageFrm").submit();
			}
			
	};
	
	$(function(){
		$("#listUl > li").click(function(){
			$("#listUl > li").removeClass("active");
			Click_C.listClick_A($(this).attr("id"));
			$(this).addClass("active");
		});
	});
	
	$(function(){
		$("#tagUl > li").click(function(){
			$("#tagUl > li").removeClass("active");
			Click_C.listClick_A($(this).attr("id"));
			$(this).addClass("active");
		});
	});
</script>

<form id="pageFrm" method="post">
	<input id="pageName" name="pageName" type="hidden"/>
	<input id="bbsID" name="bbsID" type="hidden"/>
	<input id="bbsPassword" name="bbsPassword" type="hidden"/>
</form>
