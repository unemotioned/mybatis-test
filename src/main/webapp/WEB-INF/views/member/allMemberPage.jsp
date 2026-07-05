<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>allMemberPage.jsp</title>
</head>
<body>
	<h1>회원 관리 - 전체 회원 조회(페이징)</h1>

	<hr>

	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>등급</th>
			<th>가입일</th>
		</tr>
		<c:forEach var="m" items="${memberList}">
			<tr>
				<td>${m.memberId}</td>
				<td>${m.memberName}</td>
				<td>${m.memberEmail}</td>
				<td>${m.memberPhone}</td>
				<td>${m.memberAddr}</td>
				<td><c:choose>
						<c:when test="${m.memberLevel == 1 }">
				관리자
				</c:when>
						<c:when test="${m.memberLevel == 2 }">
				정회원
				</c:when>
						<c:otherwise>
				준회원
				</c:otherwise>
					</c:choose></td>
				<td>${m.enrollDate}</td>
			</tr>
		</c:forEach>
	</table>
	${pageNavi}
</body>
</html>