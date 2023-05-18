<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL if</title>
</head>
<body>
	<%-- JSTL if 조건 --%>
	<c:if test="true">
	<%-- test 조건이 참일 경우에만 실행 --%>
	true 이므로 실행되어야 함.
	</c:if>
	
	<%-- Query String 방식 --%>
	
	<a href="step03.jsp?age=11&nick=apple">step3 다시 호출</a>
	<br><br>
	<c:if test="${param.age>5 && param.nick=='apple'}" >
	이름 : ${param.nick}<br>
	나이 : ${param.age}<br>
	</c:if>
	
	
</body>
</html>