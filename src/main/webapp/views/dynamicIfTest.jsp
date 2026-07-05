<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dynamicIfTest.jsp</title>
</head>
<body>
	<h1>IF - 회원정보</h1>

	<hr>
	<%--
	dynamicQueryTest.jsp 에서 체크한 정보만 화면에 출력할 수 있도록 아래와 같이 처리
	 --%>
	<table border="1">
		<tr>
			<c:if test="${not empty chkInfo.SFlag1}">
				<th>번호</th>
			</c:if>
			<c:if test="${not empty chkInfo.SFlag2}">
				<th>아이디</th>
			</c:if>
			<c:if test="${not empty chkInfo.SFlag3}">
				<th>이름</th>
			</c:if>
			<c:if test="${not empty chkInfo.SFlag4}">
				<th>이메일</th>
			</c:if>
			<c:if test="${not empty chkInfo.SFlag5}">
				<th>전화번호</th>
			</c:if>
		</tr>
		<c:forEach var="m" items="${list}">
			<tr>
				<c:if test="${not empty chkInfo.SFlag1}">
					<td>${m.memberNo}</td>
				</c:if>
				<c:if test="${not empty chkInfo.SFlag2}">
					<td>${m.memberId}</td>
				</c:if>
				<c:if test="${not empty chkInfo.SFlag3}">
					<td>${m.memberName}</td>
				</c:if>
				<c:if test="${not empty chkInfo.SFlag4}">
					<td>${m.memberEmail}</td>
				</c:if>
				<c:if test="${not empty chkInfo.SFlag5}">
					<td>${m.memberPhone}</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>