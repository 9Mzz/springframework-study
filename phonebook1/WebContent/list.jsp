<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.dao.PhoneDao" %>     
<%@ page import="com.javaex.vo.PhoneVo" %>   
<%
	PhoneDao pd= new PhoneDao();
	List<PhoneVo> pList= pd.getPersonList();
%>    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[phonebook1]</h1>
	
	<h2>전화번호 리스트</h2>
	
	<p>입력한 정보 내역입니다.</p>
	
	<%
	for(int i= 1; i<pList.size(); i++) {
	%>
	
	<table border= "1">
		<tr>
			<td>이름(name)</td>
			<td><%=pList.get(i).getName()%><%=" ("+pList.get(i).getPersonId()+")"%></td>
		</tr>
		<tr>
			<td>핸드폰(hp)</td>
			<td><%=pList.get(i).getHp()%></td>
		</tr>
		<tr>
			<td>회사(company)</td>
			<td><%=pList.get(i).getCompany()%></td>
		</tr>
		<tr>
			<td><a href="./updateForm.jsp?id=<%=pList.get(i).getPersonId()%>">[수정]</a></td>
			<td><a href="./delete.jsp?id=<%=pList.get(i).getPersonId()%>">[삭제]</a></td>
		</tr>
	</table>
	<br>
	
	<%
	}
	%>
	
	<a href="http://localhost:8088/phonebook1/writeForm.jsp">전화번호 등록폼</a>
</body>
</html>