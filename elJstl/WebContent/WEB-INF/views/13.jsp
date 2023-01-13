<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>el jstl</h1>
	
	<h2>jstl 반복문</h2>
	
	<h3>자바문법</h3>
	
	<%@ page import="java.util.List" %>
	<%@ page import="vo.UserVo" %>
	
	<%
		List<UserVo> uList= (List<UserVo>)request.getAttribute("userList");
	%>
	
	<table border= "1">
		<tr>
			<th>no</th>
			<th>name</th>
			<th>email</th>	
		</tr>
		
		<%for(int i= 0; i<uList.size(); i++) { %>
		<tr>
			<td><%=uList.get(i).getNo()%></td>
			<td><%=uList.get(i).getName()%></td>
			<td><%=uList.get(i).getEmail()%></td>		
		</tr>
		<%} %>
	</table>
	
	<h3>jstl</h3>
	
	<table border= "1">
		<tr>
			<th>no</th>
			<th>name</th>
			<th>email</th>
			<th>index</th>	
			<th>count</th>		
		</tr>
		
		<c:forEach items="${requestScope.userList}" var="vo" varStatus="staus" begin="" end="">
			<tr>
				<td>${vo.no}</td>
				<td>${vo.name}</td>
				<td>${vo.email}</td>
				<td>${staus.index}</td>
				<td>${staus.count}</td>		
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>