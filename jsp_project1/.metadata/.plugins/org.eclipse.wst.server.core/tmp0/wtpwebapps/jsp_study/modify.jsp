<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modify.pd?pno=${pvo.pno}" method="post">
	<table border="1">
			<tr align="center">
				<td>상품번호</td>
				<td>${pvo.pno}</td>
			</tr>
			<tr align="center">
				<td>상품명</td>
				<td><input type="text" name="pname" value="${pvo.pname}"></td>
			</tr>
			<tr align="center">
				<td>가격</td>
				<td><input type="number" name="price" value="${pvo.price}"></td>
			</tr>
			<tr align="center">
				<td>등록일자</td>
				<td>${pvo.regdate}</td>
			</tr>
			<tr align="center">
				<td>세부내용</td>
				<td><input type="text" name="madeby" value="${pvo.madeby}"></td>
			</tr>
	</table>
			<button type="submit">수정확인</button>
</form>
</body>
</html>