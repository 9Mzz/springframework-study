<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.PhoneDao" %>     
<%@ page import="com.javaex.vo.PhoneVo" %> 

<%
	PhoneDao pd= new PhoneDao();

	String name= request.getParameter("name");
	String hp= request.getParameter("hp");
	String company= request.getParameter("company");
	int id= Integer.parseInt(request.getParameter("id"));
	
	PhoneVo pv= new PhoneVo(id, name, hp, company);
	pd.personUpdate(pv);
	
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