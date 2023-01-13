<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook4]</h1>

	<h2>전화번호 수정폼</h2>

	<p>
		수정 화면입니다.<br>
	   	아래 항목을 기입하고 "수정" 버튼을 클릭하세요
	</p>
	
	<form action="/phonebook4/phone/update" method="get">
		이름(name): <input type="text" name="name" value="" placeholder="${vo.name}"> <br>
		핸드폰(hp): <input type="text" name="hp" value="" placeholder="${vo.hp}"> <br>
		회사(company): <input type="text" name="company" value="" placeholder="${vo.company}"> <br>
		코드(id): <input type="text" name="personId" value="${vo.personId}" placeholder="${vo.personId}"> <br>
		<button type="submit">수정</button>
	</form>
	
</body>
</html>