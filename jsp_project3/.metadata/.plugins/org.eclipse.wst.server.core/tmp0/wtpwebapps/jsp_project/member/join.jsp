<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join 회원가입 페이지</title>
</head>
<body>
	<h1>== 회원가입 페이지 ==</h1>
	
	<script type="text/javascript">
	const msg_regist = `<c:out value="${msg_regist}" />`;
	if(msg_regist == '0'){
		alert('중복된 아이디입니다');
	}
	</script>
	
	<form action="/mem/register" method="post">
		id : <input type="text" name="id" placeholder="id"> <br>
		password : <input type="password" name="password" placeholder="password"> <br>
		email : <input type="text" name="email" placeholder="email"> <br>
		age : <input type="number" name="age" placeholder="age"> <br>
		<button type="submit">가입완료</button>
	</form>
</body>
</html>