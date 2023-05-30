<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>modify page</title>
</head>
<body>
	<h1>modify 제품 수정 페이지</h1>

	<br>

<div align="center">
	<form action="/mem/edit" method="post" style="width:50%;">
		<input type="hidden" name="id" value="${ses.id }">
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">아이디</span> <input
				type="text" class="form-control" placeholder="UserID"
				aria-label="Username" aria-describedby="basic-addon1" name="id" value="${ses.id }" disabled="disabled">
		</div>
				<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">비밀번호</span> <input
				type="password" class="form-control" placeholder="UserPassword"
				aria-label="Username" aria-describedby="basic-addon1" name="password" value="${ses.password }">
		</div>
				<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">이메일</span> <input
				type="email" class="form-control" placeholder="UserEmail"
				aria-label="Username" aria-describedby="basic-addon1" name="email" value="${ses.email }">
		</div>
				<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">나이</span> <input
				type="text" class="form-control" placeholder="Userage"
				aria-label="Username" aria-describedby="basic-addon1" name="age" value="${ses.age }">
		</div>
				<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">등록일</span> <input
				type="text" class="form-control" placeholder="regDate"
				aria-label="Username" aria-describedby="basic-addon1" name="reg_date" value="${ses.reg_date }" disabled="disabled">
		</div>
		<input type="hidden" name="reg_date" value="${ses.reg_date }">
		<button type="submit" class="btn btn-success">완료</button>
		<a href="/"><button type="button" class="btn btn-danger">취소</button></a>
	</form>
	</div>
	<form action="/mem/delete" method="post" align="center">
		<input type="hidden" name="id" value="${ses.id }">
		<button type="submit" class="btn btn-secondary">회원탈퇴</button>
	</form>
</body>
</html>