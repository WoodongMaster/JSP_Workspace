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
	<% 
		String food[] = {"족발", "닭강정", "돼지국밥","순대국밥","설렁탕"};
		request.setAttribute("f", food);
	%>
	
	<%--
		forEach : 반복문 
		items : 대상배열, Collection
		var : 요소를 저장할 임시 변수	
		varStatus : 변수 => 변수.count(개수), 변수.index(주소)
	--%>
	<c:forEach items="${requestScope.f}" var="fname" varStatus="order">
		count : ${order.count}<br>
		index : ${order.index}<br>
		${fname}<br>
	</c:forEach>
	
</body>
</html>