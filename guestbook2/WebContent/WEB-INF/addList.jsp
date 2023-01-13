<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<!-- add section -->
	<form action="/guestbook2/gbc" method="get">
		<table border= "1" width= "500px">
			<tr>
				<td>이름</td>
				<td><input type= "text" name="name" value= ""></td>
				<td>비밀번호</td>
				<td><input type= "password" name="password" value= ""></td>
			</tr>
			<tr>
				<td colspan= "4">
					<textarea cols="65" rows="5" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan= "4">
					<input type="hidden" name="action" value="add">
					<button type="submit">글작성</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	
	<!-- list section -->
	<c:forEach items="${requestScope.gl}" var="gl">
		<table border= "1" width= "500px">
			<tr>
				<td>${gl.no}</td>
				<td>${gl.name}</td>
				<td>${gl.regDate}</td>		
				<td><a href="/guestbook2/gbc?action=deleteForm&no=${gl.no}">삭제</a></td>
			</tr>
			<tr>
				<td colspan= "4">
					${gl.content}
				</td>
			</tr>
		</table>
		<br>
	</c:forEach>

	
</body>
</html>