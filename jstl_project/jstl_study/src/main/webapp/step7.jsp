<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.PersonVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>personVO list로</title>
</head>
<body>
	<%
		List<PersonVO> list = new ArrayList<>();
		list.add(new PersonVO("김덕배", 10));
		list.add(new PersonVO("박덕배", 15));
		list.add(new PersonVO("이덕배", 20));
		list.add(new PersonVO("문덕배", 5));
		list.add(new PersonVO("윤덕배", 25));
		request.setAttribute("people", list);
	%>
	<c:if test="true"></c:if>
	<table border="1">
	<thead>
		<tr align="center">
			<td>번호</td>
			<td>이름</td>
			<td>나이</td>
			<td>연령대</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${people}" var="li" varStatus="liStatus">
		<tr align="center">
			<td>${liStatus.count}</td>
			<td>${li.name}</td>
			<td>${li.age}</td>
<%-- 			<c:choose>
				<c:when test="${li.age>=20}">
				<td>성인</td>
				</c:when>
				<c:otherwise>
				<td>미성년</td>
				</c:otherwise>
			</c:choose> --%>
			<td>${(li.age>=20)? "성인" : "미성년"}</td>
		</tr>
	</c:forEach>
	</tbody>
	

			
	</table>
	<%-- 테이블을 생성하여 표로 출력 
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
	--%>
</body>
</html>