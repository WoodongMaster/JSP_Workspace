<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- CSS only -->
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
<title>Join Page</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	
	<script type="text/javascript">
	const msg_regist = `<c:out value="${msg_regist}" />`;
	if(msg_regist == '0'){
		alert('중복된 아이디입니다');
	}
	</script>
	<div align="center">
	<form action="/mem/register" method="post" style="width:20%" >
	<div class="mb-3">
  <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="ID" name="id">
</div>
	<div class="mb-3">
  <input type="password" class="form-control" id="exampleFormControlInput1" placeholder="Password" name="password">
</div>
	<div class="mb-3">
  <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Email" name="email">
</div>
	<div class="mb-3">
  <input type="number" class="form-control" id="exampleFormControlInput1" placeholder="Age" name="age">
</div>
		<button type="submit" class="btn btn-success">가입완료</button>
		<a href="/"><button type="button" class="btn btn-danger">가입취소</button></a>
	</form>
	</div>
</body>
</html>