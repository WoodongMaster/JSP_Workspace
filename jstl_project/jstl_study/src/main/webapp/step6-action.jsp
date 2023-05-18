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
	<%-- post방식의 한글처리 (인코딩 설정) 맨위에 선언해줘도됨 --%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	주문자 : ${param.name}<br>
	--주문 음식-- <br>
	<c:forEach items="${paramValues.food}" var="fname" varStatus="order">
		${fname}<br>
	</c:forEach>
	
	<%-- <c:forEach begin="시작숫자" end="끝숫자" var="변수명"> --%>
	<c:forEach begin="1" end="10" var="num">
		${num }<br>
	</c:forEach>
</body>
</html>