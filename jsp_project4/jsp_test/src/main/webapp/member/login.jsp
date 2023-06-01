<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
	<h1>LogIn page</h1>
	<div align="center">
		<form action="/mem/login_auth" method="post" style="width:50%;">
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">ID</span> <input
					type="text" class="form-control" placeholder="UserID"
					aria-label="Username" aria-describedby="basic-addon1" name="id">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">Password</span> <input
					type="text" class="form-control" placeholder="UserPassword"
					aria-label="Username" aria-describedby="basic-addon1"
					name="password">
			</div>
			<button type="submit" class="btn btn-success">로그인</button>
			<a href="/"><button type="button" class="btn btn-secondary">돌아가기</button></a>
		</form>
	</div>
	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}" />`;
		if (msg_login == '0') {
			alert('로그인 실패');
		}
	</script>
</body>
</html>