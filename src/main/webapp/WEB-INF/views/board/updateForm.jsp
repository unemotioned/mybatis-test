<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
	<h1>게시판 - 게시글 수정</h1>

	<hr>

	<form action="/board/update" method="get">
		<input type="hidden" value="${board.boardNo}" name="boardNo">
		<table border=1>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle" value="${board.boardTitle}">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<input type="text" name="boardContent" value="${board.boardContent}">
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.readCount}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.boardWriter}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.regDate}</td>
			</tr>
		</table>
		<input type="submit" value="수정하기">
	</form>
</body>
</html>