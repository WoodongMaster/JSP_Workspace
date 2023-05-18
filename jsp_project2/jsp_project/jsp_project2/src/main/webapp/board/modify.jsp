<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
</head>
<body>
<form action="/brd/edit">
	<table border="1">
		<tr>
			<th>글번호</th>
		</tr>
		<tr align="center">
			<td><input name="bnum" value="${post.bnum}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
		</tr>
		<tr align="center">
			<td><input name="title" value="${post.title}"></td>
		</tr>
		<tr>
			<th>작성자</th>
		</tr>
		<tr align="center">
			<td><input name="writer" value="${post.writer}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>등록일자</th>
		</tr>
		<tr align="center">
			<td><input name="write_date" value="${post.write_date}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>조회수</th>
		</tr>
		<tr align="center">
			<td><input name="count" value="${post.count}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>내용</th>
		</tr>
		<tr align="center">
			<td><input name="post" value="${post.post}"></td>
		</tr>
	</table>
	<button type="submit">수정완료</button><a href="/"><button>수정취소</button></a>
	</form>
</body>
</html>