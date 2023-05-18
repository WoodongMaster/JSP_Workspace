<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list보기</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>나이</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ses}" var="list">
			<tr align="center">
		<td>${list.id}</td>
		<td>${list.email}</td>
		<td>${list.age}</td>
		<td>${list.reg_date}</td>
		</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>