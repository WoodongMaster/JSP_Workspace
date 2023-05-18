<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body align="center">
	<h1>회원가입 페이지</h1>

	<form action="/mem/register" method="post">
		아이디 : <input type="text" name ="id" placeholder="사용할 ID를 입력하세요"><br>
		비밀번호 : <input type="password" name="password" placeholder="사용할 비밀번호를 입력하세요"><br>
		이름 : <input type="text" name="name"><br> 
		나이 : <input type="number" name="age"><br>
		email : <input type="text" name="email"><br> 
		주소 : <input type="text" name="address"><br>
		<button type="submit">가입완료</button>
	</form>
</body>
</html>