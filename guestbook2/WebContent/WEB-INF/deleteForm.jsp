<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook2/gbc" method="get">
		<!-- no값 hidden 처리 -->
		<input type="hidden" name="no" value="${param.no}">
		
		비밀번호: <input type="password" name="password" value="">
		<input type="hidden" name="action" value="delete">
		<button type="submit">확인</button> <br>
		
		<a href="/guestbook2/gbc?action=addList">메인으로 돌아가기</a>
	</form>
</body>
</html>