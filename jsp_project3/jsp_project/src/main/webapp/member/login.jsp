<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
<h1>LogIn page</h1>
<form action="/mem/login_auth" method="post">
ID : <input type="text" name="id"><br> 
Password : <input type="password" name="password"><br>
<button type="submit">로그인</button> 
</form>
	<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}" />`;
	if(msg_login == '0'){
		alert('로그인 실패');
	}

	</script>
</body>
</html>