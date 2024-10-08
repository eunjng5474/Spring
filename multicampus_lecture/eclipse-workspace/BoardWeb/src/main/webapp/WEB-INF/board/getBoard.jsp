<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세</title>
</head>
<body>
<center>
	<h1>게시글 상세</h1>
	<h3><font color="red">${user.name }</font>님 환영합니다.</h3>
	<a href="logout.do">LOGOUT</a>
	<hr>	
	<form action="updateBoard.do" method="get">
	<input name="seq" type="hidden" value="${board.seq }"/>
	<table border="1" cellpadding="0" cellspacing="0" width="500">
		<tr>
			<td bgcolor="orange" width="150">제목</td>
			<td><input name="title" type="text" size="40" value="${board.title }"/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="150">작성자</td>
			<td><input name="writer" type="text" value="${board.writer }" disabled/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="150">내용</td>
			<td><textarea name="content" cols="50" rows="10">${board.content }</textarea></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="150">등록일</td>
			<td><input name="writer" type="text" value="${board.regDate }" disabled/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="150">조회수</td>
			<td><input name="writer" type="text" value="${board.cnt }" disabled/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글 수정"/>
			</td>
		</tr>
	</table>
	</form>
	<hr>
	<br>
	<a href="getBoardList.do">게시글 목록</a>&nbsp;&nbsp;&nbsp;
	<c:if test="${user.role == 'Admin' }">
		<a href="deleteBoard.do?seq=${board.seq }">게시글 삭제</a>&nbsp;&nbsp;&nbsp;
	</c:if>
	<a href="insertBoard.do">게시글 등록</a>&nbsp;&nbsp;&nbsp;
</center>
</body>
</html>







