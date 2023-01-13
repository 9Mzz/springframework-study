<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.PhoneDao" %>     
<%@ page import="com.javaex.vo.PhoneVo" %> 

<%
	// PhoneDao를 메모리에 올린다
	PhoneDao pd= new PhoneDao();
	
	
	int id= Integer.parseInt(request.getParameter("id"));
	
	pd.personDelete(id);
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>