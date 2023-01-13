<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook4/guest/delete" method="get">
		<!-- no값 hidden 처리 -->
		<input type="hidden" name="no" value="${param.no}">
		
		비밀번호: <input type="password" name="password" value="">
		<button type="submit">확인</button> <br>
		
		<a href="/guestbook4/guest/list">메인으로 돌아가기</a>
	</form>
</body>
</html>