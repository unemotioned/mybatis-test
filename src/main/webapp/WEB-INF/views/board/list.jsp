<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>게시글 - 전체 게시글 조회(페이징)</h1>

	<hr>
	<%--
		게시글 번호는 오라클 시퀸스 객체로 자동 채번되어 삽입되어지는 값
		게시글 등록시 오류가 발생해도 시퀀스는 다음 번호를 채번한다 => 번호간에 빈값이 생길 수 있음
		사용자에게 보여지는 값은 == 행번호 (rNum)
		실제 수정, 삭제 시 SQL where 조건식에 작성될 값은 == 게시글 번호
	 --%>
	<form action="/board/delete" method="get">
		<table border="1">
			<tr>
				<th>삭제</th>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="b" items="${boardList}">
				<tr>
					<td><input type="checkbox" name="delNo" value="${b.boardNo}"></td>
					<td>${b.RNum}</td>
					<td><a href="/board/detail?boardNo=${b.boardNo}&pageGb=select">${b.boardTitle}</a></td>
					<td>${b.boardWriter}</td>
					<td>${b.readCount}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="삭제">
	</form>
	<h4>${pageNavi}</h4>

	<%-- 
    	게시글 목록 페이지에서 아래와 같이 검색하면
		조건에 부함하는 게시글 목록을 조회해서 다시 현재 페이지로 응답해주어야 함
		기존 index.jsp 에서 게시판 목록을 요청했을 때 작성한 URL과 동일한 URL로 요청하여 재검색
	 --%>
	<form action="/board/getList" method="get">
		<select name="searchType">
			<option value="boardTitle">제목</option>
			<option value="boardWriter"
				<c:if test="${searchType == 'boardWriter'}">
				selected
				</c:if>>
				작성자</option>
		</select> <input type="text" name="searchKeyword" value="${searchKeyword}">
		<input type="submit" value="조회">
	</form>

	<script>
		window.addEventListener('pageshow', function(event) {
			console.log("list.jsp");
			if (event.persisted) {
				$.ajax({
					url : "/board/backHistory",
					method : "get",
					data : {
						pageId : "detailView"
					},
					success : function(res) {
						console.log("removed from session");
					},
					error : function() {
						console.log("ajax error");
					}
				});
			}
		});
	</script>
</body>
</html>
