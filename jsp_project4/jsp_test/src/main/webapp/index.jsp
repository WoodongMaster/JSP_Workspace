<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<div align="center" style="position: relative; top: 150px;">
		<h1>테스트용으로 만들었습니다</h1>
		<br>

		<c:if test="${ses.id ne null}">
		${ses.id}님이 login 하였습니다. <br>
			<br>
			<br>
		</c:if>

		<c:choose>
			<c:when test="${ses.id ne null}">
				<a href="/mem/logout"><button class="btn btn-danger">로그아웃</button></a>
				<br>
				<br>
				<a href="/brd/register"><button class="btn btn-primary">게시글 작성</button></a>
				<br>
				<br>
				<a href="/mem/modify"><button class="btn btn-secondary">회원정보수정</button></a>
				<br>
				<br>
				<a href="/mem/list"><button class="btn btn-secondary">회원리스트 보기</button></a>
				<a href="/brd/page?type=w&keyword=${ses.id}"><button class="btn btn-secondary">내가
						쓴 글보기</button></a>
			</c:when>
			<c:otherwise>
				<a href="/mem/login"><button class="btn btn-success">로그인</button></a>
				<br>
				<br>
				<a href="/mem/join"><button class="btn btn-primary">회원가입</button></a>
			</c:otherwise>
		</c:choose>
		<a href="/brd/page"><button class="btn btn-secondary">게시글보기</button></a>
	</div>
	<script type="text/javascript">
	const edit_msg = `<c:out value="${edit_msg}" />`;
	const delete_msg = `<c:out value="${delete_msg}" />`;
	if(edit_msg == '0'){
		alert('정보가 수정되었습니다. 다시 로그인 해주세요');
	}
	if(delete_msg == '0'){
		alert('계정탈퇴가 완료되었습니다.');	
	}
	
	</script>
</body>
</html>