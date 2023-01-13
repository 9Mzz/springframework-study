<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>el jstl</h1>
	
	<h2>객체접근</h2>
	
	<%@ page import="vo.UserVo" %>
	<%
		UserVo vo= (UserVo)request.getAttribute("userVo");
		int num= (int)request.getAttribute("num");
		String str= (String)request.getAttribute("str");
	%>
	
	no = <%=vo.getNo()%> <br>
	name = <%=vo.getName()%> <br>
	email = <%=vo.getEmail()%> <br>
	password = <%=vo.getPassword()%> <br>
	gender = <%=vo.getGender()%> <br>
	<br>
	num = <%=num%> <br>
	str = <%=str%> <br>
	
	<br><br>
	
	<h3>el</h3>
	
	no = ${requestScope.userVo.no} <br>
	name = ${requestScope.userVo.name} <br>
	email = ${requestScope.userVo.email} <br>
	password = ${requestScope.userVo.password} <br>
	gender = ${requestScope.userVo.gender} <br>
	tostring = ${requestScope.userVo} <br>
	<br>
	num = ${requestScope.num} <br>
	str = ${requestScope.str} <br>
	
	
	
</body>
</html>