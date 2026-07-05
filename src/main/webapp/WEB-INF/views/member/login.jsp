<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<h1>회원 관리 - 로그인 페이지</h1>

	<hr>

	<form action="/member/login" method="post">
		아이디 : <input type="text" name="memberId"> <br> 
		비밀번호 : <input type="password" name="memberPw"> <br> 
		<input type="submit" value="로그인"> <br> 
		<a href="/member/signUpFrm">회원가입 페이지 이동</a>
	</form>
</body>
</html>