<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify page</title>
</head>
<body>
		<h1>modify 제품 수정 페이지</h1>
	
	<br>
	
	<form action="/mem/modify" method="post">
	<input type="hidden" name="id" value="${ses.id }">
		아이디 : <input type="text" name="id" value="${ses.id }" disabled="disabled"> <br> 
		비밀번호 : <input type="password" name="password" value="${ses.password }"> <br>
		나이 : <input type="text" name="age" value="${ses.age }"> <br>
		이메일 : <input type="text" name="email" value="${ses.email }" > <br>
		등록일 : <input type="text" name="reg_date" value="${ses.reg_date }" disabled="disabled"> <br>
		
		<input type="hidden" name="reg_date" value="${ses.reg_date }"><br>
		<input type="hidden" name="last_login" value="${ses.last_login }"><br>
		<button type="submit">완료</button>
		<a href="/"><button type="button">취소</button></a>
	</form>
	<form action="/mem/delete" method="post">
		<input type="hidden" name="id" value="${ses.id }">
		<button type="submit">회원탈퇴</button>
	</form>
</body>
</html>