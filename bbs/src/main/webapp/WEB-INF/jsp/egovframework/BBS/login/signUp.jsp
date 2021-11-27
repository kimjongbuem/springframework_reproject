<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<title>회원가입</title>
<form action="/signUpCheck.do" method="post">
	
  <div class="form-group">
    <label for="emailId">이메일 아이디</label>
    <input type="email" class="form-control" 
    id="emailId" name="emailId" aria-describedby="emailHelp" placeholder="Enter email"
    style="display: block;margin: 4px 79px 0 10px;width:80%" >
  </div>
  <div class="form-group">
    <label for="password">패스워드</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="Password"
    style="display: block;margin: 4px 79px 0 10px;width:80%">
  </div>
   <div class="form-group">
    <label for="userName">이름</label>
    <input type="text" name="userName" class="form-control" id="userName" placeholder="이름"
    style="display: block;margin: 4px 79px 0 10px;width:80%">
  </div>
   <div class="form-group">
    	 남자 <input type="radio" name="gender" value="male" checked />
               여자 <input type="radio" name="gender" value="female" />
  </div>
  <button id= "signUpBtn" type="submit" class="btn btn-primary"  
  style="display: block;margin: 4px 79px 0 10px;width:80%">회원가입</button>
</form>

