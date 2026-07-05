<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>myPage.jsp</title>
</head>
<body>
	<h1>회원 관리 - 마이페이지</h1>

	<hr>

	<form action="/member/update" method="post">
		<input type="hidden" name="memberNo" value="${loginMember.memberNo}">
		<table border="1">
			<tr>
				<th>아이디</th>
				<%-- sessionScope 처럼 앞에다가 지정해주지 않으면 범위가 좁은거 부터 찾아서 온다 --%>
				<td><span>${loginMember.memberId}</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPw"
					value="${loginMember.memberPw}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memberEmail"
					value="${loginMember.memberEmail}"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName"
					value="${loginMember.memberName}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="memberPhone"
					value="${loginMember.memberPhone}"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="memberAddr"
					value="${loginMember.memberAddr}"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><c:choose>
						<%-- ${} 를 사용하는것은 EL 문법 --%>

						<c:when test="${loginMember.memberLevel == 1}">
						관리자
						</c:when>
						<c:when test="${loginMember.memberLevel == 2}">
						정회원
						</c:when>
						<c:otherwise>
						준회원
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><span> ${loginMember.enrollDate}</span>
			</tr>
			<tr>
				<td><input type="submit" value="수정하기">
					<button type="button" onclick="deleteMember()">삭제하기</button></td>
			</tr>

		</table>
	</form>

	<script>
		function deleteMember() {
			// 삭제 하기 버튼을 누르면 form 태그의 action 속성을 바꿔준다
			// 삭제 하기 버튼을 누르면 다른 SERVLET 으로 연결된다
			$('form').attr('action', '/member/delete');
			$('form').submit();
		}
	</script>
</body>
</html>