<%@ page import="model.CarVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL연습</title>
</head>
<body>
	<h3>EL(Expression Language)</h3>
	<%
	CarVO car = new CarVO("1234", "Sonata", 2000);
	//request.setAttribe : request객체에 값을 저장하는 역할
	//request.getAttribe : request객체에 값을 가져오는 역할
	//request.setAttribe("변수명", 값);
	request.setAttribute("cvo", car);
	%>
	<strong>1. CarVO의 객체에서 변수 출력</strong>
	<br>
	<!-- ctrl shift /  -->
	<!-- 방법 1: requestScope 사용 -->
	${requestScope.cvo.name}
	<br>
	<!-- 방법 2: requestScope (생략) -->
	${cvo.name}, ${cvo.num}, ${cvo.price}
	<br>

	<hr>

	<!-- 리스트로 변수 출력 -->
	<%
		ArrayList<CarVO> list = new ArrayList<CarVO>();
		list.add(new CarVO("4567","말리부",2500));
		list.add(new CarVO("1111","스파크",1500));
		session.setAttribute("carList", list);
	%>
	<strong>1. CarVO의 객체를 list로 출력</strong><br>
	${sessionScope.carList[0].name}<br>
	${sessionScope.carList[1].name}<br>
	<hr>
	<!-- 해쉬맵으로 변수를 출력 -->
	<%
	Map<String, CarVO> map = new HashMap<>();
	
	map.put("car1", new CarVO("1234","말리부",2500));
	map.put("car2", new CarVO("2345","아반떼",2300));
	request.setAttribute("hmap", map);
	 %>
	<strong>1. CarVO의 객체를 HashMap으로 출력</strong><br>
		${requestScope.hmap.get("car1").name},${requestScope.hmap.get("car1").num},${requestScope.hmap.get("car1").price}<br>
	 
	 <a href="step02-1.jsp">step02-1로 이동</a>
</body>
</html>