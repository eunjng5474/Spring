<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
	<h1>게시글 목록</h1>
	<h3><font color="red">${user.name }</font>님 환영합니다.</h3>
	<a href="logout.do">LOGOUT</a>
	<hr>

	<!-- 검색 시작 -->
	<form action="getBoardList.do" method="get">
	<table border="1" cellpadding="0" cellspacing="0" width="800">
		<tr>
			<td align="right">
				<select name="searchCondition">
					<option value="title">제목
					<option value="content">내용
				</select>
				<input name="searchKeyword" type="text"/>
				<input type="submit" value="검색"/>
			</td>
		</tr>
	</table>
	</form>
	<!-- 검색 종료 -->
	
	<table border="1" cellpadding="0" cellspacing="0" width="800">
		<tr>
			<th bgcolor="orange" width="100">번호</th>
			<th bgcolor="orange" width="300">제목</th>
			<th bgcolor="orange" width="150">작성자</th>
			<th bgcolor="orange" width="150">등록일</th>
			<th bgcolor="orange" width="100">조회수</th>
		</tr>
		<c:forEach var="board" items="${boardList }">
		<tr>
			<td>${board.seq }</td>
			<td><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
			<td>${board.writer }</td>
			<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
			<td>${board.cnt }</td>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<br>
	<a href="insertBoard.do">글 등록 화면으로 이동</a>
</center>
</body>
</html>