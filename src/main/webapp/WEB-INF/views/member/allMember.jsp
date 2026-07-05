<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>allMember.jsp</title>
</head>
<body>
	<h1>회원 관리 - 전체 회원 조회</h1>

	<hr>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>등급</th>
			<th>가입일</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.memberNo}</td>
				<td>${member.memberId}</td>
				<td>${member.memberName}</td>
				<td>${member.memberEmail}</td>
				<td>${member.memberPhone}</td>
				<td>${member.memberAddr}</td>
				<td>
					<c:if test="${member.memberLevel == 1}"> 
					관리자
					</c:if> 
					<c:if test="${member.memberLevel == 2}"> 
					정회원
					</c:if> 
					<c:if test="${member.memberLevel == 3}"> 
					준회원
					</c:if> 
				</td>
				<td>${member.enrollDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>