<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.PersonVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL choose</title>
</head>
<body>
	<%-- model 패키지안에 person 클래스 생성 --%>
	<%
		PersonVO pv1 = new PersonVO("존", 21);
		PersonVO pv2 = new PersonVO("윅", 25);
		request.setAttribute("p1", pv1);
		request.setAttribute("p2", pv2);
	%>
	
	이름 : ${p1.name} 나이 : ${p1.age}<br>
	이름 : ${p2.name} 나이 : ${p2.age}<br>
	
	<c:if test="true"></c:if>
	
	<c:if test="${p1.age>20}">
		${p1.name}은 성인입니다.<br>
	</c:if>
	<c:if test="${p2.age>20}">
		${p2.name}은 성인입니다.<br>
	</c:if>
	
	<%-- 다중조건을 사용할 경우 choose, when, otherwise --%>
	<%--나이가 10살 이상이면 청소년, 20살 이상이면 성인 --%>
	<c:choose>
		<c:when test="${p1.age >= 10}">
		청소년입니다<br>
		</c:when>
		<c:when test="${p1.age > 20}">
		성인입니다<br>
		</c:when>
		<c:otherwise>
		유아입니다<br>
		</c:otherwise>
	</c:choose>
	
	<%-- 
	step5-form.jsp : 이름과 나이를 입력받고 step5-action.jsp로 전송 
	step5-action.jsp : 값을 받아 이름,나이 출력후 c:choose
	다중조건 : 나이가 20살 이상이면 성인, 15세 이상이면 청소년, 5살 이상이면 어린이, 1살 이상이면 유아, 나머지는 아직 태어나지 않았습니다.
	--%>
	
	
</body>
</html>