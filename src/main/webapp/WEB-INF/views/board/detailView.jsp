<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetail.jsp</title>
</head>
<body>
	<h1>게시글 상세보기</h1>
	<hr>
	<table border="1">
		<tbody>
			<tr>
				<th>제목</th>
				<td>${board.boardTitle}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.boardWriter}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.readCount}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.regDate}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.boardContent}</td>
			</tr>
			<c:if test="${board.boardWriter eq loginMember.memberId}">
				<tr>
					<td colspan="2"><a
						href="/board/updateForm?boardNo=${board.boardNo}">수정하기</a></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>