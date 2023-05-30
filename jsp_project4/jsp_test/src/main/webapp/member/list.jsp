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
<title>List Page</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr class="table-primary" align="center">
				<th>아이디</th>
				<th>이메일</th>
				<th>나이</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mem_list}" var="list">
			<tr align="center">
		<td>${list.id}</td>
		<td>${list.email}</td>
		<td>${list.age}</td>
		<td>${list.reg_date}</td>
		</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center"><a href="/"><button class="btn btn-secondary">메인으로</button></a></div>
	
</body>
</html>