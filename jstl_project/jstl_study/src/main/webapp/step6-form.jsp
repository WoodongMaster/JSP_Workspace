<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL forEach</title>
</head>
<body>
	<%--
	get방식 : 쿼리스트링을 달고 URL 상에서 이동하는 방식(기본)
	post방식 : 데이터를 숨겨서 가는 방식(보안상, URL길이 이점)
	 --%>
	<form action="step6-action.jsp" method="post">
	주문자 : <input type="text" name="name"><br>
	<input type="checkbox" name="food" value="햄버거">햄버거<br>
	<input type="checkbox" name="food" value="돼지국밥">돼지국밥<br>
	<input type="checkbox" name="food" value="피자">피자<br>
	<input type="checkbox" name="food" value="닭강정">닭강정<br>
	<input type="checkbox" name="food" value="초밥">초밥<br>
	<input type="submit">
	</form>
</body>
</html>