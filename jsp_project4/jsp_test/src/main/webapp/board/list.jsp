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
<title>Board ListPage</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr align="center">
				<th scope="col">글번호</th>
				<th scope="col">작성자</th>
				<th scope="col">제목</th>
				<th scope="col">등록일</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<tbody class="table-group-divider" align="center">
			<c:forEach items="${list }" var="b">
				<tr>
					<td scope="row">${b.bnum }</td>
					<td>${b.writer }</td>
					<td>
					<c:if test="${b.imgfile ne '' && b.imgfile ne null}">
						<img alt="없음" src="/_fileUpload/th_${b.imgfile}">
					</c:if>
					<a href="/brd/post?bnum=${b.bnum}">${b.title }</a>
					</td>
					<td>${b.write_date }</td>
					<td>${b.count }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width:100%" align="center"><a href="/brd/register" ><button class="btn btn-success" align="center">글쓰기</button></a></div>
</body>
</html>