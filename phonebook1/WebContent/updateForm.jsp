<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.PhoneDao" %>     
<%@ page import="com.javaex.vo.PhoneVo" %> 

<% 
	PhoneDao pd= new PhoneDao();

	int id= Integer.parseInt(request.getParameter("id"));
	
	PhoneVo pv= pd.getPerson(id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook1]</h1>

	<h2>전화번호 수정폼</h2>

	<p>
		수정 화면입니다.<br>
	   	아래 항목을 기입하고 "수정" 버튼을 클릭하세요
	</p>
	
	<form action="./update.jsp" method="get">
		이름(name): <input type="text" name="name" value="" placeholder="<%=pv.getName()%>"> <br>
		핸드폰(hp): <input type="text" name="hp" value="" placeholder="<%=pv.getHp()%>"> <br>
		회사(company): <input type="text" name="company" value="" placeholder="<%=pv.getCompany()%>"> <br>
		코드(id): <input type="text" name="id" value="" placeholder="<%=id%>"> <br>
		<button type="submit">수정</button>
	</form>
	
</body>
</html>