<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signUp.jsp</title>
</head>
<body>
	<h1>회원관리 - 회원 가입</h1>

	<hr>

	<form action="/member/signUp" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memberEmail"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="memberPhone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="memberAddr"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="회원가입"></th>
			</tr>
		</table>
	</form>
</body>
</html>