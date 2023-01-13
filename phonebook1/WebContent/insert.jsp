<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.PhoneDao" %>     
<%@ page import="com.javaex.vo.PhoneVo" %> 
<%@ page import="java.util.List" %>

<%
	// PhoneDao를 메모리에 올린다
	PhoneDao pd= new PhoneDao();
	
	/* 저장 관련 */
	// 파라미터값 가져오기
	String name= request.getParameter("name");
	String hp= request.getParameter("hp");
	String company= request.getParameter("company");
	
	/* 테스트
	System.out.println(name);
	System.out.println(hp);
	System.out.println(company);
	*/
	
	// 전송된 값을 vo 객체로 만든다
	PhoneVo pv= new PhoneVo(name, hp, company);
	
	// 저장
	pd.personInsert(pv);
	
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