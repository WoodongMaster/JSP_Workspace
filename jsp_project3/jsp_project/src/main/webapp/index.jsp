<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<div align="center" style="position:relative;top: 150px;" >
	<h1>어서오세요 여기는 속마음게시판입니다!!</h1><br>

	<c:if test="${ses.id ne null}">
		${ses.id}님이 login 하였습니다. <br>
		<br><br>
	</c:if>

	<c:choose>
		<c:when test="${ses.id ne null}">		
		<a href="/mem/logout"><button class="btn btn-danger">로그아웃</button></a><br><br>
		<a href="/brd/register"><button>게시글 작성</button></a><br><br>
	<a href="/mem/modify_rdy"><button>회원정보수정</button></a><br><br>
	<a href="/mem/list"><button>회원리스트 보기</button></a>
	<a href="/brd/page?type=w&keyword=${ses.id}"><button>내가 쓴 글보기</button></a>
		</c:when>
		<c:otherwise>
		<a href="/mem/login"><button class="btn btn-success">로그인</button></a><br><br>
		<a href="/mem/join"><button>회원가입</button></a>
		</c:otherwise>
	</c:choose>
	<a href="/brd/page"><button>게시글보기</button></a>
</div>
</body>
</html>