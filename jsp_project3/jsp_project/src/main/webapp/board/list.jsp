<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트 페이지</title>
</head>
<body>
	<table border="1">
		<thead>
		<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>등록일</th>
		<th>조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list }" var="b">
		<tr align="center">
		<td>${b.bnum }</td>
		<td>${b.writer }</td>
		<td><a href="/brd/post?bnum=${b.bnum}">${b.title }</a></td>
		<td>${b.write_date }</td>
		<td>${b.count }</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<a href="/"><button>메인화면으로</button></a>
</body>
</html>